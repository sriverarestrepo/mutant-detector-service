package com.melichallenge.mutant.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.validation.DnaValidation;

@Component
public class DnaValidationImpl  implements DnaValidation{

	private static String RGX_FORMAT_DNA = "[[ATCG]]+";
	
	private static int MINIMUM_SIZE_LIMIT = 4;

	public void validate(String[] dna) throws DnaException {
		if (dna != null) {
			int dnaSize = dna.length;

			validateDnaStructure(dnaSize);

			for (String dnaSeq : dna) {
				validateSizeSequenceDna(dnaSeq, dnaSize); 
				validateFormatSequenceDna(dnaSeq); 
			}
		}else {
			throw new DnaException("DNA cannot be null");
		}
	}
	
	private void validateDnaStructure(int dnanSize) throws DnaException {
		if (dnanSize < MINIMUM_SIZE_LIMIT) {
			throw new DnaException("Invalid size dna structure");
		}
	}
	
	private void validateSizeSequenceDna(String dna, int dnaSize) throws DnaException {
		if (dna.length() != dnaSize) {
			throw new DnaException("Invalid size in the DNA sequence");
		}
	}
	
	private void validateFormatSequenceDna(String seq) throws DnaException {
		Pattern pattern = Pattern.compile(RGX_FORMAT_DNA, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(seq);
		boolean matchFound = matcher.matches();

		if (!matchFound) {
			throw new DnaException("Invalid characters in the DNA sequence");
		}
	}
	
	
}
