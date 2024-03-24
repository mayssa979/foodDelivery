package com.alibou.security.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MenuRequest {
    private Integer id;
    private String place;
    private double price;
    private String imgUrl ;
}
