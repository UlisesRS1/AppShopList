package com.shop.appshoplist.data.repository;

import com.shop.appshoplist.data.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean modifyProduct(int index, Product product);
    boolean deleteProduct(Product product);
}
