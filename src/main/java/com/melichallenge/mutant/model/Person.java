package com.melichallenge.mutant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Person")
public class Person {

	@Id
	private String id;
	
	private String dna;
	
	private boolean isMutant;

	
	public Person(String dna, boolean isMutant) {
		super();
		this.dna = dna;
		this.isMutant = isMutant;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
	
}
