package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RestaurantRequest {

    private Integer id;
    private String place;
    private String name;
    private List<Menu> menus ;
}
