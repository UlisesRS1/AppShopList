package com.shop.appshoplist.data.repository;

import com.shop.appshoplist.data.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryProductRepository implements IProductRepository{
    private static final List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("Doritos, 120g", 13.0));
        products.add(new Product("Leche, 1L", 26.30));
        products.add(new Product("Queso Oaxaca, 140g", 24.0));
        products.add(new Product("Perro muerto, 180g", 34.99));
    };

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    @Override
    public boolean modifyProduct(int index, Product product) {
        if (index < 0 || index > (products.size() - 1)) {
            return false;
        }

        products.set(index, product);

        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return products.remove(product);
    }
}
