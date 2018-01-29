package com.example.backend.controllers.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by Andreyko0 on 06/11/2017.
 */

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private JdbcTemplate template;

  @RequestMapping("/{s}")
  public void getS(@PathVariable String s) throws SQLException{
    System.out.println(":s " + s);
    template.update("INSERT INTO test VALUES (?)", s);
  }
}
