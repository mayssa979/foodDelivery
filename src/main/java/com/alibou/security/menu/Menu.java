package com.alibou.security.menu;


import com.alibou.security.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String img;
   // private int quantity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id" , referencedColumnName = "id")
    private Restaurant restaurant;



    @Override
    public String toString() {
        return "Menu{id=" + id + ", name='" + name + "', price=" + price + "}";
    }

}
