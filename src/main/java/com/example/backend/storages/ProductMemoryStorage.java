package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@Repository
public class ProductMemoryStorage implements ProductDAO {
    Map<String, Product> storage = new LinkedHashMap<>();

    public void addProduct(Product p) {
      String id = UUID.randomUUID().toString();
      p.setId(id);
      storage.put(id, p);
    }

    public Product getProduct(String id) {
      return storage.get(id);
    }
    public List<Product> getAll() {
      return new ArrayList<>(storage.values());
    }

    public Stream<Product> stream() {
      return storage.values().stream();
    }

  public void deleteAll() {
    storage.clear();
  }
}
