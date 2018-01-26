package com.example.backend.storages.dao;

import com.example.backend.models.Image;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
public interface ImageDAO {
  void saveImage(String id, String image);
  Image getImage(String id);
  void deleteAll();
}
