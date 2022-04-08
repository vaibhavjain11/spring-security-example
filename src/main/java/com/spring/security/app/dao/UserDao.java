package com.spring.security.app.dao;

import com.spring.security.app.model.User;
import com.spring.security.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    UserRepository repository;

    public User findUser(String userName) {
        return repository.findByUserName(userName);
    }

    public User findUserByUuid(String uuid) {
        return repository.findByUuId(uuid);
    }

    public void save(User user) {
        repository.save(user);
    }
}
