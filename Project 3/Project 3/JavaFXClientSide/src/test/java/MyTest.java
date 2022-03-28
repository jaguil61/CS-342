/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * Will test the BaccaratInfo class
 * 
 * MyTest.java
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	static BaccaratInfo theInfo;
	
	@BeforeAll
	static void setup()
	{
		theInfo = new BaccaratInfo("ServerInfo");
	}
	
	@Test
	void test1() 
	{
		assertEquals("ServerInfo", theInfo.getType(), "Wrong Type!");
	}

	@Test
	void test2()
	{
		theInfo.winner = "Player";
		
		assertEquals("Player", theInfo.winner, "Wrong Winner!");
	}
}
