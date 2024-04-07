package com.alibou.security.commandLine;

import com.alibou.security.command.Command;
import com.alibou.security.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {

    List<CommandLine> findByCommandId(Long cmdId);

}
