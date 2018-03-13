package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.models.User;
import com.example.backend.storages.dao.ProductDAO;
import com.example.backend.storages.dao.UserDAO;
import com.example.backend.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@RestController
@RequestMapping("/pr")
public class ProductController {

  @Autowired
  @Qualifier("products-db")
  private ProductDAO productStorage;

  @Autowired
  private UserDAO userStorage;


  // Генерируем id товара и ставим ему дату в init()
  // Добавляем товар в Storage и добавляем юзеру id товара
  @PostMapping(value = "/new")
  @ResponseBody
  public void addProduct(@RequestBody Product p, HttpServletResponse resp) {

    p.init();
    System.out.println(p.getDateLong());

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


  // Все товары конкретного юзера
  @GetMapping(value = "/sellerId/{sellerId}/all")
  public List<Product> getAllBySellerId(@PathVariable("sellerId") String sellerId) {
    return productStorage.getProductsBySellerId(sellerId);
  }


  // N конкретного юзера, начиная со start
  // также возвращаем общее количество товаров для этого юзера
  // Если товара нету, вместо него null
  @GetMapping(value = "/sellerId/{sellerId}/n/{start}/{n}")
  public Pair<List<Product>, Integer> getNBySellerId(@PathVariable("sellerId") String sellerId,
                                                     @PathVariable("start") Integer start,
                                                     @PathVariable("n") Integer n) {

    return new Pair<>(
            productStorage.getNBySellerId(sellerId, start, n),
            productStorage.size()
    );
  }

  @DeleteMapping("/delete/id/{id}")
  void deleteById(@PathVariable("id") String id) {
    productStorage.deleteProduct(id);
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

    return new Pair<>(
            productStorage.getN(start, n),
            productStorage.size()
    );
  }

}
