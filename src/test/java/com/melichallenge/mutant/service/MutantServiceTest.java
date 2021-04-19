package com.melichallenge.mutant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.MutantManager;
import com.melichallenge.mutant.model.Dna;

@SpringBootTest
public class MutantServiceTest {

	@MockBean
	private MutantManager mutantManager;

	@Autowired
	private MutantService mutantService;

	@Test
	public void isMutantTrue() throws RepositoryException, DnaException {

		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		Dna dna = new Dna();
		dna.setDna(dnaStr);

		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.OK);

		when(mutantManager.isMutant(dnaStr)).thenReturn(true);

		ResponseEntity<String> response = mutantService.isMutant(dna);

		assertEquals(responseExpected.getStatusCodeValue(), response.getStatusCodeValue());
		assertEquals(200, response.getStatusCodeValue());

	}

	@Test
	public void isMutantFalse() throws RepositoryException, DnaException {

		String[] dnaStr = new String[] { "ATA", "CAC", "XXX", "AXGG", "CCXXTA", "TCXACTG" };

		Dna dna = new Dna();
		dna.setDna(dnaStr);

		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		when(mutantManager.isMutant(dnaStr)).thenReturn(false);

		ResponseEntity<String> response = mutantService.isMutant(dna);

		assertEquals(responseExpected.getStatusCodeValue(), response.getStatusCodeValue());
		assertEquals(403, response.getStatusCodeValue());

	}
}
