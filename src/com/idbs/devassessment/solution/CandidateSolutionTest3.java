package com.idbs.devassessment.solution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.idbs.devassessment.core.DifficultyLevel;

class CandidateSolutionTest2 {
    
	
	CandidateSolution solution;
	String js;
	
	@Before
	public void setUp() throws Exception {
		
		solution = new CandidateSolution();
		if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_1) {
			js = "{'xValue' : 32, 'terms' : [{ 'power' : 2, 'multiplier' : 4, 'action' : 'subtract' },{ 'power' : 5, 'multiplier' : 6, 'action' : 'add' },{ 'power' : 2, 'multiplier' : 7,  'action' : 'subtract' },{ 'power' : 4, 'multiplier' : 8, 'action' : 'add' },{ 'power' : 5, 'multiplier' : 3, 'action' : 'subtract' },{ 'power' : 6, 'multiplier' : 2, 'action' : 'add' },{ 'power' : 2, 'multiplier' : 0, 'action' : 'subtract' },{ 'power' : 0, 'multiplier' : 6, 'action' : 'add' }]}";
			
		}
		else if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_2) {
			js = "json:{'xValue' : 32, 'terms' : [{ 'power' : 2, 'multiplier' : 4, 'action' : 'subtract' },{ 'power' : 5, 'multiplier' : 6, 'action' : 'add' },{ 'power' : 2, 'multiplier' : 7,  'action' : 'subtract' },{ 'power' : 4, 'multiplier' : 8, 'action' : 'add' },{ 'power' : 5, 'multiplier' : 3, 'action' : 'subtract' },{ 'power' : 6, 'multiplier' : 2, 'action' : 'add' },{ 'power' : 2, 'multiplier' : 0, 'action' : 'subtract' },{ 'power' : 0, 'multiplier' : 6, 'action' : 'add' }]}";
		}
		else if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_3) {
			js = " numeric:x=32;y=-4.x^2+6.x^5-7.x^2+8.x^4-3.x^5+2.x^6-0.x^2+6.x^0";
		}
		else {
			js = "";
		}
	}
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetAnswer2() {
		
		String expected = "2256524294";
		String ans = solution.getAns(js);
		assertEquals(ans,expected);
		
	}

}
