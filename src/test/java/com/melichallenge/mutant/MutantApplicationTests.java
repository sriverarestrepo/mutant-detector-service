package com.melichallenge.mutant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MutantApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		MutantApplication.main(getArgs());
	}

	private String[] getArgs() {
		String[] args = {};
		return args;
	}
}
