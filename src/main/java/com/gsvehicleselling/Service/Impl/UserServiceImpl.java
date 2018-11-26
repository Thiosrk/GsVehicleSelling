package com.gsvehicleselling.Service.Impl;

import com.gsvehicleselling.Model.User;
import com.gsvehicleselling.Repository.UserRepository;
import com.gsvehicleselling.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userID) {
        return userRepository.getOne(userID);
    }

    @Override
    public User getUser(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User modifyUser(User user) {

        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        List<User> user = new ArrayList<>();
        for(User u : users){
            if (u.getAuthority() == 0)
                user.add(u);
        }
        return user;
    }

    @Override
    public void delete(int userid) {
        userRepository.deleteById(userid);
    }
}
