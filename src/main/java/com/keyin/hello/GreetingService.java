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

        /* If greetingToUpdate is not null, indicates that a valid Greeting was found for updating, if it passes it
         * updates name and greeting fields to the greetingToUpdate method with values from this method.*/
        /* If the Greeting does not exist, create a new one and initialize a list of languages */
        if (greetingToUpdate == null) {
            greetingToUpdate = new Greeting();
            greetingToUpdate.setName(updatedGreeting.getName());
            greetingToUpdate.setGreeting(updatedGreeting.getGreeting());
            greetingToUpdate.setLanguages(new ArrayList<>());
        }

        /* Checks to see if updatedLanguages isn't null, if it is, no languages to update.
         *  Uses a stream to check all languages within current languages ignoring case differences.
         * If updated language isn't found in current languages it is added. */

        List<Language> updatedLanguages = updatedGreeting.getLanguages();
        List<Language> currLanguages = greetingToUpdate.getLanguages();

        /* Code checks to see if updatedLanguages list from UpdatedGreeting is not null, determines whether there are new languages. */
        if (updatedLanguages != null) {
            /* Loop to see if an existing language is in DB based on its identifier, if not found it is saved. */
            for (Language updatedLanguage : updatedLanguages) {
                Language languageInDb = languageRepository.findById(updatedLanguage.getId())
                        .orElseGet(() -> {
                            return languageRepository.save(updatedLanguage);
                        });

                /* Checks to see if Language already exists in the list of languages, compares the names by ignoring the case. If it does
                * not exist it is added, prevents duplicates. */
                boolean languageExists = currLanguages.stream()
                        .anyMatch(l -> l.getName().equalsIgnoreCase(updatedLanguage.getName()));
                if (!languageExists) {
                    currLanguages.add(updatedLanguage);
                }
            }
        }
        greetingToUpdate.setLanguages(currLanguages);
        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }
}
