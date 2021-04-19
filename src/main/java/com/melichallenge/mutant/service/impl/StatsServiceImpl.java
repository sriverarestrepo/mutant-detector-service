package com.melichallenge.mutant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.StatsManager;
import com.melichallenge.mutant.model.Stats;
import com.melichallenge.mutant.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsManager statsManager;
	
	@Override
	public Stats getStats() throws RepositoryException {
		return statsManager.getStats();
	}

}