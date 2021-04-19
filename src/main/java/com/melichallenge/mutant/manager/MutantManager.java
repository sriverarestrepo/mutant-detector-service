package com.melichallenge.mutant.manager;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;

public interface MutantManager {

	public boolean isMutant(String[] dna) throws DnaException, RepositoryException;
}

