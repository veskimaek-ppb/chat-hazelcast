package com.example.chat;

import com.example.chat.repository.hz.ChatMessageRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Spring beans connecting {@code spring-data-keyvalue} to the
 * specific implementation of the underlying Key-Value store.
 */
@Configuration
@EnableHazelcastRepositories(basePackages = {"com.example.chat.repository.hz"})
@EnableJpaRepositories(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                ChatMessageRepository.class
        }
        )
})
public class CommonConfiguration {
}
