package com.alibou.security.commandLine;

import java.sql.Date;
import java.util.List;

import com.alibou.security.command.Command;
import com.alibou.security.menu.Menu;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "command_line")
public class CommandLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private double prix;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "cmd_id" , referencedColumnName = "id")
    private Command command ;

    @ManyToOne
    @JoinColumn(name = "menu_id" , referencedColumnName = "id")
    private Menu menu;

    public CommandLine(Menu menu) {
        super();
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "CommandLine{" +
                "id=" + id +
                "price="+ prix +
                '}';
    }




}
