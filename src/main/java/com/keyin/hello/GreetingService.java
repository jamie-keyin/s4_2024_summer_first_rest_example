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

    public Greeting getGreeting(long index) {
        Optional<Greeting> result = greetingRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Greeting createGreeting(Greeting newGreeting) {
        // Step 1: Handle the languages for the newGreeting
        List<Language> languageList = new ArrayList<>();

        if (newGreeting.getLanguages() == null || newGreeting.getLanguages().isEmpty()) {
            // No languages provided, default to English
            Language english = languageRepository.findByName("English");
            if (english == null) {
                english = new Language("English");
                languageRepository.save(english);
            }
            languageList.add(english);
        } else {
            // Languages provided in the newGreeting object
            for (Language newGreetingLanguage : newGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(newGreetingLanguage.getName());
                if (langInDB == null) {
                    Language newLanguage = new Language(newGreetingLanguage.getName());
                    languageRepository.save(newLanguage);
                    languageList.add(newLanguage);
                } else {
                    languageList.add(langInDB);
                }
            }

        }
        // Set the languages for the newGreeting object
        newGreeting.setLanguages(languageList);

        // Step 2: Check for an existing greeting with the same name and greeting
        Greeting existingGreeting = greetingRepository.findByNameAndGreeting(newGreeting.getName(), newGreeting.getGreeting());

        if (existingGreeting == null) {
            // No existing greeting found, create a new one
            return greetingRepository.save(newGreeting);
        } else {
            // Existing greeting found, update the existing greeting
            // Merge new languages with existing languages
            List<Language> existingLanguages = existingGreeting.getLanguages();
            for (Language newLang : languageList) {
                boolean languageExists = false;

                for (Language existingLang : existingLanguages) {
                    if (existingLang.getName().equals(newLang.getName())) {
                        languageExists = true;
                        break;
                    }
                }

                if (!languageExists) {
                    existingLanguages.add(newLang);
                }
            }
            existingGreeting.setLanguages(existingLanguages);

            // Save and return the updated greeting
            return greetingRepository.save(existingGreeting);
        }

    }


    public List<Greeting> getAllGreetings() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting updateGreeting(Integer index, Greeting updatedGreeting) {
        Greeting greetingToUpdate = getGreeting(index);

        if(updatedGreeting.getGreeting() != null){
            greetingToUpdate.setGreeting(updatedGreeting.getGreeting());
        }
        if(updatedGreeting.getName() != null){
            greetingToUpdate.setName(updatedGreeting.getName());
        }

        if(updatedGreeting.getLanguages() != null){
            // check to see if language is in repository
            ArrayList<Language> updatedGreetingLanguages = new ArrayList<>();

            for (Language language : updatedGreeting.getLanguages()) {
                Language langInDB = languageRepository.findByName(language.getName());
                if (langInDB == null) {
                    languageRepository.save(language);
                    updatedGreetingLanguages.add(language);
                } else {
                    updatedGreetingLanguages.add(langInDB);
                }
            }

            greetingToUpdate.setLanguages(updatedGreetingLanguages);

        }

        return greetingRepository.save(greetingToUpdate);

    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public Greeting findGreetingByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
