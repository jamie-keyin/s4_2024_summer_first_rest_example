package com.keyin.hello;

import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    Language findByName(String name);
}


