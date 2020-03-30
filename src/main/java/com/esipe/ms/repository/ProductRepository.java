package com.esipe.ms.repository;

import com.esipe.ms.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRepository {
    private Map<String, Product> products = new HashMap<>();

    public void save(Product product){
        products.put(product.getGtin(), product);
    }

    public void update(Product product){
        if(!ifExiste(product.getGtin())){
            throw new RuntimeException("product not found");
        }
        products.put(product.getGtin(), product);
    }

    public Product getOne(String gtin){
        return products.get(gtin);
    }

    public void delete(String gtin){
        if(!ifExiste(gtin)){
            throw new RuntimeException("product not found");
        }
        products.remove(gtin);
    }

    private boolean ifExiste(String gtin){
        return products.containsKey(gtin);
    }

    public List<Product> getAll() {
        return new ArrayList<Product>(products.values());
    }





}

