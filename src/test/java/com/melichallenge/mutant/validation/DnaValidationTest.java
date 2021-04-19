package com.melichallenge.mutant.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.melichallenge.mutant.exception.DnaException;

@SpringBootTest
public class DnaValidationTest {


	@Autowired
	private DnaValidation dnaValidation;

	@Test
	public void validateNullDna() throws DnaException {
		Exception e = assertThrows(DnaException.class, () -> dnaValidation.validate(null));
		assertEquals("DNA cannot be null", e.getMessage());
	}

	@Test
	public void validateDnaStructureFail() throws DnaException {
		Exception e = assertThrows(DnaException.class, () -> dnaValidation.validate(getDnaWihtStructureFail()));
		assertEquals("Invalid size dna structure", e.getMessage());
	}

	@Test
	public void validateSizeSequenceDnaFail() throws DnaException {
		Exception e = assertThrows(DnaException.class, () -> dnaValidation.validate(getDnaWihtSizeSequenceDnaFail()));
		assertEquals("Invalid size in the DNA sequence", e.getMessage());
	}

	@Test
	public void validateFormatSequenceDnaFail() throws DnaException {
		Exception e = assertThrows(DnaException.class, () -> dnaValidation.validate(getDnaWihtFormatSequenceDnaFail()));
		assertEquals("Invalid characters in the DNA sequence", e.getMessage());
	}

	@Test
	public void validateStructureOK() throws DnaException {
		DnaValidation dnaValidationMock = Mockito.mock(DnaValidation.class);
		doNothing().when(dnaValidationMock).validate(getDnaWihtStructureOK());
		dnaValidationMock.validate(getDnaWihtStructureOK());
		verify(dnaValidationMock, times(1)).validate(getDnaWihtStructureOK());

	}

	private String[] getDnaWihtStructureOK() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		return dna;
	}

	private String[] getDnaWihtStructureFail() {
		String[] dna = { "ATA", "CAG", "CTT" };
		return dna;
	}

	private String[] getDnaWihtSizeSequenceDnaFail() {
		String[] dna = { "ATGCGA", "CGTGC", "TTAGT", "AGAAGG", "CCCTA", "TCACTG" };
		return dna;
	}

	private String[] getDnaWihtFormatSequenceDnaFail() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTXTGT", "AGAXGG", "CCCCTA", "TCXCTG" };
		return dna;
	}

}
