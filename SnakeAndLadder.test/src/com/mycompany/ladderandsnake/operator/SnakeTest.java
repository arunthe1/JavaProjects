package com.mycompany.ladderandsnake.operator;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.tools.configuration.base.MethodRef;

import com.mycompany.ladderandsnake.board.Board;
import com.mycompany.ladderandsnake.board.Square;
import com.mycompany.ladderandsnake.board.SquareType;
import com.mycompany.ladderandsnake.player.Player;


import mockit.Deencapsulation;

@Generated(value = "org.junit-tools-1.0.6")
public class SnakeTest {

	Board board = null;
	Player player = null;
	@Before
	public void setUp() throws Exception {
		board = new Board(36,2);
		player = new Player();
		Deencapsulation.invoke(board, "initPlayer", new Object[] { player });
	}

	@After
	public void tearDown() throws Exception {

	}

	private Snake createTestSubject(int snaketail, int snakehead, int hunger) {
		Square tail = new Square();
		tail.setPosition(snaketail);
		tail.setRowPosition(2);
		
		tail.setSquareType(SquareType.SnakeEnd);
		
		Square head = new Square();
		head.setPosition(snakehead);
		head.setRowPosition(4);
		head.setSquareType(SquareType.SnakeStart);
		
		player.setCurrentPosition(head);
		return new Snake(head, tail, hunger);
	}

	@MethodRef(name = "isValid", signature = "()Z")
	@Test
	public void testIsValid() throws Exception {
		Snake testSubject;
		boolean result;

		
		// default test
		
		testSubject = createTestSubject(15,28,4);
		result = testSubject.isValid();
		Assert.assertTrue(result == true);
		
		testSubject = createTestSubject(28, 15, 5);
		result = testSubject.isValid();
		Assert.assertTrue(result == false);
		
//		testSubject = createTestSubject(1, 25, 5);
//		result = testSubject.isValid();
//		Assert.assertTrue(result == false);
	}

	@MethodRef(name = "performAction", signature = "(QPlayer;Z)Z")
	@Test
	public void testPerformAction() throws Exception {
		Snake testSubject;
		boolean reduceEnergy = true;
		boolean result;
		float initialEnergy = 0.0f;
		float reducedEnergy = 0.0f;

		// default test
		testSubject = createTestSubject(15,28,4);
		initialEnergy = player.getEnergyUnits();
		reducedEnergy = initialEnergy - 1;
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 15);
		Assert.assertTrue(player.getEnergyUnits() == reducedEnergy);
		
		reduceEnergy = false;
		testSubject =createTestSubject(15,28,4);
		initialEnergy = player.getEnergyUnits();
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 15);
		Assert.assertTrue(player.getEnergyUnits() == initialEnergy);
		
		reduceEnergy = true;
		testSubject = createTestSubject(15,28,4);
		initialEnergy = player.getEnergyUnits();
		reducedEnergy = initialEnergy - 1;
		Square top = new Square();
		top.setPosition(15);
		top.setSquareType(SquareType.SnakeEnd);
		player.isMagic = true;
		player.setCurrentPosition(top,false);
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 28);
		Assert.assertTrue(player.getEnergyUnits() == reducedEnergy);
	}
}