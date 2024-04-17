package com.alibou.security.menu;

import com.alibou.security.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
        existingMenu.setImg(menu.getImg());
        repository.save(existingMenu);
            return "Menu updated ! ";
        }
//////////////////////////////////////////////////////////

    public void saveMenuWithImage(MultipartFile file, Menu menu){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file!");
        }
        try {
            menu.setImg(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(menu);

    }
///////////////////////////////////////////////////////////////////

}
