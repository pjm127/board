package com.springboard.spring_board.service;

import com.springboard.spring_board.domain.User;
import com.springboard.spring_board.exception.NotFoundException;
import com.springboard.spring_board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(String username, String password, String email){
        String encPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(username,encPassword,email);
        userRepository.save(user);
        return user;
    }
    public User getUser(String username) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername!=null) {
            return byUsername;
        } else {
//            User oo = new User("oo");
//            return oo;
            throw new NotFoundException("user not found");
        }
    }
}
