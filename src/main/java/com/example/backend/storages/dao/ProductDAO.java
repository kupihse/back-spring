package com.example.backend.storages.dao;

import com.example.backend.models.Product;

import java.util.List;

/**
 * Created by Andreyko0 on 20/01/2018.
 */
public interface ProductDAO {
  void addProduct(Product p);
  Product getProduct(String id);
  List<Product> getAll();
  List<Product> getN(int start, int n);
  List<Product> getProductsBySellerId(String sellerId);
  List<Product> getNBySellerId(String sellerId, int start, int n);
  boolean deleteProduct(String id);
  void deleteAll();
  int size();
}
