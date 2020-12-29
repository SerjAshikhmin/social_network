package com.senla.cources.service.security;

import com.senla.cources.domain.Group;
import com.senla.cources.domain.User;
import com.senla.cources.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private UserPrincipalRepository userPrincipalRepository;

    public boolean checkPermissionForUserWall(User user) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
        if (user.getUserPrincipal().getUsername().equals(currentUserName)
                || currentUser.getUserPrincipal().getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet())
                .contains("ADMIN")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForGroup(Group group) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
        if (currentUser.getAdminInGroups().contains(group)
                || currentUser.getUserPrincipal().getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet())
                .contains("ADMIN")) {
            return true;
        } else {
            return false;
        }
    }
}
