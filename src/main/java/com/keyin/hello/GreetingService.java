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

            ArrayList<Language> englishArrayList = new ArrayList<Language>();
            englishArrayList.add(english);

            newGreeting.setLanguages(englishArrayList);
        } else {
            List<Language> languagesForNewGreeting = setLanguagesForGreeting(newGreeting);
            newGreeting.setLanguages(languagesForNewGreeting);
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

        List<Language> updatedLanguagesList = setLanguagesForGreeting(updatedGreeting);
        greetingToUpdate.setLanguages(updatedLanguagesList);

        return greetingRepository.save(greetingToUpdate);
    }

    public void deleteGreeting(long index) {
        greetingRepository.delete(getGreeting(index));
    }

    public List<Greeting> findGreetingsByNameAndGreeting(String name, String greetingName) {
        return greetingRepository.findByNameAndGreeting(name, greetingName);
    }


    public boolean checkIfLanguageInDB(Language language) {

        String languageName = language.getName();

        if (languageRepository.findByName(languageName) == null) {
            return false;
        } else {
            return true;
        }

    }

    public List<Language> setLanguagesForGreeting(Greeting greeting) {
    List<Language> languagesToAddToDB = new ArrayList<>();
    List<Language> allUpdatedLanguages = new ArrayList<>();

    for(
    Language language :greeting.getLanguages())

    {
        boolean checkLanguageInDB = checkIfLanguageInDB(language);
        if (!checkLanguageInDB) {
            languagesToAddToDB.add(language);
            allUpdatedLanguages.add(language);
        } else {
            allUpdatedLanguages.add(languageRepository.findByName(language.getName()));
        }
    }

    languagesToAddToDB.stream()
            .forEach(language ->languageRepository.save(language));

        return allUpdatedLanguages;
}



}
