package com.shop.appshoplist.data.model;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private boolean checked;
    private int quantity;

    public Product() {
        this.name = "No name";
        this.price = 0.0;
        this.checked = false;
        this.quantity = 1;
    }

    public Product(Product product) {
        this.name = product.name;
        this.price = product.price;
        this.checked = product.checked;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.checked = false;
        this.quantity = 1;
    }

    public Product(String name, double price, int cantidad) {
        this.name = name;
        this.price = price;
        this.checked = false;
        this.quantity = cantidad;
    }

    public Product(String name, double price, boolean checked) {
        this.name = name;
        this.price = price;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && checked == product.checked && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, checked);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", checked=" + checked +
                ", cantidad=" + quantity +
                '}';
    }
}
