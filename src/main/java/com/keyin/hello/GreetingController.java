package com.keyin.hello;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("hello")
    public Greeting greeting(@RequestParam(value = "name", required = false) String name) {
        return greetingService.generateGreeting(name);
    }

    @GetMapping("search_greeting")
    public Greeting searchGreeting(@RequestParam(value = "index", required = false) Integer index) {
        return greetingService.getGreeting(index);
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
}
