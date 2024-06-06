package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("hello")
    public Greeting greeting(@RequestParam(value = "name", required = false) String name) {
        return greetingService.generateGreeting(name);
    }
}
