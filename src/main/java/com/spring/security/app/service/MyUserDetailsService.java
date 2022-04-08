package com.spring.security.app.service;

import com.spring.security.app.dao.UserDao;
import com.spring.security.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findUser(username);

        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles("USER").build();
            return userDetails;

        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean checkUserId(Authentication authentication, String id) {

        User user = userDao.findUserByUuid(id);

        if (user != null) {
            return true;
        }
        return false;
    }
}
