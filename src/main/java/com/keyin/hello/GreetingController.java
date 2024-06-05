package com.keyin.hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GreetingController {
    @GetMapping("hello")
    public Greeting greeting(@RequestParam("name") String name) {
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello");
        greeting.setName(name);

        return greeting;
    }
}
