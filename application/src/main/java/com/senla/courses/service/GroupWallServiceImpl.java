package com.senla.courses.service;

import com.senla.courses.repository.GroupWallMessageRepository;
import com.senla.courses.repository.GroupWallRepository;
import com.senla.courses.service.interfaces.GroupWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupWallServiceImpl implements GroupWallService {

    @Autowired
    private GroupWallRepository groupWallRepository;
    @Autowired
    private GroupWallMessageRepository groupWallMessageRepository;
}
