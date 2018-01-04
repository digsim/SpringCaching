package org.homelinux.digsim.config;

import org.homelinux.digsim.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Read this http://www.baeldung.com/properties-with-spring about how to use
 * <coce>PropertySourcesPlaceholderConfigurer</coce> together with <code><PropertySource/code>.
 */
@Configuration
@PropertySource("classpath:persistence.properties")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = Application.class)
class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}