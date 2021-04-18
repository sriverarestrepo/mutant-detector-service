package com.melichallenge.mutant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Stats;
import com.melichallenge.mutant.service.StatsService;

public class StatsControllerTest {


	@Test
	public void shouldGetDefaultStats() throws RepositoryException {
		StatsService statsService = Mockito.mock(StatsService.class);
		when(statsService.getStats()).thenReturn(loadDefaultStats());
		StatsController statsController = new StatsController(statsService);

		Stats statsExpected = new Stats(0, 0);
		assertEquals(statsExpected.getCount_human_dna(), statsController.stats().getCount_human_dna());
		assertEquals(statsExpected.getCount_mutant_dna(), statsController.stats().getCount_mutant_dna());
		assertEquals(statsExpected.getRatio(), statsController.stats().getRatio());

	}

	@Test
	public void shouldGetStats() throws RepositoryException {
		StatsService statsService = Mockito.mock(StatsService.class);
		when(statsService.getStats()).thenReturn(loadStats());
		StatsController statsController = new StatsController(statsService);

		Stats statsExpected = new Stats(40, 100);
		assertEquals(statsExpected.getCount_human_dna(), statsController.stats().getCount_human_dna());
		assertEquals(statsExpected.getCount_mutant_dna(), statsController.stats().getCount_mutant_dna());
		assertEquals(statsExpected.getRatio(), statsController.stats().getRatio());

	}

	
	@Test
	public void repositoryExceptionGetStats() throws RepositoryException {
		StatsService statsService = Mockito.mock(StatsService.class);
		when(statsService.getStats()).thenThrow(loadRepositoryExceptionStats());
		StatsController statsController = new StatsController(statsService);
		statsController.stats();
	}

	
	@Test
	public void exceptionGetStats() throws Exception {
		StatsService statsService = Mockito.mock(StatsService.class);
		when(statsService.getStats()).thenThrow(new RuntimeException());
		StatsController statsController = new StatsController(statsService);
		statsController.stats();
	}

	
	private Stats loadDefaultStats() {
		return new Stats(0, 0);
	}

	
	private Stats loadStats() {
		return new Stats(40, 100);
	}

	
	private RepositoryException loadRepositoryExceptionStats() throws RepositoryException {
		return new RepositoryException("RepositoryException default message.");
	}
}
