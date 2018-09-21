package com.example.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductInformationClient;
import com.example.service.ProductInventoryClient;

import javax.annotation.Resource;

@RestController
public class Aggregator {
  @Resource
  private ProductInformationClient informationClient;

  @Resource
  private ProductInventoryClient inventoryClient;

  @RequestMapping("/product")
  public Product getProduct() {
    Product product = new Product();
    product.setTitle(informationClient.getProductTitle());
    product.setProductInventories(inventoryClient.getProductInventories());
    return product;
  }
}
