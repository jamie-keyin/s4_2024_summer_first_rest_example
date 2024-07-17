package com.keyin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private List<Greeting> greetings = new ArrayList<>();

    public GreetingController() {
        greetings.add(new Greeting("English", "Hello"));
        greetings.add(new Greeting("Spanish", "Hola"));
        // Add more initial greetings if necessary
    }

    @PutMapping("/greetings")
    public ResponseEntity<String> addGreeting(@RequestBody Greeting newGreeting) {
        for (Greeting greeting : greetings) {
            if (greeting.getLanguage().equalsIgnoreCase(newGreeting.getLanguage())) {
                return new ResponseEntity<>("Language already exists", HttpStatus.BAD_REQUEST);
            }
        }
        greetings.add(newGreeting);
        return new ResponseEntity<>("Greeting added", HttpStatus.CREATED);
    }

    // Greeting class
    public static class Greeting {
        private String language;
        private String greeting;

        public Greeting() {
        }

        public Greeting(String language, String greeting) {
            this.language = language;
            this.greeting = greeting;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getGreeting() {
            return greeting;
        }

        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }
    }
}

