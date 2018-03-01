package com.example.backend.storages.dao;

import com.example.backend.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Andreyko0 on 20/01/2018.
 */
public interface ProductDAO {
  void addProduct(Product p);
  Product getProduct(String id);
  List<Product> getAll();
  Stream<Product> stream();
  ArrayList<String> getProductsBySeller(String sellerId);
  void deleteAll();
  int size();
}
