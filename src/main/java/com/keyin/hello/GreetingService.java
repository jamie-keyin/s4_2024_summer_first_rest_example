package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Greeting getGreeting(long id) {
        Optional<Greeting> result = greetingRepository.findById(id);
        return result.orElse(null);
    }

    public Greeting createGreeting(Greeting newGreeting) {
        if (newGreeting.getLanguages() == null) {
            Language english = languageRepository.findByName("English");
            if (english == null) {
                english = new Language();
                english.setName("English");
                languageRepository.save(english);
            }
            List<Language> languages = new ArrayList<>();
            languages.add(english);
            newGreeting.setLanguages(languages);
        } else {
            for (Language language : newGreeting.getLanguages()) {
                if (languageRepository.findByName(language.getName()) == null) {
                    languageRepository.save(language);
                }
            }
        }
        return greetingRepository.save(newGreeting);
    }

    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, Greeting updatedGreeting) {
        Greeting existingGreeting = getGreeting(id);
        if (existingGreeting == null) {
            return null;
        }
        existingGreeting.setName(updatedGreeting.getName());
        existingGreeting.setGreeting(updatedGreeting.getGreeting());
        existingGreeting.setLanguages(updatedGreeting.getLanguages());
        return greetingRepository.save(existingGreeting);
    }

    public void deleteGreeting(Long id) {
        Greeting greeting = getGreeting(id);
        if (greeting != null) {
            greetingRepository.delete(greeting);
        }
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }

    public Greeting addLanguagesToGreeting(Long id, List<Language> newLanguages) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (!optionalGreeting.isPresent()) {
            return null; // or throw a custom exception
        }

        Greeting greeting = optionalGreeting.get();
        List<Language> existingLanguages = greeting.getLanguages();
        for (Language newLanguage : newLanguages) {
            if (languageRepository.findByName(newLanguage.getName()) == null) {
                languageRepository.save(newLanguage);
            }
            if (!existingLanguages.contains(newLanguage)) {
                existingLanguages.add(newLanguage);
            }
        }
        greeting.setLanguages(existingLanguages);
        return greetingRepository.save(greeting);
    }
}

