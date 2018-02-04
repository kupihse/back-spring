package com.example.backend.storages;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
      System.out.println("Saving to: "+f.getAbsolutePath());
      if (!f.createNewFile()) {
        System.out.println("Couldn;t create file: "+id);
        return;
      }
      FileOutputStream stream = new FileOutputStream(f);
      IOUtils.copy(s, stream);
      stream.flush();
      stream.close();
      System.out.println("Saved: "+f.getAbsolutePath());
    } catch (IOException e) {
      System.out.println("File not found ex");
    }


  }

  public void getImage(String id, ServletOutputStream s) {
    File f = getFile(id);
    System.out.println("Getting from: "+f.getAbsolutePath());
    if (!f.exists()) {
      System.out.println("File doesn't exist: "+id);
      return;
    }
    try {
      FileInputStream stream = new FileInputStream(f);
      IOUtils.copy(stream, s);
      s.flush();
      s.close();
      System.out.println("Got: "+f.getAbsolutePath());
    } catch (IOException e) {
      System.out.println("Could get image: "+id);
    }

  }
}
