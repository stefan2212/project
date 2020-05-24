package com.tim.project.service;

import com.tim.project.model.*;
import com.tim.project.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        shop.getEmployeeList().forEach(employee -> employee.setShop(shop));
        shop.getProducts().forEach(product -> product.setShops(Collections.singleton(shop)));
        shopRepository.save(shop);
    }

    public Shop updateShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public void deleteShop(int id) {
        shopRepository.deleteById(id);
    }

    public void doHack() {
        Shop shop = new Shop();
        shop.setName("Lidl");
        shop.setType("Supermarket");

        Address address = new Address();
        address.setCity("Timisoara");
        address.setCountry("Romania");
        address.setStreet("Stefan cel Mare");

        Employee employee1 = new Employee();
        employee1.setFirstName("Gigel");
        employee1.setLastName("Fron");
        employee1.setEmployeePosition(EmployeePosition.MANAGER);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ion");
        employee2.setLastName("Tiriac");
        employee2.setEmployeePosition(EmployeePosition.MR_WASHY_WASHY);

        shop.setAddress(address);
        address.setShop(shop);

        shop.setEmployeeList(Arrays.asList(employee1, employee2));
        employee1.setShop(shop);
        employee2.setShop(shop);

        Product product = new Product();
        product.setName("Faina");
        product.setDescription("De grau");
        product.setPrice(5);

        Product product2 = new Product();
        product2.setName("Ulei");
        product2.setDescription("Unisol");
        product2.setPrice(6);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        productSet.add(product2);

        shop.setProducts(productSet);
        Set<Shop> shopSet = new HashSet<>();
        shopSet.add(shop);
        for(Product p: productSet) {
            p.setShops(Collections.singleton(shop));
        }

        shopRepository.save(shop);
    }
}
