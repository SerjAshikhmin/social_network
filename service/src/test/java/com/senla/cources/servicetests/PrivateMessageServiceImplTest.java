package com.senla.cources.servicetests;

import com.senla.cources.config.TestConfig;
import com.senla.cources.domain.PrivateMessage;
import com.senla.cources.domain.User;
import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.dto.PrivateMessageDto;
import com.senla.cources.dto.mappers.PrivateMessageMapper;
import com.senla.cources.exceptions.messageexceptions.SendMessageException;
import com.senla.cources.exceptions.messageexceptions.ShowDialogException;
import com.senla.cources.repository.PrivateMessageRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.service.PrivateMessageServiceImpl;
import com.senla.cources.utils.TestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class PrivateMessageServiceImplTest {

    @InjectMocks
    private PrivateMessageServiceImpl privateMessageService;
    @Mock
    private PrivateMessageRepository privateMessageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private PrivateMessageMapper privateMessageMapper;
    @Autowired
    private TestData testData;

    @BeforeAll
    public static void startPrivateMessageServiceImplTests() {
        log.info("Starting privateMessageServiceImpl tests");
    }

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        privateMessageService.setPrivateMessageMapper(privateMessageMapper);
    }

    @Test
    public void sendMessageTest() {
        log.info("Starting sendMessage test");
        PrivateMessage testMessage = testData.getTestPrivateMessages().get(0);
        User testUser = testData.getTestUserList().get(1);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        privateMessageService.sendMessage(privateMessageMapper.privateMessageToPrivateMessageDto(testMessage), 2);

        verify(userRepository, times(1)).findById(anyInt());
        verify(userPrincipalRepository, times(1)).findMyUserPrincipalByUserName(anyString());
        verify(privateMessageRepository, times(1)).save(any(PrivateMessage.class));
    }

    @Test
    public void sendMessageToNotExistingReceiverTest() {
        log.info("Starting sendMessageToNotExistingReceiver test");
        PrivateMessage testMessage = testData.getTestPrivateMessages().get(0);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(5)).thenThrow(new NoSuchElementException());
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        assertThrows(SendMessageException.class, () -> privateMessageService.sendMessage(privateMessageMapper.privateMessageToPrivateMessageDto(testMessage), 5));
        verify(userRepository, times(1)).findById(anyInt());
        verify(userPrincipalRepository, times(1)).findMyUserPrincipalByUserName(anyString());
    }

    @Test
    public void showDialogTest() {
        log.info("Starting showDialog test");
        User testUser = testData.getTestUserList().get(1);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        when(privateMessageRepository.getPrivateMessagesBySenderAndReceiver(testData.getTestUserList().get(0), testUser))
                .thenReturn(new ArrayList(Arrays.asList(testData.getTestPrivateMessages().get(0), testData.getTestPrivateMessages().get(1))));
        when(privateMessageRepository.getPrivateMessagesBySenderAndReceiver(testUser, testData.getTestUserList().get(0)))
                .thenReturn(new ArrayList(Arrays.asList(testData.getTestPrivateMessages().get(2))));
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        List<PrivateMessageDto> expectedResult = privateMessageMapper.privateMessageListToPrivateMessageDtoList(testData.getTestPrivateMessages());

        List<PrivateMessageDto> actualResult = privateMessageService.showDialog(2);
        assertEquals(actualResult, expectedResult);
        verify(userRepository, times(1)).findById(anyInt());
        verify(userPrincipalRepository, times(1)).findMyUserPrincipalByUserName(anyString());
        verify(privateMessageRepository, times(2)).getPrivateMessagesBySenderAndReceiver(any(User.class), any(User.class));
    }

    @Test
    public void showDialogWithNotExistingUserTest() {
        log.info("Starting showDialogWithNotExistingUser test");
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(5)).thenThrow(new NoSuchElementException());
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        assertThrows(ShowDialogException.class, () -> privateMessageService.showDialog(5));
        verify(userRepository, times(1)).findById(anyInt());
        verify(userPrincipalRepository, times(1)).findMyUserPrincipalByUserName(anyString());
    }
}
