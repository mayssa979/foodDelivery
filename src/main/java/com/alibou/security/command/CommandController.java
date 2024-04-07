package com.alibou.security.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alibou.security.commandLine.CommandLine;
import com.alibou.security.commandLine.CommandLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/commands")
@CrossOrigin(origins = "http://localhost:4200")
public class CommandController {
    @Autowired
    private CommandService commandService;
    @Autowired
    private CommandLineService cmdLineService;



    @PostMapping("/addCommand")
    public String addCommand(@RequestBody Command command) {
        commandService.saveCmd(command);
        double somme=0.0;
        for (CommandLine cmdLine : command.getCommandLines()) {
            cmdLine.setCommand(command);
            cmdLineService.saveCmdLine(cmdLine);
            somme+=cmdLine.getPrix();

        }
       // System.out.println("testttt"+ somme);
        command.setPrixCmd(somme);
        commandService.saveCmd(command);

        return "command added successufully!";
    }
    @GetMapping("/commandById/{id}")
    public Optional<Command> findCommandById(@PathVariable Long id){
        return commandService.getCommandById(id);
    }




}
