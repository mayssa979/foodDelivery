package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
