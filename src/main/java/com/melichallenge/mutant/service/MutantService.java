package com.melichallenge.mutant.service;

import org.springframework.http.ResponseEntity;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Dna;

public interface MutantService {

	public ResponseEntity<String> isMutant(Dna dna) throws DnaException, RepositoryException;
	
}
