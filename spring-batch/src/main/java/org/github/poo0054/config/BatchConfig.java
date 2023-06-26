package org.github.poo0054.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author zhangzhi
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilders;

    @Autowired
    StepBuilderFactory stepBuilders;

    @Bean
    public Job footballjob() {
        return jobBuilders.get("test-job")
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println("我是beforeJob--" + jobExecution.toString());
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println("我是--afterJob" + jobExecution.toString());
                    }
                })
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        TaskletStep taskletStep = stepBuilders.get("test-step")
                .<Integer, Integer>chunk(2)
                .reader(itemReader())
                .writer(itemWriter())
                .startLimit(2)
                .build();

        taskletStep.setAllowStartIfComplete(true);
        return taskletStep;
    }


    @Bean
    public ItemReader<Integer> itemReader() {
        return new ItemReader<Integer>() {
            final AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public Integer read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
                if (atomicInteger.compareAndSet(10, 0)) {
                    return null;
                }
                atomicInteger.getAndIncrement();
                return atomicInteger.intValue();
            }
        };
    }

    @Bean
    public ItemWriter<Integer> itemWriter() {
        return new ItemWriter<Integer>() {
            @Override
            public void write(List<? extends Integer> items) {
                System.out.println("一次读取：" + Arrays.toString(items.toArray()));
            }
        };

    }
}
