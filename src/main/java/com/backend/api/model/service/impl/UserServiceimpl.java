package com.backend.api.model.service.impl;

import com.backend.api.model.dao.IuserDao;
import com.backend.api.model.entity.User;
import com.backend.api.model.service.IuserService;
import com.backend.api.model.sro.UserSro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceimpl implements IuserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IuserDao userDao;

    @Override
    @Transactional
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    @Transactional
    public Boolean userEnable(int userId, boolean enable) {
        if (userDao.userEnable(userId, enable) > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User userResponse = userDao.addorUpdateUser(user);
        return userResponse;
    }
}
