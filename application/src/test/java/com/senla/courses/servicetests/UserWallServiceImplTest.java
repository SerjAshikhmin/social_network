package com.senla.courses.servicetests;

import com.senla.courses.domain.*;
import com.senla.courses.dto.mappers.UserWallMessageMapper;
import com.senla.courses.dto.mappers.UserWallMessageMapperImpl;
import com.senla.courses.exceptions.messageexceptions.PostMessageException;
import com.senla.courses.exceptions.messageexceptions.RemoveMessageException;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.repository.UserWallMessageRepository;
import com.senla.courses.repository.UserWallRepository;
import com.senla.courses.service.UserWallServiceImpl;
import com.senla.courses.utils.TestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserWallServiceImplTest {

    @InjectMocks
    private UserWallServiceImpl userWallService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserWallRepository userWallRepository;
    @Mock
    private UserWallMessageRepository userWallMessageRepository;
    private final UserWallMessageMapper userWallMessageMapper;
    private final TestData testData;

    public UserWallServiceImplTest() {
        this.userWallMessageMapper = new UserWallMessageMapperImpl();
        this.testData = new TestData();
    }

    @BeforeAll
    public static void startUserWallServiceImplTests() {
        log.info("Starting userWallServiceImpl tests");
    }

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        userWallService.setUserWallMessageMapper(userWallMessageMapper);
    }

    @Test
    public void postMessageTest() {
        log.info("Starting postMessage test");
        UserWallMessage testMessage = testData.getTestUserWallMessages().get(0);
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testUser.getUserPrincipal().getUsername(), testUser.getUserPrincipal().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        userWallService.postMessage(1, userWallMessageMapper.userWallMessageToUserWallMessageDto(testMessage));

        verify(userRepository, times(1)).findById(anyInt());
        verify(userWallRepository, times(1)).save(any(UserWall.class));
    }

    @Test
    public void postMessageInNotExistingUserTest() {
        log.info("Starting postMessageInNotExistingUser test");
        UserWallMessage testMessage = testData.getTestUserWallMessages().get(0);
        when(userRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(PostMessageException.class, () -> userWallService.postMessage(5, userWallMessageMapper.userWallMessageToUserWallMessageDto(testMessage)));
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeMessageTest() {
        log.info("Starting removeMessage test");
        UserWallMessage testMessage = testData.getTestUserWallMessages().get(0);
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(userWallMessageRepository.findById(anyInt())).thenReturn(Optional.of(testMessage));

        userWallService.removeMessage(1, 1);

        verify(userRepository, times(1)).findById(anyInt());
        verify(userWallMessageRepository, times(1)).findById(anyInt());
        verify(userWallMessageRepository, times(1)).delete(any(UserWallMessage.class));
    }

    @Test
    public void removeMessageInNotExistingUserTest() {
        log.info("Starting removeMessageInNotExistingUser test");
        when(userRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveMessageException.class, () -> userWallService.removeMessage(5, 1));
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeNotExistingMessageTest() {
        log.info("Starting removeNotExistingMessage test");
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(userWallMessageRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveMessageException.class, () -> userWallService.removeMessage(1, 5));
        verify(userWallMessageRepository, times(1)).findById(anyInt());
    }
}
