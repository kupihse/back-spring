package com.example.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
/**
 * Created by Andreyko0 on 13/10/2017.
 */
public class Product {
  private String name;
  private String description;
  private String id;
  private int price;
  private Date currentDate;
  private ArrayList<String> images;
  private String sellerId;

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
  public Date getCurrentDate(){
    return currentDate; }

  public void dateInit(){
    Calendar myCal = Calendar.getInstance();
    currentDate = myCal.getTime();
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

}
