package org.github.poo0054.dynamic.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * @author zhangzhi
 */
@Configuration(proxyBeanMethods = false)
@EnableAspectJAutoProxy(exposeProxy = true)
public class DynamicConfig {

    @Bean
    @ConditionalOnMissingBean(TransactionManager.class)
    DataSourceTransactionManager transactionManager(Environment environment, DataSource dataSource,
                                                    ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        DataSourceTransactionManager transactionManager = createTransactionManager(environment, dataSource);
        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
        return transactionManager;
    }

    private DataSourceTransactionManager createTransactionManager(Environment environment, DataSource dataSource) {
        return environment.getProperty("spring.dao.exceptiontranslation.enabled", Boolean.class, Boolean.TRUE)
                ? new JdbcTransactionManager(dataSource) : new DataSourceTransactionManager(dataSource);
    }
}
