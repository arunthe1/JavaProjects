package com.mycompany.ladderandsnake.operator;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.mycompany.ladderandsnake.board.Board;
import com.mycompany.ladderandsnake.board.Square;
import com.mycompany.ladderandsnake.board.SquareType;
import com.mycompany.ladderandsnake.player.Player;

import mockit.Deencapsulation;

@Generated(value = "org.junit-tools-1.0.6")
public class LadderTest {

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

	private Ladder createTestSubject(int bottomEntry, int topEntry) {
		Square bottom = new Square();
		bottom.setPosition(bottomEntry);
		bottom.setRowPosition(2);
		bottom.setSquareType(SquareType.LadderStart);
		
		Square top = new Square();
		top.setPosition(topEntry);
		top.setRowPosition(4);
		top.setSquareType(SquareType.LadderEnd);
		
		player.setCurrentPosition(bottom);
		
		return new Ladder(bottom, top);
	}

	@MethodRef(name = "isBeingUsed", signature = "()Z")
	@Test
	public void testIsBeingUsed() throws Exception {
		Ladder testSubject;
		boolean result;

		// default test
		testSubject = createTestSubject(24, 35);
		result = testSubject.isBeingUsed();
		
		Assert.assertTrue(result == false);
		
		testSubject.setBeingUsed(true);
		result = testSubject.isBeingUsed();
		Assert.assertTrue(result == true);
	}

	@MethodRef(name = "isValid", signature = "()Z")
	@Test
	public void testIsValid() throws Exception {
		Ladder testSubject;
		boolean result;

		// default test
		testSubject = createTestSubject(24, 35);
		result = testSubject.isValid();
		Assert.assertTrue(result == true);
		
		testSubject = createTestSubject(36, 25);
		result = testSubject.isValid();
		Assert.assertTrue(result == false);
		
//		testSubject = createTestSubject(1, 25);
//		result = testSubject.isValid();
//		Assert.assertTrue(result == false);
	}

	@MethodRef(name = "performAction", signature = "(QPlayer;Z)Z")
	@Test
	public void testPerformAction() throws Exception {
		Ladder testSubject;
		
		boolean reduceEnergy = true;
		boolean result;
		float initialEnergy = 0.0f;
		float reducedEnergy = 0.0f;

		// default test
		testSubject = createTestSubject(24,35);
		initialEnergy = player.getEnergyUnits();
		reducedEnergy = initialEnergy - 1;
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 35);
		Assert.assertTrue(player.getEnergyUnits() == reducedEnergy);
		
		reduceEnergy = false;
		testSubject = createTestSubject(24,35);
		initialEnergy = player.getEnergyUnits();
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 35);
		Assert.assertTrue(player.getEnergyUnits() == initialEnergy);
		
		reduceEnergy = true;
		testSubject = createTestSubject(24,35);
		initialEnergy = player.getEnergyUnits();
		reducedEnergy = initialEnergy - 1;
		Square top = new Square();
		top.setPosition(35);
		top.setSquareType(SquareType.LadderEnd);
		player.isMagic = true;
		player.setCurrentPosition(top,false);
		result = testSubject.performAction(player, reduceEnergy);
		Assert.assertTrue(result == true);
		Assert.assertTrue(player.getCurrentPosition().getPosition() == 24);
		Assert.assertTrue(player.getEnergyUnits() == reducedEnergy);
	}
}