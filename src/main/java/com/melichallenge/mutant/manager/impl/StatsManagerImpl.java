package com.melichallenge.mutant.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.StatsManager;
import com.melichallenge.mutant.model.Person;
import com.melichallenge.mutant.model.Stats;
import com.melichallenge.mutant.repository.DnaPersonRepository;

@Component
public class StatsManagerImpl implements StatsManager{
	
	@Autowired
	private DnaPersonRepository dnaPersonRepository;
	
	@Override
	public Stats getStats() throws RepositoryException {
		List<Person> human_dna_list;
		List<Person> mutant_dna_list;
		try {
			mutant_dna_list = dnaPersonRepository.findByIsMutant(true);
			human_dna_list = dnaPersonRepository.findByIsMutant(false);
		} catch (Exception e) {
			throw new RepositoryException("Failed to get records from Person table");
		}
		
		return new Stats(mutant_dna_list.size(), human_dna_list.size());
	}
}
