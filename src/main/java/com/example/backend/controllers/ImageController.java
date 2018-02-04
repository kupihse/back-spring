package com.example.backend.controllers;

import com.example.backend.models.Image;
import com.example.backend.storages.ImageByteMemoryStorage;
import com.example.backend.storages.dao.ImageDAO;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
  private ImageByteMemoryStorage byteMemoryStorage;

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

  @PostMapping("/images/upload/{id}")
  void upload(@PathVariable("id") String id, HttpServletRequest request) {
    ByteOutputStream stream = new ByteOutputStream();
    try {
      IOUtils.copy(request.getInputStream(), stream);
    } catch (IOException e) {
      System.out.println("Couldn;t upload: "+e.getMessage());
      return;
    }
    byteMemoryStorage.saveImage(id, stream.getBytes());
  }

  @GetMapping("/images/download/{id}")
  void download(@PathVariable("id") String id, HttpServletResponse response) {
    byte[] bytes = byteMemoryStorage.getImage(id);
    ByteInputStream stream = new ByteInputStream();
    try {
      stream.read(bytes);
//      response.getOutputStream()
      IOUtils.copy(stream, response.getOutputStream());
    } catch (IOException e) {
      System.out.println("Couldn;t download: "+e.getMessage());
    }
  }

}
