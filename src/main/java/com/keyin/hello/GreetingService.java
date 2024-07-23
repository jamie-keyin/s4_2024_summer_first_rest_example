package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Greeting getGreeting(long index) {
        Optional<Greeting> result = greetingRepository.findById(index);
        return result.orElse(null);
    }

    public Greeting createGreeting(Greeting newGreeting) {
        List<Language> languages = handleLanguages(newGreeting.getLanguages());
        newGreeting.setLanguages(languages);
        return greetingRepository.save(newGreeting);
    }

    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting updateGreeting(Integer index, Greeting updatedGreeting) {
        Greeting greetingToUpdate = getGreeting(index);

        if (greetingToUpdate == null) {
            throw new IllegalArgumentException("Greeting not found for index: " + index);
        }

        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());

        List<Language> updatedLanguages = handleLanguages(updatedGreeting.getLanguages());
        greetingToUpdate.setLanguages(updatedLanguages);

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        Greeting greeting = getGreeting(index);
        if (greeting != null) {
            greetingRepository.delete(greeting);
        }
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }

    private List<Language> handleLanguages(List<Language> languages) {
        List<Language> languageList = new ArrayList<>();
        if (languages == null || languages.isEmpty()) {
            Language english = languageRepository.findByName("English");

            if (english == null) {
                english = new Language();
                english.setName("English");
                english = languageRepository.save(english);
            }

            languageList.add(english);
        } else {
            for (Language language : languages) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    langInDB = languageRepository.save(language);
                }

                languageList.add(langInDB);
            }
        }
        return languageList;
    }
}
