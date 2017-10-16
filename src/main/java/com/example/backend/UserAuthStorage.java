package com.example.backend;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Set;

public class UserAuthStorage {
    static Map<String, UserAuth> authstorage = new LinkedHashMap<>();
    private static Random random = new Random();

    public static void addUser(UserAuth u) {
        String id = UUID.randomUUID().toString();
        u.setId(id);
        authstorage.put(id, u);
    }

    public static UserAuth getUs(String id) {
        return authstorage.get(id);
    }

    public static boolean check (UserAuth d){  //есть ли юзер?
        Set<Map.Entry<String, UserAuth>> entrySet=authstorage.entrySet(); //поиск
        for (Map.Entry<String,UserAuth> pair : entrySet) {
            if (d.equals(pair.getValue())) {
                return true; // нашли наше значение
            }
        }
        return false;
    }
}
