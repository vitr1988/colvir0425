package com.colvir.webinar19.config;

import com.integralblue.log4jdbc.spring.Log4jdbcBeanPostProcessor;
import com.zaxxer.hikari.HikariDataSource;
import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

/**
 * Prevents class cast exceptions in {@link org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata#getDataSource()},
 * when HikariDataSource is actually DataSourceSpy implementation.
 */
@Component
public class CustomLog4jdbcBeanPostProcessor extends Log4jdbcBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof HikariDataSource) {
            return new WrappedSpyDataSource((DataSourceSpy) super.postProcessBeforeInitialization(bean, beanName), (HikariDataSource) bean);
        } else {
            return super.postProcessBeforeInitialization(bean, beanName);
        }
    }
}

