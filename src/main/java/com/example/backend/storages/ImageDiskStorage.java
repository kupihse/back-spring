package com.example.backend.storages;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andreyko0 on 25/01/2018.
 */
@Repository
public class ImageDiskStorage {
  final private String IMG_PATH = "/root/imgs/";

  private File getFile(String id) {
    return new File(IMG_PATH+id+".jpg");
  }

  public void saveImage(String id, ServletInputStream s) {
    try {
      File f = getFile(id);
      if (!f.createNewFile()) {
        System.out.println("Couldn;t create file: "+id);
        return;
      }
      FileOutputStream stream = new FileOutputStream(f);
      IOUtils.copy(s, stream);
    } catch (IOException e) {
      System.out.println("File not found ex");
    }


  }

  public void getImage(String id, ServletOutputStream s) {
    File f = getFile(id);
    if (!f.exists()) {
      System.out.println("File doesn't exist: "+id);
      return;
    }
    try {
      FileInputStream stream = new FileInputStream(f);
      IOUtils.copy(stream, s);
    } catch (IOException e) {
      System.out.println("Could get image: "+id);
    }

  }
}
