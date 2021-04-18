package com.melichallenge.mutant.validation;

import com.melichallenge.mutant.exception.DnaException;

public interface DnaValidation {

	public void validate(String[] dna) throws DnaException;

}
