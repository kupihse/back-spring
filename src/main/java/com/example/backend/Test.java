package com.example.backend;

import javax.persistence.*;

/**
 * Created by Andreyko0 on 06/11/2017.
 */
@Entity
@Table(name = "test2")
public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String s;

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }
}
