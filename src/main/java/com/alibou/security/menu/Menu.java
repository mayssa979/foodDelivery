package com.alibou.security.menu;


import com.alibou.security.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private double price;
    private String imgUrl;
   // private int quantity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id" , referencedColumnName = "id")
    private Restaurant restaurant;

}
