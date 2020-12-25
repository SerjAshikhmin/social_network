package com.senla.cources.config;

import com.senla.cources.dto.mappers.*;
import com.senla.cources.utils.TestData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public GroupMapper groupMapper() {
        return new GroupMapperImpl();
    }

    @Bean
    public GroupWallMessageMapper groupWallMessageMapper() {
        return new GroupWallMessageMapperImpl();
    }

    @Bean
    public UserWallMessageMapper userWallMessageMapper() {
        return new UserWallMessageMapperImpl();
    }

    @Bean
    public PrivateMessageMapper privateMessageMapper() {
        return new PrivateMessageMapperImpl();
    }

    @Bean
    public TestData testData() {
        return new TestData();
    }
}
