package com.melichallenge.mutant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Dna;
import com.melichallenge.mutant.service.MutantService;

public class MutantControllerTest {

	@Test
	public void isMutantTrue() throws RepositoryException, DnaException {

		Dna dna = new Dna();
		dna.setDna(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" });

		MutantService mutantService = Mockito.mock(MutantService.class);

		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.OK);
		when(mutantService.isMutant(dna)).thenReturn(responseExpected);
		MutantController mutantController = new MutantController(mutantService);

		ResponseEntity<String> responseController = mutantController.isMutant(dna);

		assertEquals(responseExpected.getStatusCodeValue(), responseController.getStatusCodeValue());
		assertEquals(200, responseController.getStatusCodeValue());
	}
	
	
	@Test
	public void isMutantFalse() throws RepositoryException, DnaException {

		Dna dna = new Dna();
		dna.setDna(new String[] { "CGA", "TGC", "TGT", "AGG", "CTA", "TCA" });

		MutantService mutantService = Mockito.mock(MutantService.class);

		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		when(mutantService.isMutant(dna)).thenReturn(responseExpected);
		MutantController mutantController = new MutantController(mutantService);

		ResponseEntity<String> responseController = mutantController.isMutant(dna);

		assertEquals(responseExpected.getStatusCodeValue(), responseController.getStatusCodeValue());
		assertEquals(403, responseController.getStatusCodeValue());

	}
	
	@Test
	public void isMutantWhitDnaException() throws RepositoryException, DnaException {

		Dna dna = new Dna();
		dna.setDna(new String[] { "CXA", "TXC", "TXT", "AXG", "CXA", "TXA" });

		MutantService mutantService = Mockito.mock(MutantService.class);

		when(mutantService.isMutant(dna)).thenThrow(new DnaException("DnaException"));
		MutantController mutantController = new MutantController(mutantService);

		ResponseEntity<String> responseController = mutantController.isMutant(dna);
		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		assertEquals(responseExpected.getStatusCodeValue(), responseController.getStatusCodeValue());
		assertEquals(403, responseController.getStatusCodeValue());

	}
	
	@Test
	public void isMutantWhitRepositoryExceptionException() throws RepositoryException, DnaException {

		Dna dna = new Dna();
		dna.setDna(new String[] { "CXA", "TXC", "TXT", "AXG", "CXA", "TXA" });

		MutantService mutantService = Mockito.mock(MutantService.class);

		when(mutantService.isMutant(dna)).thenThrow(new RepositoryException("RepositoryException"));
		MutantController mutantController = new MutantController(mutantService);

		ResponseEntity<String> responseController = mutantController.isMutant(dna);
		ResponseEntity<String> responseExpected = new ResponseEntity<>(HttpStatus.FORBIDDEN);

		assertEquals(responseExpected.getStatusCodeValue(), responseController.getStatusCodeValue());
		assertEquals(403, responseController.getStatusCodeValue());

	}

}
