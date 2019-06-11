package com.backend.api.model.dao;

import com.backend.api.model.entity.User;

public interface IuserDao {

    User getUser(String username);

    Integer userEnable(int userId, boolean enable);

    User addorUpdateUser(User user);
}
