package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
  private ProductDAO storage;

  @GetMapping("/name/{name}")
  public List<Product> searchName(@PathVariable("name") String name) {
    return storage.stream()
            .filter(p -> p.getName().contains(name))
            .collect(Collectors.toList());
  }

  @GetMapping("/desc/{desc}")
  public List<Product> searchDesc(@PathVariable("desc") String desc) {
    return storage.stream()
            .filter(p -> p.getDescription().contains(desc))
            .collect(Collectors.toList());
  }

}
