package com.kleberrhuan.controller;

import com.kleberrhuan.model.product.Product;
import com.kleberrhuan.persistence.Exceptions.JsonPersistException;
import com.kleberrhuan.persistence.JsonFileHandler;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InventoryController {
    private Map<Long, Product> products;
    private static final String JSON_FILE = "data/products.json";

    public InventoryController() {
        products = JsonFileHandler
                .loadDataFromFile(JSON_FILE, Long.class, Product.class);
    }
    
    public List<Product> getProducts(){
        return products
                .values()
                .stream()
                .toList();
    }
    
    public Optional<Product> getProduct(long id){
        return Optional.ofNullable(products.get(id));
    }
    
    public void addProduct(Product product){
        products.put(product.getId(), product);
        saveProducts();
    }
    
    public void saveProducts(){
        try {
            JsonFileHandler.saveFile(products, JSON_FILE);
        } catch (RuntimeException e){
            throw new JsonPersistException("Error Saving Products List");
        }
    }
    
    public Optional<Product> removeProduct(final long id) {
        return Optional.ofNullable(products.remove(id));
    }
    
}
