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
            List<Language> langsInDB = new ArrayList<Language>();

            for (Language language : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());

                if (langInDB == null) {
                    langsInDB.add(languageRepository.save(language));
                } else {
                    langsInDB.add(langInDB);
                }
            }

            newGreeting.setLanguages(langsInDB);
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


        List<Language> updatedGreetingLanguages = updatedGreeting.getLanguages();
        List<Language> finalLanguages = new ArrayList<Language>();

        // Iterate across the list of languages provided in the updated greeting
        if (!updatedGreetingLanguages.isEmpty()) {
            for (Language language : updatedGreetingLanguages) {
                Language languageInDB = languageRepository.findByName(language.getName());

                // Add the language to the database if it does not already exist
                if (languageInDB == null)
                    finalLanguages.add(languageRepository.save(language));
                else
                    finalLanguages.add(languageInDB);
            }
        }

        greetingToUpdate.setLanguages(finalLanguages);

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
