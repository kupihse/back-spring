package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * Created by Andreyko0 on 06/11/2017.
 */

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  DataSource src;
  @RequestMapping("/{s}")
  public void getS(@PathVariable String s) {
    System.out.println(s);
  }
}
