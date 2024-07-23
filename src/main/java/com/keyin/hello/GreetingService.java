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
        if (newGreeting.getLanguages() == null || newGreeting.getLanguages().isEmpty()) {
            Language english = languageRepository.findByName("English");

            if (english == null) {
                english = new Language();
                english.setName("English");
                languageRepository.save(english);
            }

            ArrayList<Language> languageArrayList = new ArrayList<>();
            languageArrayList.add(english);

            newGreeting.setLanguages(languageArrayList);
        } else {
            List<Language> languages = new ArrayList<>();
            for (Language language : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    language = languageRepository.save(language);
                }
                languages.add(language);
            }
            newGreeting.setLanguages(languages);
        }

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

        List<Language> updatedLanguages = new ArrayList<>();
        for (Language language : updatedGreeting.getLanguages()) {
            Language langInDB = languageRepository.findByName(language.getName());

            if (langInDB == null) {
                language = languageRepository.save(language);
            } else {
                language = langInDB;
            }

            updatedLanguages.add(language);
        }

        greetingToUpdate.setLanguages(updatedLanguages);

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
