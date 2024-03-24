package com.alibou.security.restaurant;

import com.alibou.security.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository, MenuRepository menuRepository) {
        super();
        this.repository = repository;
        this.menuRepository = menuRepository;
    }
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }
}
