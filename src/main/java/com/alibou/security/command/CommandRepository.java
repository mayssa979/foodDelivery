package com.alibou.security.command;

import java.util.List;
import java.util.Optional;

import com.alibou.security.menu.Menu;
import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{
    List<Command> findByUserId(int userId);

}
