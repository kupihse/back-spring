package com.example.backend;

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

  private JdbcTemplate jdbc;

  @Autowired
  TestController(JdbcTemplate t) {
    this.jdbc = t;
  }

  @RequestMapping("/{s}")
  public void getS(@PathVariable String s) throws SQLException{
    System.out.println(s);
//    Connection c = src.getConnection();
//    PreparedStatement stmt = c.prepareStatement("INSERT INTO test VALUES (?)");
//    stmt.setString(1, s);
//    stmt.execute();
    jdbc.update("INSERT INTO test VALUES (?)", s);
  }
}
