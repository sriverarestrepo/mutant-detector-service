package com.melichallenge.mutant.manager;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Stats;

public interface StatsManager {
	
	public Stats getStats() throws RepositoryException;
	
}
