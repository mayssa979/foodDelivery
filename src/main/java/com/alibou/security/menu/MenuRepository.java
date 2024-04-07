package com.alibou.security.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Optional<Menu> findByName(String name);
    List<Menu> findByRestaurantId(int restaurantId);
    //Menu findByRestauId(int id);
}
