package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
public class Product {
  private String name;
  private String description;
  private String id;
  private int price;
  private ArrayList<String> images;
  private Set<String> tags;
  private String sellerId;

  @JsonIgnore
  private Date date;

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

  @JsonProperty("sendableDate")
  public long getSendableDate(){
    return date.getTime();
  }


  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
      this.date = date;
  }

  public void initDate() {
    this.setDate(new Date());
  }

  public void initId() {
    String id = UUID.randomUUID().toString();
    this.setId(id);
  }

  public void init() {
    initId();
    initDate();
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ArrayList<String> getImages() {
    return images;
  }

  public void setImages(ArrayList<String> images) {
    this.images = images;
  }

  public void setSellerId (String id) { sellerId = id; }

  public String getSellerId () { return sellerId; }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public void addTag(String s) {
    tags.add(s);
  }

}
