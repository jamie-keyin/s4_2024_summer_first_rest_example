package com.keyin.hynes.braden.s4qap2sdat.interfaces.repositories;
import com.keyin.hynes.braden.s4qap2sdat.classes.entities.GreetingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GreetingRepository extends CrudRepository<GreetingEntity, Integer> {
    GreetingEntity findByNameAndBody(
        String name,
        String body
    );
}