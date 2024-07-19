package com.keyin.hello;

import jakarta.persistence.EntityNotFoundException;
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
                english = new Language();
                languageRepository.save(english);
            }
            ArrayList<Language> languageArrayList = new ArrayList<Language>();
            languageArrayList.add(english);
            newGreeting.setLanguages(languageArrayList);
        } else {
            ArrayList<Language> languageArrayList = new ArrayList<>();
            for (Language language : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    langInDB = languageRepository.save(language);
                }
                languageArrayList.add(langInDB);
            }
            newGreeting.setLanguages(languageArrayList);
        }
        return greetingRepository.save(newGreeting);
    }

    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting updateGreeting(long index, Greeting updatedGreeting) {
        Optional<Greeting> result = greetingRepository.findById(index);
        if(result.isPresent()) {

        Greeting greetingToUpdate = getGreeting(index);
        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());

        ArrayList<Language> userLanguages = new ArrayList<>();

        for (Language language : updatedGreeting.getLanguages()) {
            Language langInDB = languageRepository.findByName(language.getName());

            if (langInDB == null) {
                langInDB = languageRepository.save(language);
            }
            userLanguages.add(langInDB);
        }
        greetingToUpdate.setLanguages(userLanguages);
        return greetingRepository.save(greetingToUpdate);

        } else {
            return null;
        }
    }

    public Greeting deleteGreeting(long index) {
        Optional<Greeting> result = greetingRepository.findById(index);
        if (result.isPresent()) {
            Greeting greetingToDelete = result.get();
            greetingRepository.delete(greetingToDelete);
            return greetingToDelete;
        } else {
            return null;
        }
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
