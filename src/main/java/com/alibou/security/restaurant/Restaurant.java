package com.alibou.security.restaurant;

import com.alibou.security.menu.Menu;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {

    @Id
    @GeneratedValue
    private Integer id;
    private String place;
    private String name;
    private String logo;
    @OneToMany(mappedBy="restaurant", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"restaurant", "handler","hibernateLazyInitializer"}, allowSetters = true)
    private List<Menu> menus ;



}
