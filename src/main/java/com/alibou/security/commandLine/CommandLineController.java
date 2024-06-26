package com.alibou.security.commandLine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/commandLine")
@CrossOrigin(origins = "http://localhost:4200")
public class CommandLineController {


    @Autowired
    private CommandLineService service;


    @GetMapping
    public List<CommandLine> findAllCommandLines(){
        return service.getCommandLines();
    }

}
