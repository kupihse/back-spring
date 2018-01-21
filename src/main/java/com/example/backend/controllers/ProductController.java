package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.storages.ProductStorage;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
  private ProductDAO storage;

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void addProduct(@RequestBody Product p, HttpServletResponse resp) {
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
}
