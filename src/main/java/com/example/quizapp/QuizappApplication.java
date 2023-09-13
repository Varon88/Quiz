package com.example.quizapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Version;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "QuizApi", version = "0.0.1"),
		servers = {@Server(url = "http://localhost:8080") /* can add more */},
		tags = { @Tag( name = "Question operations", description = "implements question operations"), @Tag(name = "Quiz operations", description = "implements quiz operations")} // this line would have a common description for all tags named Question operation
)
public class QuizappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);
	}

}
