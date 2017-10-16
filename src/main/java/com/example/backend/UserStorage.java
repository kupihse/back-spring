package com.example.backend;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class UserStorage {
    static Map<String, User> authstorage = new LinkedHashMap<>();

    // Возращает bool - добавлен ли юзер, или уже есть
    public static boolean addUser(User u) {
        //String id = u.get();
        if (authstorage.containsKey(u.getLogname())) {
            return false;
        }
        authstorage.put(u.getLogname(), u);
        return true;
    }

    /*public static User getUs(String logname) {
    return authstorage.get(logname);
    }

    /*public static boolean check (User d){  //есть ли юзер?
        Set<Map.Entry<String, User>> entrySet=authstorage.entrySet(); //поиск
        for (Map.Entry<String,User> pair : entrySet) {
            if (d.equals(pair.getValue())) {
                return true; // нашли наше значение
            }
        }
        return false;
    }
    */
}
