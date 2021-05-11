package com.senla.cources.repository;

import com.senla.cources.domain.User;

import java.util.List;

public interface UserSearchingRepository {

    List<User> searchUsers
            (String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears);
}
