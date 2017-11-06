package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Andreyko0 on 06/11/2017.
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

  @Value("${db.url}")
  private String db_url;
  @Value("${db.username}")
  private String db_username;
  @Value("${db.password}")
  private String db_password;
  @Value("${db.driver}")
  private String db_driver;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(db_driver);
    dataSource.setUrl(db_url);
    dataSource.setUsername(db_username);
    dataSource.setPassword(db_password);
    return dataSource;
  }
}
