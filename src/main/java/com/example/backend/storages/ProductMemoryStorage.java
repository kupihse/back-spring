package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@Repository("products-memory")
public class ProductMemoryStorage implements ProductDAO {

  // список товаров
  ArrayList<Product> products = new ArrayList<>();

  // мап id товара к его индексу в списке
  // для быстрого поиска
  HashMap<String, Integer> indexMap = new HashMap<>();

  @Override
  public void addProduct(Product p) {
    indexMap.replaceAll((k, v) -> ++v);
    products.add(0, p);
    indexMap.put(p.getId(), 0);
  }

  @Override
  public Product getProduct(String id) {
    int index = indexMap.get(id);
    return products.get(index);
  }

  @Override
  public List<Product> getProductsBySellerId(String sellerId) {
    return products.stream()
            .filter((p) -> p.getSellerId().equals(sellerId))
            .collect(Collectors.toList());
  }

  @Override
  public List<Product> getNBySellerId(String sellerId, int start, int n) {
    return products.stream()
            .filter((p) -> p.getSellerId().equals(sellerId))
            .skip(start)
            .limit(n)
            .collect(Collectors.toList());
  }

  @Override
  public List<Product> getAll() {
    return products;
  }

  @Override
  public List<Product> getN(int start, int n) {
    return products.stream().skip(start).limit(n).collect(Collectors.toList());
  }

  @Override
  public boolean deleteProduct(String id) {
    Integer idx = indexMap.remove(id);
    if (idx == null) {
      return false;
    }
    return products.remove(idx.intValue()) != null;
  }

  @Override
  public void deleteAll() {
    products.clear();
    indexMap.clear();
  }

  @Override
  public int size() {
    return products.size();
  }

  @Override
  public int sizeBySellerId(String sellerId) {
    return (int) products.stream()
            .filter((p) -> p.getSellerId().equals(sellerId))
            .count();
  }

  @Override
  public List<Product> getByIds(List<String> ids) {
    return products.stream().filter(p->ids.indexOf(p.getId())!=-1).collect(Collectors.toList());
  }

  @Override
  public List<Product> search(String query) {
    return products.stream()
            .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
  }

  @Override
  public List<String> suggestNames(String query) {
    return products.stream()
            .map(p -> p.getName())
            .filter(s -> s.toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
  }

}
