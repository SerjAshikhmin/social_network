package com.senla.courses.service;

import com.senla.courses.repository.GroupRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

}
