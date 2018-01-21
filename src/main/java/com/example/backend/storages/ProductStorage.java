package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andreyko0 on 20/01/2018.
 */

//@Service
//public class ProductStorage implements ProductDAO {
//  private ProductDAO dao = new ProductMemoryStorage();
//
//  public void addProduct(Product p) {
//    dao.addProduct(p);
//  }
//  public Product getProduct(String id){
//    return dao.getProduct(id);
//  }
//  public List<Product> getAll(){
//    return dao.getAll();
//  }
//}