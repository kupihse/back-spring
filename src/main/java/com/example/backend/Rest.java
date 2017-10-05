package com.example.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andreyko0 on 05/10/2017.
 */
@RestController
public class Rest {

  @RequestMapping("/s")
  public Integer string() {
    return 3;
  }
}
