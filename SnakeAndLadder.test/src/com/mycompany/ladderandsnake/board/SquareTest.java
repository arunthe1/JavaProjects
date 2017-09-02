package com.mycompany.ladderandsnake.board;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

@Generated(value = "org.junit-tools-1.0.6")
public class SquareTest {

	@Generated(value = "org.junit-tools-1.0.6")
	private Logger logger = Logger.getLogger(SquareTest.class.toString());

	private float defaultEnergyUnits = 10.5f;
	private int defaultPosition =1;
	private  int defaultRowPosition = 0;
	private int defaultColPosition = 0;
	private SquareType defaultSquareType = SquareType.Normal;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	private Square createTestSubject() {
		Square obj = new Square();
		obj.setPosition(defaultPosition);
		obj.setColumnPosition(defaultColPosition);
		obj.setRowPosition(defaultRowPosition);
		obj.setEnergyUnits(defaultEnergyUnits);
		obj.setSquareType(defaultSquareType);
		return obj;
	}

	@MethodRef(name = "getSquareType", signature = "()QSquareType;")
	@Test
	public void testGetSquareType() throws Exception {
		Square testSubject;
		SquareType result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getSquareType();

		Assert.assertTrue(result == defaultSquareType);
	}

	@MethodRef(name = "setSquareType", signature = "(QSquareType;)V")
	@Test
	public void testSetSquareType() throws Exception {
		Square testSubject;
		SquareType squareType = SquareType.Pitstop;

		// default test
		testSubject = createTestSubject();
		testSubject.setSquareType(squareType);

		SquareType result = testSubject.getSquareType();
		Assert.assertTrue(result == squareType);
	}

	@MethodRef(name = "getEnergyUnits", signature = "()F")
	@Test
	public void testGetEnergyUnits() throws Exception {
		Square testSubject;
		float result;

		// default test
		testSubject = createTestSubject();
	
		result = testSubject.getEnergyUnits();

		Assert.assertTrue(result == defaultEnergyUnits);
	}

	@MethodRef(name = "setEnergyUnits", signature = "(F)V")
	@Test
	public void testSetEnergyUnits() throws Exception {
		Square testSubject;
		float energyUnits = 20.45f;

		// default test
		testSubject = createTestSubject();
		testSubject.setEnergyUnits(energyUnits);
		float result = testSubject.getEnergyUnits();

		Assert.assertTrue(result == energyUnits);
		
	}

	@MethodRef(name = "getPosition", signature = "()I")
	@Test
	public void testGetPosition() throws Exception {
		Square testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getPosition();

		Assert.assertTrue(result == defaultPosition);
	}

	@MethodRef(name = "setPosition", signature = "(I)V")
	@Test
	public void testSetPosition() throws Exception {
		Square testSubject;
		int position = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.setPosition(position);

		int result = testSubject.getPosition();

		Assert.assertTrue(result == position);
	}

	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		Square testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();

		StringBuilder builder = new StringBuilder();
		builder.append("Position:");
		builder.append(defaultPosition);
		builder.append("\t");
		builder.append("Energy units:");
		builder.append(defaultEnergyUnits);

		builder.append("\t");
		builder.append("Type:");
		builder.append(defaultSquareType);

		
		String condition =  builder.toString();
		
		Assert.assertEquals(result, condition );
	}

	
	@MethodRef(name = "getRowPosition", signature = "()I")
	@Test
	public void testGetRowPosition() throws Exception {
		Square testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getRowPosition();

		Assert.assertTrue(result == defaultRowPosition);
	}

	@MethodRef(name = "setRowPosition", signature = "(I)V")
	@Test
	public void testSetRowPosition() throws Exception {
		Square testSubject;
		int row = 2;

		// default test
		testSubject = createTestSubject();
		testSubject.setRowPosition(row);

		int result = testSubject.getRowPosition();

		Assert.assertTrue(result == row);
	}

	@MethodRef(name = "getColumnPosition", signature = "()I")
	@Test
	public void testGetColumnPosition() throws Exception {
		Square testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getColumnPosition();

		Assert.assertTrue(result == defaultColPosition);
	}

	@MethodRef(name = "setColumnPosition", signature = "(I)V")
	@Test
	public void testSetColumnPosition() throws Exception {
		Square testSubject;
		int col = 2;

		// default test
		testSubject = createTestSubject();
		testSubject.setColumnPosition(col);

		int result = testSubject.getColumnPosition();

		Assert.assertTrue(result == col);
	}
}