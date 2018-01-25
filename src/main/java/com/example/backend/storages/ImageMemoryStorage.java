package com.example.backend.storages;

import com.example.backend.models.Image;
import com.example.backend.storages.dao.ImageDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
@Repository
public class ImageMemoryStorage implements ImageDAO {
  private Map<String, String> storage = new HashMap<>();
  @Override
  public void saveImage(String id, String image) {
    storage.put(id,image);
  }

  public Image getImage(String id) {
    String data = storage.get(id);
    Image img = new Image();
    img.id = id;
    img.body = data;
    return img;
  }
}
