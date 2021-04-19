package com.melichallenge.mutant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.StatsManager;
import com.melichallenge.mutant.model.Stats;

@SpringBootTest
public class StatsServiceTest {
	
	@MockBean
	private StatsManager statsManager;
	
	@Autowired
	private StatsService statsService;
	
	@Test
	public void shouldGetStats() throws RepositoryException {
		Stats statsExpected = new Stats(0, 0);
		when(statsManager.getStats()).thenReturn(statsExpected);
		
		Stats statsResponse = statsService.getStats();

		assertEquals(statsExpected.getCount_human_dna(), statsResponse.getCount_human_dna());
		assertEquals(statsExpected.getCount_mutant_dna(), statsResponse.getCount_mutant_dna());
		assertEquals(statsExpected.getRatio(), statsResponse.getRatio());

	}
}
