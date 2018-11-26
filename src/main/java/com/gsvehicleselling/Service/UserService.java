package com.gsvehicleselling.Service;

import com.gsvehicleselling.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUser(int userID);

    User getUser(String nickname);

    void saveUser(User user);

    User modifyUser(User user);

    List<User> getAllUser();

    void delete(int userid);
}
