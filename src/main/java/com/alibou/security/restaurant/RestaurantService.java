package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import com.alibou.security.menu.MenuRepository;
import com.alibou.security.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private MenuService menuSer;

    public RestaurantService(RestaurantRepository repository, MenuRepository menuRepository) {
        super();
        this.repository = repository;
        this.menuRepository = menuRepository;
    }
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> findAllRestaurants() {
        return repository.findAll();
    }
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant findRestaurant(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteRestaurant(int id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);
        int restauid = optionalRestaurant.get().getId();

        if ( optionalRestaurant.isPresent()) {
            List<Menu> menus = menuRepository.findByRestaurantId(restauid);
            for(Menu menu : menus){
                menuRepository.deleteById(menu.getId());
            }
            repository.deleteById(id);
            return "Deleted restaurant with ID:"+id;
        }
        else {
            return "restaurant with ID: "+id+" does not exist!!";
        }
    }

    public String updateRestau(Restaurant restau) {
        Restaurant existingRestau = repository.findById(restau.getId()).orElse(null);
        existingRestau.setName(restau.getName());
        existingRestau.setPlace(restau.getPlace());
        existingRestau.setImage(restau.getImage());
        List<Menu> existingMenus = menuRepository.findByRestaurantId(existingRestau.getId());
        for (Menu menu : existingMenus) {
            for (Menu updatedMenu : restau.getMenus()) {
                if (menu.getId().equals(updatedMenu.getId())) {
                    menu.setName(updatedMenu.getName());
                    menu.setPrice(updatedMenu.getPrice());
                    menu.setImg(updatedMenu.getImg());
                    menuRepository.save(menu); // Save each updated menu
                }
            }
        }
        repository.save(existingRestau);
        return "Restaurant updated ! ";
    }


    public List<Menu> findMenusByRestaurantId(int id) {
        Restaurant existingRestau = repository.findById(id).orElse(null);
        return existingRestau.getMenus();
    }

    public Location findLocation(int id) {
        Restaurant existingRestau = repository.findById(id).orElse(null);
        Location loc =new Location(existingRestau.getLongitude(), existingRestau.getLatitude());
        return loc;
    }

    ///////////////////////////////////////////////////////////
    public void saveRestaurantWithImage(MultipartFile file, Restaurant restaurant){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file!");
        }
        try {
            restaurant.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(restaurant);

    }
    //////////////////////////////////////
    public void saveRestaurantWithImageAndMenus(MultipartFile restaurantImage, Restaurant restaurant, List<MultipartFile> menuImages, List<Menu> menus) {
        try {
            // Save restaurant image
            restaurant.setImage(Base64.getEncoder().encodeToString(restaurantImage.getBytes()));

            // Save restaurant
            Restaurant savedRestaurant = repository.save(restaurant);

            // Save menus with images
            for (int i = 0; i < menuImages.size(); i++) {
                MultipartFile menuImage = menuImages.get(i);
                Menu menu = menus.get(i);
                menu.setImg(Base64.getEncoder().encodeToString(menuImage.getBytes()));
                menu.setRestaurant(savedRestaurant);
                menuRepository.save(menu);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving images: " + e.getMessage());
        }
    }
}
