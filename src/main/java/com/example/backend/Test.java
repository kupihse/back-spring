package com.example.backend;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andreyko0 on 06/11/2017.
 */
@Entity
@Table(name = "test")
public class Test {
  private String s;

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }
}
