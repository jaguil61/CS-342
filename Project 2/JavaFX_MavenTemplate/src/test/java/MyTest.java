/* 
 * CS 342 Project 2 (test cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * MyTest.java
 * 
 * In this project you will implement the popular casino and state lottery game, Keno.
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	static JavaFXTemplate app;
	
	@BeforeAll
	static void setup()
	{
		app = new JavaFXTemplate();
	}
	
	// tests for the randomNums() method
	@Test
	void twentyRandomTest() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// the length should be 20
		assertEquals(20, randomTestArray.length, "Wrong length!");
	}
	
	@Test
	void tenRandomTest() 
	{
		int randomTestArray[] = app.randomNums(10);
		
		// the length should be 10
		assertEquals(10, randomTestArray.length, "Wrong length!");
	}
	
	@Test
	void eightRandomTest() 
	{
		int randomTestArray[] = app.randomNums(8);
		
		// the length should be 8
		assertEquals(8, randomTestArray.length, "Wrong length!");
	}
	
	@Test
	void fourRandomTest() 
	{
		int randomTestArray[] = app.randomNums(4);
		
		// the length should be 4
		assertEquals(4, randomTestArray.length, "Wrong length!");
	}
	
	@Test
	void oneRandomTest() 
	{
		int randomTestArray[] = app.randomNums(1);
		
		// the length should be 1
		assertEquals(1, randomTestArray.length, "Wrong length!");
	}
	// END OF length tests
	
	//Tests to make sure each number in the array is between 1-80
	@Test
	void twentyRandomTest2() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// each number must be between 1-80
		for (int i : randomTestArray)
			assertTrue(1 <= i && i <= 80, "Value " + " was not in range!");
	}
	
	@Test
	void tenRandomTest2() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// each number must be between 1-80
		for (int i : randomTestArray)
			assertTrue(1 <= i && i <= 80, "Value " + " was not in range!");
	}
	
	@Test
	void eightRandomTest2() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// each number must be between 1-80
		for (int i : randomTestArray)
			assertTrue(1 <= i && i <= 80, "Value " + " was not in range!");
	}
	
	@Test
	void fourRandomTest2() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// each number must be between 1-80
		for (int i : randomTestArray)
			assertTrue(1 <= i && i <= 80, "Value " + " was not in range!");
	}
	
	@Test
	void oneRandomTest2() 
	{
		int randomTestArray[] = app.randomNums(20);
		
		// each number must be between 1-80
		for (int i : randomTestArray)
			assertTrue(1 <= i && i <= 80, "Value " + " was not in range!");
	}
	// END OF range tests
	
	// Tests the isInArray() method
	@Test
	void isInArrayTest()
	{
		int testArr[] = {1, 2, 3, 4 , 5};
		
		// should return true because 3 is in the array
		assertTrue(app.isInArray(testArr, 3), "3 was not found in array!");
	}
	
	@Test
	void isInArrayTest2()
	{
		int testArr[] = {1, 2, 3, 4 , 5};
		
		// should return false because 10 is not in the array
		assertFalse(app.isInArray(testArr, 10), "10 was found in array!");
	}
	// END OF inInArray() Tests
	
	// Tests for the matchingNums() method
	@Test
	void matchingNumsTest()
	{
		int winningNums[] = {1, 2, 3, 4, 5};
		Integer chosenNums[] = {3, 2, 1, 7, 0};
		ArrayList<Integer> matchedNums = new ArrayList<>();
		
		// should return 3 matched numbers
		assertEquals(3, app.matchingNums(winningNums, chosenNums, matchedNums), "Incorrect numbers matched!");
	}
	
	@Test
	void matchingNumsTest2()
	{
		int winningNums[] = {10, 2, 3, 1, 15};
		Integer chosenNums[] = {3, 2, 1, 7, 0, 9, 20};
		Integer correctNumsMatched[] = {3, 2, 1};
		ArrayList<Integer> matchedNums = new ArrayList<>();
		
		// should return 3 matched numbers
		assertEquals(3, app.matchingNums(winningNums, chosenNums, matchedNums), "Incorrect numbers matched!");
		
		int iter = 0;
		
		for (Integer i : matchedNums)
		{
			// the array list should have the values from correctNumsMatched[]
			assertEquals(correctNumsMatched[iter], i, i + " was not in the array!");
			iter++;
		}
	}
	// END OF matchingNums() tests
	
	// Tests for the currentWinnings() method
	@Test
	void currentWinningsTest()
	{
		int spotsPlayed = 10;
		int matched = 10;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 100,000
		assertEquals(100000, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest2()
	{
		int spotsPlayed = 10;
		int matched = 7;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 40
		assertEquals(40, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest3()
	{
		int spotsPlayed = 8;
		int matched = 8;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 10000
		assertEquals(10000, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest4()
	{
		int spotsPlayed = 8;
		int matched = 5;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 12
		assertEquals(12, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest5()
	{
		int spotsPlayed = 4;
		int matched = 4;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 75
		assertEquals(75, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest6()
	{
		int spotsPlayed = 4;
		int matched = 0;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 0
		assertEquals(0, score, "Score was in correct!");
	}
	
	@Test
	void currentWinningsTest7()
	{
		int spotsPlayed = 1;
		int matched = 1;
		int score = 0;
		
		score = app.currentWinnings(spotsPlayed, matched);
		
		// the score should be 2
		assertEquals(2, score, "Score was in correct!");
	}
	// END OF currentWinnings() tests
	
	// Tests for the ten, eight, four, and oneSpot() methods
	@Test
	void tenSpotsTest()
	{
		int matched = 0;
		int score = 0;
		
		score = app.tenSpots(matched);
		
		// should return the score 5
		assertEquals(5, score, "Incorrect score!");
	}
	
	@Test
	void eightSpotsTest()
	{
		int matched = 4;
		int score = 0;
		
		score = app.eightSpots(matched);
		
		// should return the score 2
		assertEquals(2, score, "Incorrect score!");
	}
	
	@Test
	void fourSpotsTest()
	{
		int matched = 3;
		int score = 0;
		
		score = app.fourSpots(matched);
		
		// should return the score 5
		assertEquals(5, score, "Incorrect score!");
	}
	
	@Test
	void oneSpotTest()
	{
		int matched = 0;
		int score = 0;
		
		score = app.oneSpot(matched);
		
		// should return the score 0
		assertEquals(0, score, "Incorrect score!");
	}
	
}
