package com.senla.cources.service.interfaces;

import com.senla.cources.dto.PrivateMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivateMessageService {

    void sendMessage(PrivateMessageDto message, int receiverId);
    void receiveMessages(int senderId);
    List<PrivateMessageDto> showDialog(int userId);
}
