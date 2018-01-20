package com.example.backend.models;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
public class Product {
  private String name;
  private String description;
  private String id;

  public Product(String name) {
    this.name = name;
  }

  public Product(String name, String desctiption) {
    this.name = name;
    this.description = desctiption;
  }
  public Product(String id, String name, String desctiption) {
    this.id = id;
    this.name = name;
    this.description = desctiption;
  }
  public Product() {
    this.id = "def";
    this.name = "def";
    this.description = "def";
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
