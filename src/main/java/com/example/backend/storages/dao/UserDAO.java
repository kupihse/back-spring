package com.example.backend.storages.dao;
import com.example.backend.models.User;
import java.util.List;

public interface UserDAO {
    GetResp add(User u);
    GetResp log(User u);
    GetResp deleteUser(User u);  // функции для модераторов, естественно жи
    List<User> getAllConfirmed();// -----||-------
    List<User> getNonConfirmed();// -----||-------


    enum GetResp {
        OK,
        WRONG_PASSWORD,
        ALREADY_EXISTS,
        USER_NOT_EXISTS,
        FAIL
    }
}
