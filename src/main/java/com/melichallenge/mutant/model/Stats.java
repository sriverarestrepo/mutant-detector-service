package com.melichallenge.mutant.model;

public class Stats {
	
	private double count_mutant_dna;
	
	private double count_human_dna;
	
	private double ratio;
	
	
	public Stats(double count_mutant_dna, double count_human_dna) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = (count_mutant_dna > 0.0)?Math.round((count_mutant_dna / count_human_dna)* 10.0)/10.0 :0.0;;
	}

	public double getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public double getCount_human_dna() {
		return count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}
		
}
