package com.alibou.security.command;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.alibou.security.commandLine.CommandLine;
import com.alibou.security.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "commands")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int status;
    private double prixCmd;
    @JsonProperty
    @OneToMany(mappedBy="command", cascade = CascadeType.MERGE)
    private List<CommandLine> commandLines;


    @ManyToOne
    @JoinColumn(name = "client_id" , referencedColumnName = "id")
    private User user ;


    public Command(Date date) {
        super();
        this.date = date;
    }



}
