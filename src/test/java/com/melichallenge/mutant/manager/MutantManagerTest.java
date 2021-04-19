package com.melichallenge.mutant.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Person;
import com.melichallenge.mutant.repository.DnaPersonRepository;
import com.melichallenge.mutant.validation.DnaValidation;

@SpringBootTest
public class MutantManagerTest {

	@MockBean
	private DnaPersonRepository dnaPersonRepository;
	
	@MockBean
	private DnaValidation dnaValidation;
	
	@MockBean
	private MutantDetectorManager mutantDetectorManager;
	
	@Autowired
	private MutantManager mutantManager;
	
	
	@Test
	public void isMutantAllStepsTrue() throws DnaException, RepositoryException {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		//execute validations
		doNothing().when(dnaValidation).validate(dnaStr);
		dnaValidation.validate(dnaStr);
		verify(dnaValidation, times(1)).validate(dnaStr);
		
		when(mutantDetectorManager.detectMutantPatterns(dnaStr)).thenReturn(2);
		
		boolean result = mutantManager.isMutant(dnaStr);

		assertEquals(true, result);
	}
	
	
	@Test
	public void isMutantAllStepsFalse() throws DnaException, RepositoryException {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		//execute validations
		doNothing().when(dnaValidation).validate(dnaStr);
		dnaValidation.validate(dnaStr);
		verify(dnaValidation, times(1)).validate(dnaStr);
		
		when(mutantDetectorManager.detectMutantPatterns(dnaStr)).thenReturn(1);
		
		boolean result = mutantManager.isMutant(dnaStr);

		assertEquals(false, result);
	}
	
	
	@Test
	public void isMutantSaveStepNullCase() throws DnaException, RepositoryException {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		//execute validations
		doNothing().when(dnaValidation).validate(dnaStr);
		dnaValidation.validate(dnaStr);
		verify(dnaValidation, times(1)).validate(dnaStr);
		
		when(mutantDetectorManager.detectMutantPatterns(dnaStr)).thenReturn(1);
		
		when(dnaPersonRepository.findByDna(String.join("_", dnaStr))).thenReturn(null);
		
		boolean result = mutantManager.isMutant(dnaStr);

		assertEquals(false, result);
	}
	
	@Test
	public void isMutantSaveStepPersonExistCase() throws DnaException, RepositoryException {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		//execute validations
		doNothing().when(dnaValidation).validate(dnaStr);
		dnaValidation.validate(dnaStr);
		verify(dnaValidation, times(1)).validate(dnaStr);
		
		when(mutantDetectorManager.detectMutantPatterns(dnaStr)).thenReturn(1);
		
		Person person = new Person(String.join("+", dnaStr), false);
		when(dnaPersonRepository.findByDna(String.join("_", dnaStr))).thenReturn(person);
		
		boolean result = mutantManager.isMutant(dnaStr);

		assertEquals(false, result);
	}
	
	
	@Test
	public void isMutantSaveStepExceptionCase() throws DnaException, RepositoryException {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		//execute validations
		doNothing().when(dnaValidation).validate(dnaStr);
		dnaValidation.validate(dnaStr);
		verify(dnaValidation, times(1)).validate(dnaStr);
		
		when(mutantDetectorManager.detectMutantPatterns(dnaStr)).thenReturn(1);
		
		when(dnaPersonRepository.findByDna(String.join("_", dnaStr))).thenThrow(new RuntimeException());
		
		Exception e = assertThrows(RepositoryException.class, () -> mutantManager.isMutant(dnaStr));
		assertEquals("Error saving records into Person table", e.getMessage());
	}
}
