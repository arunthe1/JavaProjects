package com.mycompany.ladderandsnake.board;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.mycompany.ladderandsnake.player.Player;

import mockit.Deencapsulation;

@Generated(value = "org.junit-tools-1.0.6")
public class BoardTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	private Board createTestSubject() throws Exception {
		return new Board(36, 2);
	}

	@MethodRef(name = "setSnake", signature = "(III)V")
	@Test
	public void testSetSnake() throws Exception {
		Board testSubject;
		int headPosition = 0;
		int tailPosition = 0;
		int hunger = 0;

		// test 1
		testSubject = createTestSubject();
		headPosition = 28;
		tailPosition = 15;
		hunger = 4;
		testSubject.setSnake(headPosition, tailPosition, hunger);

		// test 2
		testSubject = createTestSubject();
		headPosition = 17;
		tailPosition = 2;
		hunger = 2;
		testSubject.setSnake(headPosition, tailPosition, hunger);

	}

	@MethodRef(name = "setSnake", signature = "(III)V")
	@Test(expected = Exception.class)
	public void testSetSnakeNegative() throws Exception {
		Board testSubject;
		int headPosition = 0;
		int tailPosition = 0;
		int hunger = 0;

		// test 3
		testSubject = createTestSubject();
		headPosition = 3;
		tailPosition = 2;
		hunger = 2;
		testSubject.setSnake(headPosition, tailPosition, hunger);

		// test 4
		testSubject = createTestSubject();
		tailPosition = 10;
		headPosition = 2;
		hunger = 3;
		testSubject.setSnake(headPosition, tailPosition, hunger);

	}

	@MethodRef(name = "setLadder", signature = "(II)V")
	@Test
	public void testSetLadder() throws Exception {
		Board testSubject;
		int headPosition = 0;
		int tailPosition = 0;

		// test 1
		testSubject = createTestSubject();
		headPosition = 24;
		tailPosition = 35;
		testSubject.setLadder(headPosition, tailPosition);

		// test 2

		headPosition = 4;
		tailPosition = 20;
		testSubject.setLadder(headPosition, tailPosition);

	}

	@MethodRef(name = "setLadder", signature = "(II)V")
	@Test(expected = Exception.class)
	public void testSetLadderNegative() throws Exception {
		Board testSubject;
		int headPosition = 0;
		int tailPosition = 0;

		// test 1
		testSubject = createTestSubject();

		headPosition = 5;
		tailPosition = 2;
		testSubject.setLadder(headPosition, tailPosition);

		// test 4

		tailPosition = 5;
		headPosition = 20;
		testSubject.setLadder(headPosition, tailPosition);

	}

	@MethodRef(name = "setMagic", signature = "(I)V")
	@Test
	public void testSetMagic() throws Exception {
		Board testSubject;
		int position = 15;

		// default test
		testSubject = createTestSubject();
		testSubject.setMagic(position);
	}

	@MethodRef(name = "initPlayer", signature = "(QPlayer;)V")
	@Test
	public void testInitPlayer() throws Exception {
		Board testSubject;
		Player player = null;

		// test 1
		testSubject = createTestSubject();
		player =  new Player();;
		Deencapsulation.invoke(testSubject, "initPlayer", new Object[] { player });
	}
}