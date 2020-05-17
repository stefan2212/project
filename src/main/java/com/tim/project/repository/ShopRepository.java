package com.tim.project.repository;

import com.tim.project.model.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
    List<Shop> findAllByName(String name);

    @Query("Select shop from Shop shop where shop.type = ?1")
    List<Shop> findByType(String type);
}
