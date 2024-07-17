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

        if (greetingToUpdate != null) {
            greetingToUpdate.setName(updatedGreeting.getName());
            greetingToUpdate.setGreeting(updatedGreeting.getGreeting());

            List<Language> updatedLanguages = updatedGreeting.getLanguages();
            List<Language> existingLanguages = greetingToUpdate.getLanguages();

            if (updatedLanguages != null) {
                for (Language updatedLanguage : updatedLanguages) {
                    boolean languageExists = false;

                    for (Language existingLanguage : existingLanguages) {
                        if (existingLanguage.getName().equals(updatedLanguage.getName())) {
                            languageExists = true;
                            break;
                        }
                    }

                    if (!languageExists) {
                        Language newLanguage = languageRepository.findByName(updatedLanguage.getName());
                        if (newLanguage == null) {
                            newLanguage = languageRepository.save(updatedLanguage);
                        }
                        existingLanguages.add(newLanguage);
                    }
                }
            }

            return greetingRepository.save(greetingToUpdate);
        }

        return null;
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
