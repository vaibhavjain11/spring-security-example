package com.spring.security.app.service;

import com.spring.security.app.dao.UserDao;
import com.spring.security.app.exceptions.UserAlreadyExistsException;
import com.spring.security.app.exceptions.UserNotFoundException;
import com.spring.security.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(Map<String, String> params) throws UserAlreadyExistsException {
        String userName = params.get("userName");
        String password = params.get("password");
        String role = params.get("role");

        User user = userDao.findUser(userName);

        if (user != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        UUID uuid = UUID.randomUUID();
        user.setUuId(uuid.toString());

        userDao.save(user);
        LOG.info("User : {}", user);

    }

    public User login(Map<String, String> params) throws UserNotFoundException {

        String userName = params.get("userName");
        String password = params.get("password");

       User user = userDao.findUser(userName);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                LOG.info("User {} found", userName);
                return user;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public User getUserDetail(String uuid) {

        User user = userDao.findUserByUuid(uuid);

        if (user != null) {
            return user;
        }

        return null;

    }

    public void updateUser(String uuid, Map<String, String> params) {

        User user = userDao.findUserByUuid(uuid);

        user.setName(params.get("name"));
        user.setPhone(params.get("phone"));
        user.setEmail(params.get("email"));

        userDao.save(user);


    }
}
