package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping("search_language")
    public Language searchLanguage(@RequestParam(value = "name", required = false) String name) {
        return languageService.findLanguagesByName(name);
    }

    @GetMapping("languages")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("language/{index}")
    public Language getLanguage(@PathVariable Integer index) {
        return languageService.getLanguage(index);
    }

    @PostMapping("language")
    public Language createLanguage(@RequestBody Language newLanguage) {
        return languageService.createLanguage(newLanguage);
    }

    @PutMapping("language/{index}")
    public Language updateLanguage(@PathVariable Integer index, @RequestBody Language updatedLanguage) {
        return languageService.updateLanguage(index, updatedLanguage);
    }

    @DeleteMapping("language/{index}")
    public void deleteLanguage(@PathVariable Integer index) {
        languageService.deleteLanguage(index);
    }
}
