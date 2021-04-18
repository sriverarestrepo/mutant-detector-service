package com.melichallenge.mutant.service;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Stats;

public interface StatsService {

	public Stats getStats() throws RepositoryException ;
	
}
