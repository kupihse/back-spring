package com.example.backend;
import org.mindrot.jbcrypt.BCrypt;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class UserStorage {
    static private Map<String, User> storage = new LinkedHashMap<>();

    static private Map<String, String> notConfirmedUsers = new LinkedHashMap<>();

    public enum GetResp {
        OK,
        WRONG_LOGIN,
        WRONG_PASS
    }

    // Возращает bool - добавлен ли юзер, или уже есть
    public static String addUser(User u) {
        //String id = u.get();
        if (storage.containsKey(u.getLogin())) {
            return "";
        }

        // Заменям пароль на новый - хеш пароля со случайной солью
        String hashedPass = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        u.setPassword(hashedPass);
        u.setNotConfirmed();
        storage.put(u.getLogin(), u);
        String id = UUID.randomUUID().toString();
        notConfirmedUsers.put(id, u.getLogin());
        return id;
    }

    public static void confirmUser(String id) {
        String userLogin = notConfirmedUsers.get(id);
        storage.get(userLogin).confirm();
        notConfirmedUsers.remove(id);
    }

    // Возвращает enum - нет юзера / неверный пароль / все ок
    public static GetResp getUser(User u) {
        User u2 = storage.get(u.getLogin());
        if (u2 == null) {
            return GetResp.WRONG_LOGIN;
        }

        // u - сожержит нехешированный пароль
        if (!BCrypt.checkpw(u.getPassword(), u2.getPassword())) {
            return GetResp.WRONG_PASS;
        }
        return GetResp.OK;
    }

    public static Map<String, User> getStorage() {
        return storage;
    }

    public static Map<String, String > getNotConfirmedUsers() {
        return notConfirmedUsers;
    }
}
