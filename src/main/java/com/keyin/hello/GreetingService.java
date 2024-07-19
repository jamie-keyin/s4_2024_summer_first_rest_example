// GreetingService.java
package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Greeting getGreeting(long index) {
        Optional<Greeting> result = greetingRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Greeting createGreeting(Greeting newGreeting) {
        if (newGreeting.getLanguages() == null) {
            Language english = languageRepository.findByName("English");

            if (english == null) {
                english = new Language("English");
                languageRepository.save(english);
            }

            ArrayList<Language> languageArrayList = new ArrayList<>();
            languageArrayList.add(english);

            newGreeting.setLanguages(languageArrayList);
        } else {
            for (Language language : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    language = languageRepository.save(language);
                }
            }
        }

        return greetingRepository.save(newGreeting);
    }

    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long index, Greeting updatedGreeting) {  // Changed Integer to Long to match the ID type in the entity
        Greeting greetingToUpdate = getGreeting(index);

        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());
        greetingToUpdate.setLanguages(updatedGreeting.getLanguages());

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }

    public void initializeGreetings() {
        List<Language> languages = Arrays.asList(
                new Language("English"),
                new Language("French"),
                new Language("Arabic"),
                new Language("Dutch"),
                new Language("Swedish")
        );

        for (Language language : languages) {
            if (languageRepository.findByName(language.getName()) == null) {
                languageRepository.save(language);
            }
        }

        List<Greeting> greetings = Arrays.asList(
                new Greeting("Hello", "Greeting1", Arrays.asList(languageRepository.findByName("English"))),
                new Greeting("Bonjour", "Greeting2", Arrays.asList(languageRepository.findByName("French"))),
                new Greeting("مرحبا", "Greeting3", Arrays.asList(languageRepository.findByName("Arabic"))),
                new Greeting("Hallo", "Greeting4", Arrays.asList(languageRepository.findByName("Dutch"))),
                new Greeting("Hej", "Greeting5", Arrays.asList(languageRepository.findByName("Swedish")))
        );

        for (Greeting greeting : greetings) {
            createGreeting(greeting);
        }
    }
}

