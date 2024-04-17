package com.alibou.security.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.alibou.security.command.Command;

@AllArgsConstructor
@RequiredArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "command_id")
    private Command command;

    private double longitude ;
    private double latitude;

}
