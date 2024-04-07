package com.alibou.security.menu;

import com.alibou.security.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;



    public String saveMenu(Menu menu) {

        repository.save(menu);
        return "Menu added successufully!";
    }




    public List<Menu> saveMenus(List<Menu> menus) {
        return repository.saveAll(menus);
    }


    public List<Menu> getMenus() {
        return repository.findAll();
    }


    public Optional<Menu> getMenuById(int id) {
        return repository.findById(id);
    }


    public Optional<Menu> getMenuByName(String name) {
        return repository.findByName(name);
    }


    public String deleteMenu(int id) {
        Optional<Menu> optionalMenu = repository.findById(id);
        if ( optionalMenu.isPresent()) {
            repository.deleteById(id);
            return "Deleted menu with ID:"+id;
        }
        else {
            return "Menu with ID: "+id+" does not exist!!";
        }
    }
public List<Menu> findMenusByRestaurantId(int restaurantId) {
    return repository.findByRestaurantId(restaurantId);
}

    public String updateMenu(Menu menu) {
        Menu existingMenu = repository.findById(menu.getId()).orElse(null);
        existingMenu.setName(menu.getName());
        existingMenu.setPrice(menu.getPrice());
        existingMenu.setImgUrl(menu.getImgUrl());
        repository.save(existingMenu);
            return "Menu updated ! ";
        }


}
