package com.example.backend;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class UserStorage {
    static Map<String, User> authstorage = new LinkedHashMap<>();
    private static Random random = new Random();

    public static void addUser(User u) {
        //String id = u.get();
        authstorage.put(u.getLogname(), u);
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
