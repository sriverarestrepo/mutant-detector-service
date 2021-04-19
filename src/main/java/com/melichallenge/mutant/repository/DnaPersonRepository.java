package com.melichallenge.mutant.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.melichallenge.mutant.model.Person;

public interface DnaPersonRepository extends MongoRepository<Person, String> {

	public Person findByDna(String dna);
	
	public List<Person> findByIsMutant(boolean isMutant); 
	
}
