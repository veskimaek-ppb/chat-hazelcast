package com.example.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;


/**
 * Spring beans connecting {@code spring-data-keyvalue} to the
 * specific implementation of the underlying Key-Value store.
 */
@Configuration
@EnableHazelcastRepositories(basePackages = {"com.example.chat"})
public class CommonConfiguration {
}
