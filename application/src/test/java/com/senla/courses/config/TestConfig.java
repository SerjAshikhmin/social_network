package com.senla.courses.config;

import com.senla.courses.dto.mappers.*;
import com.senla.courses.repository.GroupRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.utils.TestData;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {


    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;

    /*@Bean
    public GroupService groupService() {
        return new GroupServiceImpl();
    }*/

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
