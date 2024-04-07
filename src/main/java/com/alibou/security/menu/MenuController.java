package com.alibou.security.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {
    @Autowired
    private MenuService service;

    @PostMapping("/addMenu")
    public String addMenu(@RequestBody Menu menu){
        return service.saveMenu(menu);
    }

    @PostMapping("/addMenus")
    public List<Menu> addMenus(@RequestBody List<Menu> menus){
        return service.saveMenus(menus);
    }

    @GetMapping("/get")
    public List<Menu> findAllMenus(){
        return service.getMenus();
    }

    @GetMapping("/menuById/{id}")
    public Optional<Menu> findMenuById(@PathVariable int id){
        return service.getMenuById(id);
    }

    @GetMapping("/{name}")
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


    @PostMapping(value="/add/menu",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveWithImg(@RequestPart("menu") Menu menu,
                              @RequestPart("file") MultipartFile file){
        service.saveMenuWithImage(file, menu);
        return "Menu saved with success !";
    }
}
