package com.keyin.hynes.braden.s4qap2sdat.classes.entities;
import com.keyin.hynes.braden.s4qap2sdat.classes.abstracts.DataEntity;
import java.util.List;
import jakarta.persistence.ManyToMany;
public final class GreetingEntity extends DataEntity {
    private String greeting;
    private String name;
    @ManyToMany
    private List<LanguageEntity> languages;
    public GreetingEntity(
        String greeting,
        String name,
        List<LanguageEntity> languages
    ) {
        super();
        this.greeting = greeting;
        this.name = name;
        this.languages = languages;
    }
    public GreetingEntity() {
        super();
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
    public List<LanguageEntity> getLanguages() {
        return languages;
    }
    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }
}