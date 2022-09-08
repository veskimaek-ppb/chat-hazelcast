package com.example.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class HazelcastServerConfiguration {

    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Create the datasource onto the database.
     *
     * Specify the driver class, so any connection error is clearer if the database is down.
     *
     * @return A datasource onto the database server
     */
    @Bean
    public DataSource dataSource() throws Exception {
        String driverClassName = org.hsqldb.jdbc.JDBCDriver.class.getName();

        return DataSourceBuilder.create()
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .driverClassName(driverClassName)
                .build();
    }
}
