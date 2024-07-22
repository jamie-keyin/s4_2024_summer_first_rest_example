package com.keyin.hello;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {
    List<Greeting> findByNameAndGreeting(String name, String greeting);
}

