package com.example.backend.controllers;

import com.example.backend.models.Image;
import com.example.backend.storages.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
@RestController
@RequestMapping("/images")
public class ImageController {

  @Autowired
  private ImageDAO storage;

  @PostMapping("/add")
  void addImage(@RequestBody Image img) {
    storage.saveImage(img.id, img.body);
  }

  @GetMapping("/get/{id}")
  Image getImage(@PathVariable("id") String id) {
    return storage.getImage(id);
  }
}
