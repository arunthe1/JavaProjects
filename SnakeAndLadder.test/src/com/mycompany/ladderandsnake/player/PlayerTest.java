package com.mycompany.ladderandsnake.player;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.mycompany.ladderandsnake.board.Square;
import com.mycompany.ladderandsnake.board.SquareType;

@Generated(value = "org.junit-tools-1.0.6")
public class PlayerTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	private Player createTestSubject() {
		return new Player();
	}

	@MethodRef(name = "boostEnergy", signature = "(F)V")
	@Test
	public void testBoostEnergy() throws Exception {
		Player testSubject;
		float energyUnits = 10;

		// default test
		testSubject = createTestSubject();
		testSubject.boostEnergy(energyUnits);
		float afterEnergy = testSubject.getEnergyUnits();
		Assert.assertTrue(energyUnits == afterEnergy);
	}

	@MethodRef(name = "setCurrentPosition", signature = "(QSquare;)V")
	@Test
	public void testSetCurrentPosition() throws Exception {
		Player testSubject;

		Square bottom = new Square();
		bottom.setPosition(25);
		bottom.setRowPosition(2);
		bottom.setSquareType(SquareType.LadderStart);

		// default test
		testSubject = createTestSubject();
		testSubject.setEnergyUnits(10f);
		testSubject.setCurrentPosition(bottom);

		Square afterSet = testSubject.getCurrentPosition();
		Assert.assertTrue(afterSet.getPosition() == 25);
		Assert.assertTrue(testSubject.getEnergyUnits() == 9.0f);
	}

	@MethodRef(name = "setCurrentPosition", signature = "(QSquare;Z)V")
	@Test
	public void testSetCurrentPosition_1() throws Exception {
		Player testSubject;
		Square bottom = new Square();
		bottom.setPosition(25);
		bottom.setRowPosition(2);
		bottom.setSquareType(SquareType.LadderStart);
		boolean reduceEnergy = false;

		// default test
		testSubject = createTestSubject();
		testSubject.setEnergyUnits(10f);
		testSubject.setCurrentPosition(bottom, reduceEnergy);
		Square afterSet = testSubject.getCurrentPosition();
		Assert.assertTrue(afterSet.getPosition() == 25);
		Assert.assertTrue(testSubject.getEnergyUnits() == 10.0f);
	}

	@MethodRef(name = "getLastSquareBeforeMoves", signature = "(I)QSquare;")
	@Test
	public void testGetLastSquareBeforeMoves() throws Exception {
		Player testSubject;
		int moves = 0;
		Square result;

		Square postion1 = new Square();
		postion1.setPosition(25);
		postion1.setRowPosition(2);
		postion1.setSquareType(SquareType.Normal);

		Square position2 = new Square();
		position2.setPosition(27);
		position2.setRowPosition(2);
		position2.setSquareType(SquareType.Normal);

		// test 1
		testSubject = createTestSubject();
		testSubject.addMove(postion1);
		testSubject.addMove(position2);
		moves = 1;
		result = testSubject.getLastSquareBeforeMoves(moves);
		Assert.assertEquals(position2, result);

		// test 2
		testSubject = createTestSubject();
		testSubject.addMove(postion1);
		testSubject.addMove(position2);
		moves = 2;
		result = testSubject.getLastSquareBeforeMoves(moves);
		Assert.assertEquals(postion1, result);

		// test 3
		testSubject = createTestSubject();
		testSubject.addMove(postion1);
		testSubject.addMove(position2);
		moves = -1;
		result = testSubject.getLastSquareBeforeMoves(moves);
		Assert.assertEquals(null, result);

		// test 4
		testSubject = createTestSubject();
		testSubject.addMove(postion1);
		testSubject.addMove(position2);
		moves = 0;
		result = testSubject.getLastSquareBeforeMoves(moves);
		Assert.assertEquals(null, result);
	}

}