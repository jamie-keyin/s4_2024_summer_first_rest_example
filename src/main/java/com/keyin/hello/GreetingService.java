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
                english = new Language();
                languageRepository.save(english);
            }

            ArrayList<Language> languageArrayList = new ArrayList<Language>();
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

    public Greeting updateGreeting(Integer index, Greeting updatedGreeting) {
        Greeting greetingToUpdate = getGreeting(index);

        greetingToUpdate.setName(updatedGreeting.getName());
        greetingToUpdate.setGreeting(updatedGreeting.getGreeting());
        greetingToUpdate.setLanguages(findOrCreateLanguage(updatedGreeting.getLanguages()));

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }

    /**
     * Takes a list of languages and finds the corresponding database language or
     * creates a new one if not found.
     */
    private List<Language> findOrCreateLanguage(List<Language> languages) {
        List<Language> newLanguages = new ArrayList<>();
        for (Language language : languages) {
            String name = language.getName();
            language = languageRepository.findByName(name);
            if (language == null) {
                language = languageRepository.save(new Language(name));
            }
            newLanguages.add(language);
        }
        return newLanguages;
    }
}
