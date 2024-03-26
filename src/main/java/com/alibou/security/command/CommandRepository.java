package com.alibou.security.command;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

}
