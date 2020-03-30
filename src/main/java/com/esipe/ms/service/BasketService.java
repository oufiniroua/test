package com.esipe.ms.service;

import com.esipe.ms.domain.Basket;
import com.esipe.ms.domain.Product;
import com.esipe.ms.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    public void add(Basket basket){
        System.out.println("add basket of : " + basket.getUser());

        basketRepository.save(basket);
    }

    public Basket getOne(String user){
        System.out.println("get basket by user mail : " + user);

        return basketRepository.getOne(user);
    }

    public List<Basket> getAll(){
        return basketRepository.getAll();
    }

    public void update(Basket basket){
        System.out.println("update basket with user mail: " + basket.getUser());

        basketRepository.update(basket);
    }

    public void delete(String user){
        System.out.println("delete basket with user mail : " + user);

        basketRepository.delete(user);
    }

    /*Fonction principale de gestion de produit dans un panier*/

   public void addProduct(Product product, String user){
        System.out.println("add product " +  product.getGtin() + "in basket of" + user);

        basketRepository.addProduct(product,user);
    }

    public void removeProduct(Product product, String user){
        System.out.println("remove product : " +  product.getGtin() + " from basket of " + user);

        basketRepository.removeProduct(product, user);
    }

}
