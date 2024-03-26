package com.alibou.security.commandLine;

import java.util.List;
import java.util.Optional;

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
        Double prixcmd = price*quantity;
        commandLine.setPrix(prixcmd);
        return cmdLineRepository.save(commandLine);
    }


    public List<CommandLine> getCommandLines() {
        return cmdLineRepository.findAll();
    }

}
