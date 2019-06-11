package com.backend.api.security;

import com.backend.api.model.entity.User;
import com.backend.api.model.service.IuserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IuserService iuserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iuserService.getUser(username);
        UserDetailsImpl userDetailsImpl = null;
        if (user != null) {
            userDetailsImpl = new UserDetailsImpl();
            userDetailsImpl.setUser(user);
        } else {
            throw new UsernameNotFoundException("user doesn't exist");
        }
        return userDetailsImpl;
    }
}
