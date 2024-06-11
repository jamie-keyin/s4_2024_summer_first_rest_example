package com.keyin.hello;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GreetingService {
    private Map<Integer, Greeting> greetingMap = new HashMap<Integer, Greeting>();

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

    public Greeting getGreeting(Integer index) {
        return greetingMap.get(index);
    }

    public Greeting createGreeting(Greeting newGreeting) {
        greetingMap.put(greetingMap.size() + 1, newGreeting);

        return newGreeting;
    }

    public List<Greeting> getAllGreetings() {
        return List.copyOf(greetingMap.values());
    }
}
