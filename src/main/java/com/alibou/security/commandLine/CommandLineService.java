package com.alibou.security.commandLine;

import java.util.List;
import java.util.Optional;

import com.alibou.security.command.Command;
import com.alibou.security.menu.Menu;
import com.alibou.security.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandLineService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private CommandLineRepository cmdLineRepository;


    public CommandLineService(MenuRepository menuRepository, CommandLineRepository cmdLineRepository) {
        super();
        this.menuRepository = menuRepository;
        this.cmdLineRepository = cmdLineRepository;
    }


    public CommandLine saveCmdLine(CommandLine commandLine) {
        int menuid = commandLine.getMenu().getId();
        Menu menu = menuRepository.getById(menuid);
        double price = menu.getPrice();
        int quantity = commandLine.getQuantity();
        Double prixcmd = price * quantity;
        commandLine.setPrix(prixcmd);
        return cmdLineRepository.save(commandLine);
    }


    public List<CommandLine> getCommandLines() {
        return cmdLineRepository.findAll();
    }

    public String deleteCommandLine(Long id) {
        Optional<CommandLine> optionalCommandLine = cmdLineRepository.findById(id);
        if (optionalCommandLine.isPresent()) {
            cmdLineRepository.deleteById(id);
            return "Deleted command line  with ID:" + id;
        } else {
            return "Command Line with ID: " + id + " does not exist!!";
        }

    }
}
