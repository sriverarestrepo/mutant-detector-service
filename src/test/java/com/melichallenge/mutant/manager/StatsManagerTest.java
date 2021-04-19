package com.melichallenge.mutant.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Person;
import com.melichallenge.mutant.model.Stats;
import com.melichallenge.mutant.repository.DnaPersonRepository;

@SpringBootTest
public class StatsManagerTest {

	@MockBean
	private DnaPersonRepository dnaPersonRepository;
	
	@Autowired
	private StatsManager statsManager;

	
	@Test
	public void shouldFindByIsMutant() throws RepositoryException {
		
		Stats statsExpected = new Stats(0, 0); 
		
		when(dnaPersonRepository.findByIsMutant(false)).thenReturn(new ArrayList<Person>());
		when(dnaPersonRepository.findByIsMutant(true)).thenReturn(new ArrayList<Person>());

		Stats statsResponse = statsManager.getStats();

		assertEquals(statsExpected.getCount_human_dna(), statsResponse.getCount_human_dna());
		assertEquals(statsExpected.getCount_mutant_dna(), statsResponse.getCount_mutant_dna());
		assertEquals(statsExpected.getRatio(), statsResponse.getRatio());

	}
	
	@Test
	public void shouldFindByIsMutantFail() throws DnaException {
		when(dnaPersonRepository.findByIsMutant(false)).thenThrow(new RuntimeException());
		Exception e = assertThrows(RepositoryException.class, () -> statsManager.getStats());
		assertEquals("Failed to get records from Person table", e.getMessage());
	}
	
}
