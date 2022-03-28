// Test cases for some of the public functions

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class myTests {

	MinMax myMinMax;
	TTTCall myCall;
	String[] initBoard = {"b", "b", "b", "b", "b", "b", "b", "b", "b"};
	String turn = "X";
	String mode = "Expert";
	
	@BeforeEach
	void setup()
	{
		myMinMax = new MinMax(initBoard);
		myCall = new TTTCall(initBoard, turn, mode);
		
	}
	
	@Test
	void movesListTest() {
		
		ArrayList<Node> movesList = myMinMax.findMoves();
		
		//size of the list should be 9
		assertEquals(9, movesList.size(), "Wrong Moves List Size!");
	}
	
	@Test
	void randMoveTest1() {
		
		ArrayList<Node> movesList = myMinMax.findMoves();
		Integer randNum = TTTCall.randMove(movesList);
		
		// the random number should be between 0 and 8
		assertTrue(0 <= randNum, "Value is below 0!");
		assertTrue(8 >= randNum, "Value is above 8!");
	}
	
	@Test
	void randMoveTest2() {
		
		ArrayList<Node> movesList = myMinMax.findMoves();
		Integer randNum = TTTCall.randMove(movesList);
		
		// the random number should be between 0 and 8
		assertTrue(0 <= randNum, "Value is below 0!");
		assertTrue(8 >= randNum, "Value is above 8!");
	}
	
	@Test
	void randMoveTest3() {
		
		ArrayList<Node> movesList = myMinMax.findMoves();
		Integer randNum = TTTCall.randMove(movesList);
		
		// the random number should be between 0 and 8
		assertTrue(0 <= randNum, "Value is below 0!");
		assertTrue(8 >= randNum, "Value is above 8!");
	}

}
