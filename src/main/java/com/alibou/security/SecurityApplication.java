package com.alibou.security;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.*;
import java.util.Objects;

import static com.alibou.security.user.Role.ADMIN;
import static com.alibou.security.user.Role.CLIENT;
@SpringBootApplication
@EnableJpaAuditing
public class SecurityApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SecurityApplication.class, args);
	}
}
