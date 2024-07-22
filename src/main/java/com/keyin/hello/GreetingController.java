package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("search_greeting")
    public List<Greeting> searchGreeting(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "greeting", required = false) String greeting) {
        return greetingService.findGreetingsByNameAndGreeting(name, greeting);
    }

    @GetMapping("greetings")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("greeting/{index}")
    public Greeting getGreeting(@PathVariable Long index) {
        return greetingService.getGreeting(index);
    }

    @PostMapping("greeting")
    public Greeting createGreeting(@Valid @RequestBody Greeting newGreeting) {
        return greetingService.createGreeting(newGreeting);
    }

    @PutMapping("greeting/{index}")
    public Greeting updateGreeting(@PathVariable Integer index, @Valid @RequestBody Greeting updatedGreeting) {
        return greetingService.updateGreeting(index, updatedGreeting);
    }

    @DeleteMapping("greeting/{index}")
    public void deleteGreeting(@PathVariable Long index) {
        greetingService.deleteGreeting(index);
    }

    @PutMapping("greeting/{index}/languages")
    public Greeting addLanguagesToGreeting(@PathVariable Long index, @RequestBody List<Language> languages) {
        return greetingService.addLanguages(index, languages);
    }
}





