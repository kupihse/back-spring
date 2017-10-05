package com.example.backend;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andreyko0 on 05/10/2017.
 */
@RestController
@RequestMapping("/api")
public class Rest {

  public class Data {
    int x;
    String y;

    public String getZsh() {
      return y;
    }
    public Integer zshx() {
      return x;
    }

    public Integer get() {
      return x;
    }
  }

  @RequestMapping("/s")
  public Integer string() {
    return 3;
  }

  @RequestMapping("/data/{d}.{i}")
  public Data data(@PathVariable String d, @PathVariable String i) {
    Data x = new Data();
    x.x = Integer.valueOf(i);
    x.y = d;
    return x;
  }

}
