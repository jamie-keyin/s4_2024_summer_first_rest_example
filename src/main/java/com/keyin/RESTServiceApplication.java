// RESTServiceApplication Mohamed
package com.keyin;

import com.keyin.hello.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RESTServiceApplication implements CommandLineRunner {

    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) {
        SpringApplication.run(RESTServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        greetingService.initializeGreetings();
        greetingService.getAllGreetings().forEach(greeting -> {
            System.out.println("Greeting: " + greeting.getGreeting());
            System.out.println("Languages: ");
            greeting.getLanguages().forEach(language -> System.out.println(language.getName()));
        });
    }
}

