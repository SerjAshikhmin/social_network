package com.senla.cources.servicetests;

import com.senla.cources.config.TestConfig;
import com.senla.cources.domain.Group;
import com.senla.cources.domain.GroupWall;
import com.senla.cources.domain.GroupWallMessage;
import com.senla.cources.dto.mappers.GroupWallMessageMapper;
import com.senla.cources.exceptions.messageexceptions.PostMessageException;
import com.senla.cources.exceptions.messageexceptions.RemoveMessageException;
import com.senla.cources.repository.GroupRepository;
import com.senla.cources.repository.GroupWallMessageRepository;
import com.senla.cources.repository.GroupWallRepository;
import com.senla.cources.service.GroupWallServiceImpl;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class GroupWallServiceImplTest {

    @InjectMocks
    private GroupWallServiceImpl groupWallService;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private GroupWallRepository groupWallRepository;
    @Mock
    private GroupWallMessageRepository groupWallMessageRepository;
    @Autowired
    private GroupWallMessageMapper groupWallMessageMapper;
    @Autowired
    private TestData testData;

    @BeforeAll
    public static void startGroupWallServiceImplTests() {
        log.info("Starting groupWallServiceImpl tests");
    }

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        groupWallService.setGroupWallMessageMapper(groupWallMessageMapper);
    }

    @Test
    public void postMessageTest() {
        log.info("Starting postMessage test");
        GroupWallMessage testMessage = testData.getTestGroupWallMessages().get(0);
        Group testGroup = testData.getTestGroupList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));

        groupWallService.postMessage(1, groupWallMessageMapper.groupWallMessageToGroupWallMessageDto(testMessage));

        verify(groupRepository, times(1)).findById(anyInt());
        verify(groupWallMessageRepository, times(1)).save(any(GroupWallMessage.class));
        verify(groupWallRepository, times(1)).save(any(GroupWall.class));
    }

    @Test
    public void postMessageInNotExistingGroupTest() {
        log.info("Starting postMessageInNotExistingGroup test");
        GroupWallMessage testMessage = testData.getTestGroupWallMessages().get(0);
        when(groupRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(PostMessageException.class, () -> groupWallService.postMessage(5, groupWallMessageMapper.groupWallMessageToGroupWallMessageDto(testMessage)));
        verify(groupRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeMessageTest() {
        log.info("Starting removeMessage test");
        Group testGroup = testData.getTestGroupList().get(0);
        GroupWallMessage testMessage = testData.getTestGroupWallMessages().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));
        when(groupWallMessageRepository.findById(anyInt())).thenReturn(Optional.of(testMessage));

        groupWallService.removeMessage(1, 1);

        verify(groupRepository, times(1)).findById(anyInt());
        verify(groupWallMessageRepository, times(1)).findById(anyInt());
        verify(groupWallMessageRepository, times(1)).delete(any(GroupWallMessage.class));
    }

    @Test
    public void removeMessageInNotExistingGroupTest() {
        log.info("Starting removeMessageInNotExistingGroup test");
        when(groupRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveMessageException.class, () -> groupWallService.removeMessage(5, 1));
        verify(groupRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeNotExistingMessageTest() {
        log.info("Starting removeNotExistingMessage test");
        Group testGroup = testData.getTestGroupList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));
        when(groupWallMessageRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveMessageException.class, () -> groupWallService.removeMessage(1, 5));
        verify(groupWallMessageRepository, times(1)).findById(anyInt());
    }
}
