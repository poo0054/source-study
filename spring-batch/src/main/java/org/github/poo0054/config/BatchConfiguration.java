package org.github.poo0054.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzhi
 */
@Configuration
public class BatchConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job footballjob() {
        return jobBuilderFactory.get("test").start(step()).build();
    }

    @Bean
    public Step step() {
        TaskletStep build = stepBuilderFactory.get("test-stop")
                .<MessageSource, MessageSource>chunk(1)
                .build();
        return build;
    }

}
