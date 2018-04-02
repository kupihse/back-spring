package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andreyko0 on 20/01/2018.
 */


// Надо хранить у каждого товара айди главной картинки, чтоб не запрашивать каждый раз все картинки сразу


@Repository("products-db")
public class ProductStorage implements ProductDAO {

  @Autowired
  private JdbcTemplate template;

  @Override
  public void addProduct(Product p) {
    template.update("INSERT INTO Products" +
                    " (prod_id, title, description, price, seller_id, add_date)" +
                    " VALUES (?,?,?,?,?,?)", p.getId(), p.getName(),
            p.getDescription(), p.getPrice(), p.getSellerId(), p.getDate());
    if (p.getImages() == null) {
      return;
    }
    List<Object[]> data = p.getImages().stream()
            .map((id) -> new Object[]{id, p.getId()})
            .collect(Collectors.toList());
    template.batchUpdate("INSERT INTO Product_photo" +
            " (photo_id, product_id)" +
            " VALUES (?,?)", data);
  }

  @Override
  public Product getProduct(String id) {
    // Хз работает ли
    System.out.println("Log/d: getting product, id - "+id);
    Product product = template.queryForObject("Select * from Products where prod_id=?", Product.class, id);
    System.out.println("Log/d: name - "+product.getName());
    List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, id);
    System.out.println("Log/d: size - "+im_ids.size());
    if (im_ids.size() != 0) {
      System.out.println("Log/d: img[0] - "+im_ids.get(0));
    }
    product.setImages(im_ids);
    return product;
  }

  @Override
  public List<Product> getProductsBySellerId(String sellerId) {
    List<Product> products = template.query("Select * from Products WHERE seller_id = ?",
            (rs, rowNum) -> rowToProduct(rs),
            sellerId);
    for (Product p : products) {
      List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, p.getId());
      p.setImages(im_ids);
    }
    return products;
  }

  @Override
  public List<Product> getNBySellerId(String sellerId, int start, int n) {
    List<Product> products = template.query("Select * from Products WHERE seller_id = ?" +
                    " LIMIT ? OFFSET ?",
            (rs, rowNum) -> rowToProduct(rs),
            sellerId, n, start);
    for (Product p : products) {
      List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, p.getId());
      p.setImages(im_ids);
    }
    return products;
  }

  @Override
  public List<Product> getAll() {
    List<Product> products = template.query("Select * from Products", (rs, rowNum) -> rowToProduct(rs));
    for (Product p : products) {
      List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, p.getId());
      p.setImages(im_ids);
    }
    return products;
  }


  // Надо поменять
  @Override
  public List<Product> getN(int start, int n) {
    List<Product> products = template.query("Select * from Products" +
                    " LIMIT ? OFFSET ?", (rs, rowNum) -> rowToProduct(rs),
            n, start);
    for (Product p : products) {
      List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, p.getId());
      p.setImages(im_ids);
    }
    return products;
  }

  @Override
  public void deleteAll() {
    template.update("DELETE FROM Products");
  }

  @Override
  public boolean deleteProduct(String id) {
    int rows = template.update("DELETE FROM Products where prod_id = ?", id);
    return rows > 0;
  }

  @Override
  public int size() {
    return template.queryForObject("SELECT COUNT(*) FROM Products", Integer.class);
  }


  private static Product rowToProduct(ResultSet rs) throws SQLException {
    Product product = new Product();
    product.setId(rs.getString("prod_id"));
    product.setName(rs.getString("title"));
    product.setDescription(rs.getString("description"));
    product.setPrice(rs.getInt("price"));
    product.setDate(rs.getDate("add_date"));
    // buy_date пропущено пока что
    product.setSellerId(rs.getString("seller_id"));
    // buyer_id не нужно по идее
    // по-другому надо как-то сделать,не один же покупатель будет

    return product;
  }


  @Override
  public List<Product> search(String query) {
    List<Product> products = template.query("Select * from Products" +
                    " where title like ?",
            (rs, rowNum) -> rowToProduct(rs),
            "%" + query.toLowerCase() + "%");
    for (Product p : products) {
      List<String> im_ids = template.queryForList("Select photo_id from Product_photo where product_id = ?", String.class, p.getId());
      p.setImages(im_ids);
    }
    return products;
  }

  @Override
  public List<String> suggestNames(String query) {
    return template.queryForList("Select * from Products" +
                    " where title like ?",
            String.class,
            "%" + query.toLowerCase() + "%");
  }
}