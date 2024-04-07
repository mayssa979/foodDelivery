package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import com.alibou.security.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RestaurantController {
    @Autowired
    private final RestaurantService service;
    @Autowired
    private MenuService menuSer;


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
    public ResponseEntity<List<Restaurant>> findAllRestau() {
        System.out.println(service.findAll());
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/getRestaurant/{id}")
    public Restaurant findRestaurant(@PathVariable int id) {
        return service.findRestaurant(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        return service.deleteRestaurant(id);
    }

    @PutMapping("/update")
    public String updateMenu(@RequestBody Restaurant restau) {
        return service.updateRestau(restau);
    }
}
