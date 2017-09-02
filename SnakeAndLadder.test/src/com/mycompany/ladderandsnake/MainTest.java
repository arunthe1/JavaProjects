package com.mycompany.ladderandsnake;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;
import org.junit.Assert;

import com.mycompany.ladderandsnake.board.Board;
import com.mycompany.ladderandsnake.board.Square;
import com.mycompany.ladderandsnake.board.SquareType;


import mockit.Deencapsulation;

@Generated(value = "org.junit-tools-1.0.6")
public class MainTest {

	private Board board = null;

	@Before
	public void setUp() throws Exception {
		board = new Board(36, 2);
	}

	@After
	public void tearDown() throws Exception {

	}

	private Main createTestSubject() {
		return new Main();
	}

	@MethodRef(name = "setBoardConfiguration", signature = "(QBoard;QString;)V")
	@Test
	public void testSetBoardConfiguration() throws Exception {
		Main testSubject;

		String configuration = "S 15 28 4";

		// test 1
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] { board, configuration });

		Square square = Deencapsulation.invoke(board, "getSquareatPosition", 15);
		Assert.assertTrue(square.getSquareType() == SquareType.SnakeEnd);
		square = Deencapsulation.invoke(board, "getSquareatPosition", 28);
		Assert.assertTrue(square.getSquareType() == SquareType.SnakeStart);

		// test 2
		configuration = "L 24 35";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] { board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 24);
		Assert.assertTrue(square.getSquareType() == SquareType.LadderStart);

		square = Deencapsulation.invoke(board, "getSquareatPosition", 35);
		Assert.assertTrue(square.getSquareType() == SquareType.LadderEnd);

		// test 3
		configuration = "P 19 5";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] { board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 19);
		Assert.assertTrue(square.getSquareType() == SquareType.Pitstop);
		Assert.assertTrue(square.getEnergyUnits() == 5.0f);

		// test 4
		configuration = "ME 22";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] { board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 22);
		Assert.assertTrue(square.getSquareType() == SquareType.Memory);

		// test 5
		configuration = "MA 23";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] { board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 23);
		Assert.assertTrue(square.getSquareType() == SquareType.Magic);

		// test 6
		configuration = "T 10";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] {board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 10);
		Assert.assertTrue(square.getSquareType() == SquareType.Trampoline);

		// test 7
		configuration = "E 5";
		testSubject = createTestSubject();
		Deencapsulation.invoke(testSubject, "setBoardConfiguration", new Object[] {board, configuration });

		square = Deencapsulation.invoke(board, "getSquareatPosition", 5);
		Assert.assertTrue(square.getSquareType() == SquareType.Elevator);
	}
}