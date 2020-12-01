package com.senla.courses.service;

import com.senla.courses.repository.UserRepository;
import com.senla.courses.repository.UserWallMessageRepository;
import com.senla.courses.repository.UserWallRepository;
import com.senla.courses.service.interfaces.UserWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWallServiceImpl implements UserWallService {

    @Autowired
    private UserWallRepository userWallRepository;
    @Autowired
    private UserWallMessageRepository userWallMessageRepository;
    @Autowired
    private UserRepository userRepository;
}
