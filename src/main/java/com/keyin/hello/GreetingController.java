package com.keyin.hello;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("search_greeting")
    public Greeting searchGreeting(@RequestParam(value = "name") String name, @RequestParam(value = "greeting") String greeting) {
        return greetingService.findGreetingByNameAndGreeting(name, greeting);
    }

    @GetMapping("greetings")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("greeting/{index}")
    public Greeting getGreeting(@PathVariable Integer index) {
        return greetingService.getGreeting(index);
    }

    @PostMapping("greeting")
    public Greeting createGreeting(@RequestBody Greeting newGreeting) {
        return greetingService.createGreeting(newGreeting);
    }

    @PutMapping("greeting/{index}")
    public Greeting updateGreeting(@PathVariable Integer index, @RequestBody Greeting updatedGreeting) {
        return greetingService.updateGreeting(index, updatedGreeting);
    }

    @DeleteMapping("greeting/{index}")
    public void deleteGreeting(@PathVariable Integer index) {
        greetingService.deleteGreeting(index);
    }
}
