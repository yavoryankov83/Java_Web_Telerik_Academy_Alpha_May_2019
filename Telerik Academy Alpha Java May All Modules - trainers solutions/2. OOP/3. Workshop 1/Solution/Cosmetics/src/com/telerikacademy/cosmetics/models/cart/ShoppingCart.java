package com.telerikacademy.cosmetics.models.cart;

import com.telerikacademy.cosmetics.models.common.ValidationHelper;
import com.telerikacademy.cosmetics.models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private ArrayList<Product> productList;

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void addProduct(Product product) {
        ValidationHelper.checkProductNotNull(product);
        productList.add(product);
    }

    public void removeProduct(Product product) {
        ValidationHelper.checkProductNotNull(product);
        productList.remove(product);
    }

    public boolean containsProduct(Product product) {
        ValidationHelper.checkProductNotNull(product);
        return productList.contains(product);
    }

    public double totalPrice() {
        double result = 0;
        for (Product product : productList) {
            result += product.getPrice();
        }
        return result;
    }
}
