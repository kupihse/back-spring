package com.example.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
public class ProductStorage {
    static Map<String, Product> storage = new LinkedHashMap<>();
    private static Random random = new Random();

    public static void addProduct(Product p) {
      String id = UUID.randomUUID().toString();
      p.setId(id);
      storage.put(id, p);
    }

    public static Product getProduct(String id) {
      return storage.get(id);
    }

}
