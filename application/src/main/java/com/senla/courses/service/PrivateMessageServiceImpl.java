package com.senla.courses.service;

import com.senla.courses.repository.PrivateMessageRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;
    @Autowired
    private UserRepository userRepository;
}
