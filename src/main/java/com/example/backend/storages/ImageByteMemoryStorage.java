package com.example.backend.storages;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
@Repository
public class ImageByteMemoryStorage {
  private Map<String, byte[]> storage = new HashMap<>();

  public void saveImage(String id, byte[] image) {
    storage.put(id,image);
  }

  public byte[] getImage(String id) {
    byte[] data = storage.get(id);
//    ByteBuffer.allocate(10)
    return data;
  }

  public void deleteAll() {
    storage.clear();
  }
}
