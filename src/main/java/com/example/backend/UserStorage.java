package com.example.backend;
import org.mindrot.jbcrypt.BCrypt;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserStorage {
    static private Map<String, User> storage = new LinkedHashMap<>();

    public enum GetResp {
        OK,
        WRONG_LOGIN,
        WRONG_PASS
    }

    // Возращает bool - добавлен ли юзер, или уже есть
    public static boolean addUser(User u) {
        //String id = u.get();
        if (storage.containsKey(u.getLogin())) {
            return false;
        }

        // Заменям пароль на новый - хеш пароля со случайной солью
        String hashedPass = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        u.setPassword(hashedPass);
        storage.put(u.getLogin(), u);
        return true;
    }

    // Возвращает enum - нет юзера / неверный пароль / все ок
    public static GetResp getUser(User u) {
        User u2 = storage.get(u.getLogin());
        if (u2 == null) {
            return GetResp.WRONG_LOGIN;
        }

        // u - сожержит нехешированный пароль
        if (BCrypt.checkpw(u.getPassword(), u2.getPassword())) {
            return GetResp.WRONG_PASS;
        }
        return GetResp.OK;
    }

    /*public static User getUs(String logname) {
    return storage.get(logname);
    }

    /*public static boolean check (User d){  //есть ли юзер?
        Set<Map.Entry<String, User>> entrySet=storage.entrySet(); //поиск
        for (Map.Entry<String,User> pair : entrySet) {
            if (d.equals(pair.getValue())) {
                return true; // нашли наше значение
            }
        }
        return false;
    }
    */
}
