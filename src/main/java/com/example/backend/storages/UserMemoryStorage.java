package com.example.backend.storages;
import org.springframework.stereotype.Repository;
import com.example.backend.models.User;
import org.mindrot.jbcrypt.BCrypt;
import com.example.backend.storages.dao.UserDAO;


import java.util.*;

@Repository
public class UserMemoryStorage implements UserDAO{
    static private Map<String, User> storage = new LinkedHashMap<>();
    static private Map<String, User> nonConfirmed = new LinkedHashMap<>();

    public GetResp add(User u){
        if (storage.containsKey(u.getLogin())) {
            return GetResp.ALREADY_EXISTS;
        }
        else {
            String hashPass = BCrypt.hashpw(u.getLogin(), BCrypt.gensalt());
            u.setPassword(hashPass);
            u.setNotConfirmed();
            storage.put(u.getLogin(), u);
            return GetResp.OK;
        }
    }


    public GetResp log(User u){
        User Checkable = storage.get(u.getLogin());
            if (Checkable == null){
                return GetResp.USER_NOT_EXISTS;
            }
            else if (!BCrypt.checkpw(u.getPassword(),Checkable.getPassword())){
                return GetResp.WRONG_PASSWORD;
            }
            return GetResp.OK;
    }

    //------------- Все, что дальше для модеров, кек -----------------
    //----------------------------------------------------------------
    public GetResp deleteUser(User u){
        int switcher;
        if (nonConfirmed.get(u.getLogin())!=null) switcher = 1;
        else if (storage.get(u.getLogin())!=null) switcher = 2;
        else switcher = 0;

        switch (switcher){
            case 1:
                nonConfirmed.remove(u.getLogin());
                return GetResp.OK;
            case 2:
                storage.remove(u.getLogin());
                return GetResp.OK;
            default:
                return GetResp.FAIL;

        }
    }
    public List<User> getAllConfirmed() {
        return new ArrayList<>(storage.values());
    }
    public List<User> getNonConfirmed(){
        return new ArrayList<>(nonConfirmed.values());
    }

}
