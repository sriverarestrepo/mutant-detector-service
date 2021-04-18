package com.melichallenge.mutant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.melichallenge.mutant.exception.DnaException;
import com.melichallenge.mutant.exception.RepositoryException;
import com.melichallenge.mutant.manager.MutantManager;
import com.melichallenge.mutant.model.Dna;
import com.melichallenge.mutant.service.MutantService;

@Service
public class MutantServiceImpl implements MutantService{

	@Autowired
	private MutantManager mutantManager;

	@Override
	public ResponseEntity<String> isMutant(Dna dna) throws DnaException, RepositoryException {
		HttpStatus httpStatus = (mutantManager.isMutant(dna.getDna())) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
		return new ResponseEntity<>(httpStatus);
	}
}
