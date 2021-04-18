package com.melichallenge.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Stats;
import com.melichallenge.mutant.service.StatsService;

@RestController
public class StatsController {

	@Autowired
	private StatsService statsService;

	public StatsController(StatsService statsService) {
		this.statsService = statsService;
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public Stats stats() {

		Stats statsResponse = null;

		try {
			statsResponse = statsService.getStats();

		} catch (RepositoryException e) {
		} catch (Exception e) {
		}
		return statsResponse;
	}
}
