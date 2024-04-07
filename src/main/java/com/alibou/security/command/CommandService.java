package com.alibou.security.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.alibou.security.commandLine.CommandLine;
import com.alibou.security.commandLine.CommandLineRepository;
import com.alibou.security.commandLine.CommandLineService;
import com.alibou.security.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandService {
    @Autowired
    private CommandRepository cmdRepository;
    @Autowired
    private CommandLineRepository cmdLineRepository;
    @Autowired
    private CommandLineService cmdLineServ;



    public CommandService(CommandRepository cmdRepository, CommandLineRepository cmdLineRepository) {
        super();
        this.cmdRepository = cmdRepository;
        this.cmdLineRepository = cmdLineRepository;
    }

    public Command saveCmd(Command command){
        Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormatee = formater.format(aujourdhui);
        Date date;
        try {
            date = formater.parse(dateFormatee);
            command.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
			/*double total = 0.0 ;
			for(CommandLine cmdLine : cmd.getCommandLines()) {
				total += cmdLine.getPrix();
			}
			command.setPrixCmd(total);*/
        return cmdRepository.save(command);
    }

    public Optional<Command> getCommandById(Long id) {
        return cmdRepository.findById(id);
    }

    public double findCmdLines(Long id) {
        double somme = 0.0;

        for(CommandLine cmdLine : cmdLineServ.getCommandLines()) {
            if (cmdLine.getCommand().getId()== id) {
                somme+= cmdLine.getPrix();
            }
        }
        return somme;
    }
    public String deleteCommand(Long id) {
        Optional<Command> optionalCommand = cmdRepository.findById(id);
        if ( optionalCommand.isPresent()) {
            List<CommandLine> lines = cmdLineRepository.findByCommandId(optionalCommand.get().getId());
            for(CommandLine line : lines){
                cmdLineRepository.deleteById(line.getId());
            }
            cmdRepository.deleteById(id);
            return "Deleted command with ID:"+id;
        }
        else {
            return "Command with ID: "+id+" does not exist!!";
        }
    }



}
