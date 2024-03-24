package com.alibou.security.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService service;

    @PostMapping("/addMenu")
    public Menu addMenu(@RequestBody Menu menu){
        return service.saveMenu(menu);
    }

    @PostMapping("/addMenus")
    public List<Menu> addMenus(@RequestBody List<Menu> menus){
        return service.saveMenus(menus);
    }

    @GetMapping
    public List<Menu> findAllProducts(){
        return service.getMenus();
    }

    @GetMapping("/menuById/{id}")
    public Optional<Menu> findMenuById(@PathVariable int id){
        return service.getMenuById(id);
    }

    @GetMapping("/menu/{name}")
    public Optional<Menu> findMenuByName(@PathVariable String name){
        return service.getMenuByName(name);
    }

    @PutMapping("/update")
    public String updateMenu(@RequestBody Menu menu) {
        return service.updateMenu(menu);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMenu(@PathVariable int id) {
        return service.deleteMenu(id);
    }

}
