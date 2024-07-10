package com.keyin.hello;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Greeting {

    @Id
    @SequenceGenerator(name = "greeting_sequence", sequenceName = "greeting_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "greeting_sequence")
    private long id;
    private String greeting;
    private String name;

    @ManyToMany
    private List<Language> languages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
