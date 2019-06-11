package com.backend.api.model.service;

import com.backend.api.model.entity.User;
import com.backend.api.model.sro.UserSro;

public interface IuserService {

    User getUser(String username);

    Boolean userEnable(int userId, boolean enable);

    User addUser(User user);
}
