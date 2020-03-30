package com.esipe.ms.resource;

import com.esipe.ms.domain.Basket;
import com.esipe.ms.domain.Product;
import com.esipe.ms.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "baskets", produces = {"application/json"})
public class BasketResource {
    private BasketService basketService;

    @Autowired
    public BasketResource (BasketService bs){
        basketService = bs;
    }

    @GetMapping("{user}")
    public Basket getOne(@PathVariable("user") String user){

        return basketService.getOne(user);
    }

    @GetMapping
    public List<Basket> getAll(){
        return basketService.getAll();
    }

    @PostMapping
    public void add(@RequestBody Basket basket){
        basketService.add(basket);
    }

    @PutMapping("{user}")
    public void update(@PathVariable("user") String user, @RequestBody Basket basket){
        if(!user.equals(basket.getUser())){
            throw new RuntimeException("update basket par email exception");
        }
        basketService.update(basket);
    }

    @DeleteMapping("{user}")
    public void delete(@PathVariable("user") String user){
        basketService.delete(user);
    }


    /*Fonction en plus */
   @PostMapping("addProd/{user}")
    public void addProduct(@PathVariable("user") String user,@RequestBody Product product){
        basketService.addProduct(product,user);
    }

    @PostMapping("deleteProd/{user}")
    public void deleteProduct(@PathVariable("user") String user,@RequestBody Product product){
        basketService.removeProduct(product,user);
    }

}
