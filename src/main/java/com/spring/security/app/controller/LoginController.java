package com.spring.security.app.controller;

import com.spring.security.app.exceptions.UserAlreadyExistsException;
import com.spring.security.app.exceptions.UserNotFoundException;
import com.spring.security.app.model.ErrorResponse;
import com.spring.security.app.model.User;
import com.spring.security.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Map<String, String> params) throws UserAlreadyExistsException {
        try {
            userService.registerUser(params);
            return new ResponseEntity("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(e.getMessage(), Collections.emptyList()), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> params) {

        try {
            User user= userService.login(params);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(new ErrorResponse(e.getMessage() , Collections.emptyList()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity getUserDetail(@PathVariable("uuid") String uuid) {
        User user = userService.getUserDetail(uuid);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users/{uuid}")
    public ResponseEntity updateUser(@PathVariable("uuid") String uuid,
                                        @RequestBody Map<String, String> params) {

        try {
            userService.updateUser(uuid, params);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }

    }
}
