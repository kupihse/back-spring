package com.example.backend;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Set;

public class AuthProcStorage {
    static Map<String, AuthProc> authstorage = new LinkedHashMap<>();
    private static Random random = new Random();

    public static void addUser(AuthProc u) {
        String id = UUID.randomUUID().toString();
        u.setId(id);
        authstorage.put(id, u);
    }

    public static AuthProc getUs(String id) {
        return authstorage.get(id);
    }

    public static boolean check (AuthProc d){  //есть ли юзер?
        Set<Map.Entry<String, AuthProc>> entrySet=authstorage.entrySet(); //поиск
        for (Map.Entry<String,AuthProc> pair : entrySet) {
            if (d.equals(pair.getValue())) {
                return true; // нашли наше значение
            }
        }
        return false;
    }
}
