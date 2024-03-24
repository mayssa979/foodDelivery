package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import com.alibou.security.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;
    @Autowired
    private MenuService menuSer;

  /*  @PostMapping
    public ResponseEntity<?> save(
            @RequestBody RestaurantRequest request
    ) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }*/
    @PostMapping
    public String addRestaurant(@RequestBody Restaurant restaurant){
         service.save(restaurant);
         for(Menu menu : restaurant.getMenus()){
             menu.setRestaurant(restaurant);
             menuSer.saveMenu(menu);
         }
         return "restaurant added successufully!";
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAllRestaurantss() {
        return ResponseEntity.ok(service.findAll());
    }
}
