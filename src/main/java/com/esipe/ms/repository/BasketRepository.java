package com.esipe.ms.repository;

import com.esipe.ms.domain.Basket;
import com.esipe.ms.domain.Product;
import com.esipe.ms.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BasketRepository {

    /*CRUD*/
    private Map<String, Basket> baskets = new HashMap<>();

    public void save(Basket basket){
        baskets.put(basket.getUser(), basket);
    }

    public void update(Basket basket){
        if(!ifExiste(basket.getUser())){
            throw new RuntimeException("panier vide");
        }
        baskets.put(basket.getUser(), basket);
    }

    public Basket getOne(String user){
        return baskets.get(user); }

    public void delete(String user){
        if(!ifExiste(user)){
            throw new RuntimeException("panier not found");
        }
        baskets.remove(user);
    }

    private boolean ifExiste(String user){
        return baskets.containsKey(user);
    }

    public List<Basket> getAll() {
        return new ArrayList<Basket>(baskets.values());
    }

    /* fonction principale de gestion de panier */

    public void addProduct(Product item, String user){
        int lastprice,newprice;
        if(!ifExiste(user)){
            throw new RuntimeException("panier vide");
        }
        baskets.get(user).getItems().add(item);
        lastprice = baskets.get(user).getTotalAmount() ;
        newprice = lastprice + (int)item.getUnitPrice()*item.getQuantity();
        baskets.get(user).setTotalAmount(newprice);
    }

    public void removeProduct(Product item, String user){
    int lastprice,newprice;
    if(!ifExiste(user)){
        throw new RuntimeException("panier vide");
    }
    lastprice = baskets.get(user).getTotalAmount() ;
    newprice = lastprice - (int)item.getUnitPrice()*item.getQuantity();
    baskets.get(user).setTotalAmount(newprice);

    if(baskets.get(user).getItems().contains(item)) {
        int index = baskets.get(user).getItems().indexOf(item);
        baskets.get(user).getItems().remove(index);
    }



    }

}
