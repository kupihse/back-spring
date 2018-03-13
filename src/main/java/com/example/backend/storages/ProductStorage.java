package com.example.backend.storages;

import com.example.backend.models.Product;
import com.example.backend.storages.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andreyko0 on 20/01/2018.
 */

@Table(name = "Products")
@Repository("products-db")
public class ProductStorage implements ProductDAO {

  @Autowired
  private JdbcTemplate template;

  @Override
  public void addProduct(Product p) {
    template.update("INSERT INTO Products" +
                    " (prod_id, title, description, price, seller_id, add_date) " +
                    "VALUES (?,?,?,?,?,?)", p.getId(), p.getName(),
            p.getDescription(), p.getPrice(), p.getSellerId(), p.getDate());
  }

  @Override
  public Product getProduct(String id) {
    // Хз работает ли
    return template.queryForObject("Select * from Products where prod_id=?", Product.class, id);
  }

  @Override
  public List<Product> getProductsBySellerId(String sellerId) {
    return template.query("Select * from Products WHERE seller_id = ?",
            (rs, rowNum) -> rowToProduct(rs),
            sellerId);
  }

  @Override
  public List<Product> getNBySellerId(String sellerId, int start, int n) {
    return template.query("Select * from Products WHERE seller_id = ? " +
                    "LIMIT ? OFFSET ?",
            (rs, rowNum) -> rowToProduct(rs),
            sellerId, n, start);
  }

  @Override
  public List<Product> getAll() {
    return template.query("Select * from Products", (rs, rowNum) -> rowToProduct(rs));
  }

  @Override
  public List<Product> getN(int start, int n) {
    return template.query("Select * from Products " +
                    "LIMIT ? OFFSET ?", (rs, rowNum) -> rowToProduct(rs),
            n, start);
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
    return template.query("Select * from Products" +
                    "where title like ?",
            (rs, rowNum) -> rowToProduct(rs),
            "%" + query.toLowerCase() + "%");
  }

  @Override
  public List<String> suggestNames(String query) {
    return template.queryForList("Select * from Products" +
                    "where title like ?",
            String.class,
            "%" + query.toLowerCase() + "%");
  }
}