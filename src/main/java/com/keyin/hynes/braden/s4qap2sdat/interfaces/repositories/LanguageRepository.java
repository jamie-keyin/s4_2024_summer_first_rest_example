package com.keyin.hynes.braden.s4qap2sdat.interfaces.repositories;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.LanguageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    LanguageEntity findByName(String name);
}