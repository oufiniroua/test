package com.esipe.ms.service;


import com.esipe.ms.domain.Product;
import com.esipe.ms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void add(Product product){
        System.out.println("add product : " + product.getGtin());

        productRepository.save(product);
    }

    public Product getOne(String gtin){
        System.out.println("get product by gtin : " + gtin);

        return productRepository.getOne(gtin);
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public void update(Product product){
        System.out.println("update product with gtin : " + product.getGtin());

        productRepository.update(product);
    }

    public void delete(String gtin){
        System.out.println("delete product with gtin : " + gtin);

        productRepository.delete(gtin);
    }

}
