package com.melichallenge.mutant.manager.impl;

import org.springframework.stereotype.Component;

import com.melichallenge.mutant.manager.MutantDetectorManager;

@Component
public class MutantDetectorManagerImpl implements MutantDetectorManager {

	private static final int MIN_VALUE_MATCHES = 2;

	private static final int MIN_VALUE_DNA = 4;

	private static final int PATTERN_MUTANT_DNA = 4;

	public int detectMutantPatterns(String[] dna) {
		int matchsCount = 0;
		String[][] dnaTable = loadDnaStructureToTable(dna);

		matchsCount = findRepeatedSeqHorizontalShape(matchsCount, dnaTable);
		if (matchsCount < MIN_VALUE_MATCHES) {
			matchsCount = findRepeatedSeqVerticalShape(matchsCount, dnaTable);

			if (matchsCount < MIN_VALUE_MATCHES) {
				matchsCount = findRepeatedSeqObliqueShape((dna.length - MIN_VALUE_DNA), 0, +1, matchsCount, dnaTable);

				if (matchsCount < MIN_VALUE_MATCHES) {
					matchsCount = findRepeatedSeqObliqueShape((dna.length - 1), (dna.length - 1), -1, matchsCount,
							dnaTable);
				}
			}
		}

		return matchsCount;
	}

	private String[][] loadDnaStructureToTable(String[] dna) {

		int dnaSize = dna.length;
		String[][] dnaTable = new String[dnaSize][dnaSize];

		for (int row = 0; row < dnaSize; row++) {
			String dnaRow = dna[row];
			String[] dnaCol = dnaRow.split("");
			for (int col = 0; col < dnaSize; col++) {
				dnaTable[row][col] = dnaCol[col];
			}
		}
		return dnaTable;
	}

	private int findRepeatedSeqVerticalShape(int matchsCount, String[][] dnaTable) {
		for (int col = 0; col < dnaTable.length; col++) {
			for (int row = 0; row <= (dnaTable.length - MIN_VALUE_DNA); row++) {
				if (verticalShapeAnalyze(row, col, dnaTable)) {
					matchsCount++;
					if (matchsCount >= MIN_VALUE_MATCHES) {
						return matchsCount;
					}
				}
			}
		}
		return matchsCount;
	}

	private boolean verticalShapeAnalyze(int row, int col, String[][] dnaTable) {
		for (int k = (row + PATTERN_MUTANT_DNA - 1); k >= row; k--) {
			if (!dnaTable[row][col].equals(dnaTable[k][col])) {
				return false;
			}
		}
		return true;
	}

	private int findRepeatedSeqHorizontalShape(int matchsCount, String[][] dnaTable) {
		for (int row = 0; row < dnaTable.length; row++) {
			for (int col = 0; col <= (dnaTable.length - MIN_VALUE_DNA); col++) {
				if (horizontalShapeAnalyze(row, col, dnaTable)) {
					matchsCount++;
					if (matchsCount >= MIN_VALUE_MATCHES) {
						return matchsCount;
					}
				}
			}
		}
		return matchsCount;
	}

	private static boolean horizontalShapeAnalyze(int row, int col, String[][] dnaTable) {
		for (int k = (col + PATTERN_MUTANT_DNA - 1); k >= col; k--) {
			if (!dnaTable[row][col].equals(dnaTable[row][k])) {
				return false;
			}
		}
		return true;
	}

	private int findRepeatedSeqObliqueShape(int rowEnd, int colPosition, int direction, int matchsCount,
			String[][] dnaTable) {
		int iterationIni = 0;

		for (int i = 0; i <= rowEnd; i++) {
			if (i == 0) {

				if (direction == 1) {
					for (int j = (dnaTable.length - MIN_VALUE_DNA); j >= 0; j--) {
						iterationIni++;
						if (obliqueShapeAnalyze(i, j, iterationIni, direction, dnaTable)) {
							matchsCount++;
							if (matchsCount >= MIN_VALUE_MATCHES) {
								return matchsCount;
							}
						}
					}
				}

				if (direction == -1) {
					for (int j = (MIN_VALUE_DNA - 1); j <= dnaTable.length - 1; j++) {
						iterationIni++;
						if (obliqueShapeAnalyze(i, j, iterationIni, direction, dnaTable)) {
							matchsCount++;
							if (matchsCount >= MIN_VALUE_MATCHES) {
								return matchsCount;
							}
						}
					}
				}
			} else {
				iterationIni--;

				if (iterationIni > 0 && obliqueShapeAnalyze(i, colPosition, iterationIni, direction, dnaTable)) {
					matchsCount++;
					if (matchsCount >= MIN_VALUE_MATCHES) {
						return matchsCount;
					}
				}
			}
		}
		return matchsCount;
	}

	private boolean obliqueShapeAnalyze(int row, int col, int interate, int direction, String[][] dnaTable) {
		for (int q = 0; q < interate; q++) {
			for (int k = (MIN_VALUE_DNA - 1); k > 0; k--) {

				int rowIni = col + direction * q;
				int colIni = row + q;

				int rowIte = col + direction * q + direction * k;
				int colIte = row + q + k;

				if (!dnaTable[rowIni][colIni].equals(dnaTable[rowIte][colIte])) {
					break;
				}

				if (k == 1) {
					return true;
				}
			}
		}
		return false;
	}

}
