package com.melichallenge.mutant.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.MutantDetectorManager;
import com.melichallenge.mutant.manager.MutantManager;
import com.melichallenge.mutant.model.Person;
import com.melichallenge.mutant.repository.DnaPersonRepository;
import com.melichallenge.mutant.validation.DnaValidation;

@Component
public class MutantManagerImpl implements MutantManager {

	private DnaPersonRepository dnaPersonRepository;

	@Autowired
	private DnaValidation dnaValidation;

	@Autowired
	private MutantDetectorManager mutantDetectorManager;

	private static final String DELIMITER_STRING_FOR_TRANSFORM = "_";

	private static final int MIN_VALUE_MATCHES = 2;

	public MutantManagerImpl(DnaPersonRepository dnaPersonRepository) {
		this.dnaPersonRepository = dnaPersonRepository;
	}
	
	

	@Override
	public boolean isMutant(String[] dna) throws DnaException, RepositoryException {
		boolean isMutant = false;
		int matchsCount = 0;

		dnaValidation.validate(dna);

		matchsCount = mutantDetectorManager.detectMutantPatterns(dna);
		if (matchsCount >= MIN_VALUE_MATCHES) {
			isMutant = true;
		}

		saveDnaPerson(dna, isMutant);

		return isMutant;
	}

	
	private void saveDnaPerson(String[] dna, boolean isMutant) throws RepositoryException {

		String dnaStrTransformed = String.join(DELIMITER_STRING_FOR_TRANSFORM, dna);

		try {
			Person person = dnaPersonRepository.findByDna(dnaStrTransformed);

			if (person == null || !dnaStrTransformed.equalsIgnoreCase(person.getDna())) {
				person = new Person(dnaStrTransformed, isMutant);
				dnaPersonRepository.save(person);
			}

		} catch (Exception e) {
			throw new RepositoryException("Error saving records into Person table");
		}

	}
}
