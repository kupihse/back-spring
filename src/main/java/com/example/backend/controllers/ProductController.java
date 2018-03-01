package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.models.User;
import com.example.backend.storages.dao.ProductDAO;
import com.example.backend.storages.dao.UserDAO;
import com.example.backend.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@RestController
@RequestMapping("/pr")
public class ProductController {

  @Autowired
  private ProductDAO productStorage;

  @Autowired
  private UserDAO userStorage;



  // Генерируем id товара и ставим ему дату в init()
  // Добавляем товар в Storage и добавляем юзеру id товара
  @PostMapping(value = "/new")
  @ResponseBody
  public void addProduct(@RequestBody Product p, HttpServletResponse resp) {

    p.init();
    System.out.println(p.getSendableDate());

    productStorage.addProduct(p);

    User user = userStorage.get(p.getSellerId());
    if (user == null) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      return;
    }
    user.addProduct(p.getId());

    System.out.println("Got prd");
    resp.setStatus(HttpServletResponse.SC_OK);
  }


  // Список всех товаров
  @GetMapping(value = "/all")
  public List<Product> getAll() {
    System.out.println("Responded with all products, and logged it, testing again");
    return productStorage.getAll();
  }


  // Один товар по id
  @GetMapping(value = "/id/{id}")
  public Product getProduct(@PathVariable("id") String id) {
    return productStorage.getProduct(id);
  }


  // Берем stream товаров (не список – для гибкости) для определенного юзера
  private Pair<Stream<Product>, Integer> getBySellerId(String sellerId) {
    User user = userStorage.get(sellerId);
    if (user == null) {
      return null;
    }
    Set<String> ids = user.getProducts();
    return new Pair<>(productStorage.getProductStreamByIds(ids), ids.size());
  }


  // Все товары конкретного юзера
  @GetMapping(value = "/sellerId/{sellerId}/all")
  public ResponseEntity<List<Product>> getAllBySellerId(@PathVariable("sellerId") String sellerId) {
    Pair<Stream<Product>, Integer> productStreamPair = getBySellerId(sellerId);
    if (productStreamPair == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(productStreamPair.first.collect(Collectors.toList()));
  }


  // N конкретного юзера, начиная со start
  // также возвращаем общее количество товаров для этого юзера
  // Если товара нету, вместо него null
  @GetMapping(value = "/sellerId/{sellerId}/n/{start}/{n}")
  public ResponseEntity<Pair<List<Product>, Integer>> getNBySellerId(@PathVariable("sellerId") String sellerId,
                                                      @PathVariable("start") Integer start,
                                                      @PathVariable("n") Integer n) {
    Pair<Stream<Product>, Integer> productStreamPair = getBySellerId(sellerId);
    if (productStreamPair == null) {
      return ResponseEntity.notFound().build();
    }

    Pair<List<Product>, Integer> response = new Pair<>();
    response.first = productStreamPair.first
            .skip(start)
            .limit(n)
            .collect(Collectors.toList());
    response.second = productStreamPair.second;

    return ResponseEntity.ok(response);
  }


  // очищаем Storage
  @GetMapping("/delete/all")
  void deleteAll() {
    productStorage.deleteAll();
  }


  // N товаров, начиная со start
  // также возвращаем общее количество товаров для этого юзера
  @GetMapping("/all/n/{start}/{n}")
  public Pair<List<Product>, Integer> getN(@PathVariable("start") Integer start,
                   @PathVariable("n") Integer n) {
    Pair<List<Product>, Integer> response = new Pair<>();
    response.second = productStorage.size();
    response.first = productStorage.stream()
            .skip(start)
            .limit(n)
            .collect(Collectors.toList());
    return response;
  }

}
