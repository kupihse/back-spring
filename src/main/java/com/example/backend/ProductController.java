package com.example.backend;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Andreyko0 on 13/10/2017.
 */
@RestController
@RequestMapping("/pr")
public class ProductController {

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void addProduct(@RequestBody Product p, HttpServletResponse resp) {
    ProductStorage.addProduct(p);
    System.out.println("Got prd");
    resp.setStatus(HttpServletResponse.SC_OK);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public Map<String, Product> getAll() {
    return ProductStorage.storage;
  }
}
