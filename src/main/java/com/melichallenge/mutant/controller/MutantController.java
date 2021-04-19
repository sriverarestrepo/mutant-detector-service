package com.melichallenge.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.model.Dna;
import com.melichallenge.mutant.service.MutantService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MutantController {

	@Autowired
	private MutantService mutantService;

	public MutantController(MutantService mutantService) {
		this.mutantService = mutantService;
	}

	@ApiOperation(value = "", notes = "Mutant detection service from a dna array")
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public ResponseEntity<String> isMutant(@RequestBody Dna dna) {

		ResponseEntity<String> response = null;
		try {
			response = mutantService.isMutant(dna);
		} catch (DnaException | RepositoryException e) {
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return response;

	}
}
