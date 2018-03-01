package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@Repository
public class ProductMemoryStorage implements ProductDAO {

  // список товаров
  ArrayList<Product> products = new ArrayList<>();

  // мап id товара к его индексу в списке
  // для быстрого поиска
  HashMap<String, Integer> indexMap = new HashMap<>();

  public void addProduct(Product p) {
    indexMap.replaceAll((k, v) -> ++v);
    products.add(0, p);
    indexMap.put(p.getId(), 0);
  }

  public Product getProduct(String id) {
    int index = indexMap.get(id);
    return products.get(index);
  }

  public Stream<Product> getProductStreamByIds(Set<String> ids) {
    return ids.stream()
            .map(id -> indexMap.get(id))
            .map(idx -> {
              if (idx == null) {
                return null;
              }
              return products.get(idx);
            });
  }

  public List<Product> getAll() {
    return products;
  }

  public Stream<Product> stream() {
    return products.stream();
  }

  public void deleteAll() {
    products.clear();
    indexMap.clear();
  }

  public int size() {
    return products.size();
  }
}
