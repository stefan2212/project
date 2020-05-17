package com.tim.project.service;

import com.tim.project.model.Shop;
import com.tim.project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> getAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }

    public Shop getShopById(int id) {
        return shopRepository.findById(id).orElse(new Shop());
    }

    public List<Shop> getShopsByName(String name) {
        return shopRepository.findAllByName(name);
    }

    public List<Shop> getShopsByType(String type) {
        return shopRepository.findByType(type);
    }

    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    public Shop updateShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public void deleteShop(int id) {
        shopRepository.deleteById(id);
    }
}
