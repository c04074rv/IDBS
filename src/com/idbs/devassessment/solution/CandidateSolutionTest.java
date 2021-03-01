package com.idbs.devassessment.solution;

import static org.junit.Assert.*;
import org.junit.Test;

import com.idbs.devassessment.core.DifficultyLevel;

public class CandidateSolutionTest {

	CandidateSolution solution;
	String js;
	
	@Before
	public void setup() throws Exception{
		
		solution = new CandidateSolution();
		if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_1) {
			js = "{'xValue' : 23, 'terms' : [{ 'power' : 3, 'multiplier' : 4, 'action' : 'add' },{ 'power' : 6, 'multiplier' : 9, 'action' : 'subtract' },{ 'power' : 5, 'multiplier' : 7,  'action' : 'add' },{ 'power' : 1, 'multiplier' : 2, 'action' : 'add' },{ 'power' : 7, 'multiplier' : 6, 'action' : 'subtract' },{ 'power' : 3, 'multiplier' : 4, 'action' : 'subtract' },{ 'power' : 2, 'multiplier' : 0, 'action' : 'subtract' },{ 'power' : 0, 'multiplier' : 6, 'action' : 'add' }]}";
			
		}
		else if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_2) {
			js = "json:{'xValue' : 23, 'terms' : [{ 'power' : 3, 'multiplier' : 4, 'action' : 'add' },{ 'power' : 6, 'multiplier' : 9, 'action' : 'subtract' },{ 'power' : 5, 'multiplier' : 7,  'action' : 'add' },{ 'power' : 1, 'multiplier' : 2, 'action' : 'add' },{ 'power' : 7, 'multiplier' : 6, 'action' : 'subtract' },{ 'power' : 3, 'multiplier' : 4, 'action' : 'subtract' },{ 'power' : 2, 'multiplier' : 0, 'action' : 'subtract' },{ 'power' : 0, 'multiplier' : 6, 'action' : 'add' }]}";
		}
		else if(solution.getDifficultyLevel() == DifficultyLevel.LEVEL_3) {
			js = " numeric:x=23;y=+4.x^3-9.x^6+7.x^5+2.x^1-6.x^7-4.x^3-0.x^2+6.x^0";
		}
		else {
			js = "";
		}
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testgetAnswer() {
		
		String expected = "-21716221230";
		String ans = solution.getAns(js);
		assertEquals(ans,expected);
		
	}

}
