package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@RestController
@RequestMapping("/pr")
public class ProductController {

  @Autowired
  private ProductDAO storage;
  private Date currentDate;
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void addProduct(@RequestBody Product p, HttpServletResponse resp) {
    currentDate = new Date();
    p.dateInit(currentDate.getTime());
    System.out.println(p.getSendableDate());
    currentDate = null;
    storage.addProduct(p);
    System.out.println("Got prd");
    resp.setStatus(HttpServletResponse.SC_OK);
    // some stupid testing changes
    // wtf
    // 44
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Product> getAll() {
    System.out.println("Responded with all products, and logged it, testing again");
    return storage.getAll();
  }

  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public Product getProduct(@PathVariable("id") String id) {
    return storage.getProduct(id);
  }

  @GetMapping("/delete/all")
  void deleteAll() {
    storage.deleteAll();
  }

  @GetMapping("/all/n/{start}/{n}")
  public NProductsResponse getN(@PathVariable("start") Integer start,
                            @PathVariable("n") Integer n) {
    NProductsResponse response = new NProductsResponse();
    response.max = storage.size();
    response.products = storage.stream()
            .skip(start)
            .limit(n)
            .collect(Collectors.toList());
    return response;
  }


  class NProductsResponse {
    public List<Product> products;
    public int max;
  }
  
}
