package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andreyko0 on 28/01/2018.
 */

@RestController
@RequestMapping("/search")
public class SearchController {

  @Autowired
  @Qualifier("products-db")
  private ProductDAO storage;

  @GetMapping("/name/{name}")
  public List<Product> searchName(@PathVariable("name") String name) {
    return storage.search(name);
  }

  @GetMapping("/suggest/name/{name}")
  public List<String> suggestNames(@PathVariable("name") String name) {
    return storage.suggestNames(name);
  }


}
