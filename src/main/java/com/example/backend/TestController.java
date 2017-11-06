package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Andreyko0 on 06/11/2017.
 */

@RestController
@RequestMapping("/test")
public class TestController {

  @RequestMapping("/{s}")
  public void getS(@PathVariable String s) throws SQLException{
    System.out.println(s);
    Connection c = src.getConnection();
    PreparedStatement stmt = c.prepareStatement("INSERT INTO test VALUES (?)");
    stmt.setString(1, s);
    stmt.execute();
  }

  @Autowired
  @Qualifier("dataSource")
  private DataSource src;
}
