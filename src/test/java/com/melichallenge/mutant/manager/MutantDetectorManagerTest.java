package com.melichallenge.mutant.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MutantDetectorManagerTest {

	@Autowired
	private MutantDetectorManager mutantDetectorManager;

	@Test
	public void oneFoundOfEveryShape() {
		String[] dnaStr = new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void oneFoundOfHorizontalShape() {
		String[] dnaStr = new String[] { "AAAA", "TCGA", "ATCG", "GTCA" };
		int matchsCountExpected = 1;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void moreOneFoundOfHorizontalShape() {
		String[] dnaStr = new String[] { "AAAA", "TTTT", "ATCG", "GGGG" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void oneFoundOfVerticalShape() {
		String[] dnaStr = new String[] { "AAGA", "AGCC", "ACTT", "ATAG" };
		int matchsCountExpected = 1;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void moreOneFoundOfVerticalShape() {
		String[] dnaStr = new String[] { "AAGC", "AGCC", "ACTC", "ATAC" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void oneFoundOfObliqueShape() {
		String[] dnaStr = new String[] { "ACAA", "TAGA", "ATAG", "GTCA" };
		int matchsCountExpected = 1;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}

	@Test
	public void moreOneFoundOfObliqueShape() {
		String[] dnaStr = new String[] { "ATGG", "CAGT", "TGAT", "GCAA" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}
	

	@Test
	public void moreOneFoundOfObliqueShapeUpDown() {
		String[] dnaStr = new String[] { "AGAAG", "TAGAT", "ATAGT", "GTCAG","AAGGC" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}
	
	@Test
	public void moreOneFoundOfObliqueShapeDownUP() {
		String[] dnaStr = new String[] { "GTTGC", "AAGAG", "AGACG", "GATTA", "ATAGA" };
		int matchsCountExpected = 2;
		int matchsCountResult = mutantDetectorManager.detectMutantPatterns(dnaStr);
		assertEquals(matchsCountExpected, matchsCountResult);
	}
}
