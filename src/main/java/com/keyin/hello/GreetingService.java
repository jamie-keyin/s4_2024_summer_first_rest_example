package com.keyin.hello;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GreetingService {
    private Map<Integer, Greeting> greetingMap = new HashMap<Integer, Greeting>();

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

    public Greeting updateGreeting(Integer index, Greeting updatedGreeting) {
        Greeting greetingToUpdate = greetingMap.get(index);

        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());

        greetingMap.put(index, greetingToUpdate);

        return greetingToUpdate;
    }

    public void deleteGreeting(Integer index) {
        greetingMap.remove(index);
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        List<Greeting> greetingsFound = new ArrayList<Greeting>();

        for (Greeting greeting : greetingMap.values()) {
            if (greeting.getName().equalsIgnoreCase(name) &&
                    greeting.getGreeting().equalsIgnoreCase(greetingName)) {
                greetingsFound.add(greeting);
            }
        }

        return greetingsFound;
    }
}
