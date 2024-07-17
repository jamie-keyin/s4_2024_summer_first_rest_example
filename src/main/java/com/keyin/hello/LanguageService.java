package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LanguageService {
    @Autowired
    private GreetingRepository greetingRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public Language getLanguage(long index) {
        Optional<Language> result = languageRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Language createLanguage(Language newLanguage) {
        return languageRepository.save(newLanguage);
    }

    public List<Language> getAllLanguages() {
        return (List<Language>) languageRepository.findAll();
    }

    public Language updateLanguage(Integer index, Language updatedLanguage) {
        Language languageToUpdate = getLanguage(index);

        languageToUpdate.setName(updatedLanguage.getName());

        return languageRepository.save(languageToUpdate);
    }

    public void deleteLanguage(long index) {
        languageRepository.delete(getLanguage(index));
    }

    public Language findLanguagesByName(String name) {
        return languageRepository.findByName(name);
    }
}
