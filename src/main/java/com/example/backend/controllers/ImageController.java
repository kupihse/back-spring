package com.example.backend.controllers;

import com.example.backend.models.Image;
import com.example.backend.storages.ImageDiskStorage;
import com.example.backend.storages.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
@RestController
@RequestMapping("/images")
public class ImageController {

  @Autowired
  private ImageDAO storage;

  @Autowired
  private ImageDiskStorage diskStorage;

  @PostMapping("/add")
  void addImage(@RequestBody Image img) {
    storage.saveImage(img.id, img.body);
  }

  @GetMapping("/get/{id}")
  Image getImage(@PathVariable("id") String id) {
    return storage.getImage(id);
  }

  @GetMapping("/delete/all")
  void deleteAll() {
    storage.deleteAll();
  }

  @PostMapping("/upload/{id}")
  void upload(@PathVariable("id") String id, HttpServletRequest request) throws IOException {
    diskStorage.saveImage(id, request.getInputStream());
  }

  @GetMapping(
          value = "/download/{id}",
  produces = MediaType.IMAGE_JPEG_VALUE)
  void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
    diskStorage.getImage(id, response.getOutputStream());
  }

}
