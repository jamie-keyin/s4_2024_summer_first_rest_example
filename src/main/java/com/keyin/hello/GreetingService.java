package com.keyin.hello;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GreetingService {
    public Greeting generateGreeting(String name) {
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello");

        if (StringUtils.hasText(name)) {
            greeting.setName(name);
        } else {
            greeting.setName("World");
        }

        return greeting;
    }

}
