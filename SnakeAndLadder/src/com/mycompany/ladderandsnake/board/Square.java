/**
 * 
 */
package com.mycompany.ladderandsnake.board;

/**
 * @author Arun
 *
 */
public class Square {

	/**
	 * This class defines a square in the board.
	 */

	private SquareType squareType;
	private float energyUnits;
	private int position;
	private int row;
	private int col;

	public Square() {
		setSquareType(SquareType.Normal);
		setEnergyUnits(0);
	}

	public Square(SquareType type, float energy) {
		setSquareType(type);
		setEnergyUnits(energy);
	}

	/**
	 * @return the squareType
	 */
	public SquareType getSquareType() {
		return squareType;
	}

	/**
	 * @param squareType
	 *            the squareType to set
	 */
	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}

	/**
	 * @return the energyUnits
	 */
	public float getEnergyUnits() {
		return energyUnits;
	}

	/**
	 * @param energyUnits
	 *            the energyUnits to set
	 */
	public void setEnergyUnits(float energyUnits) {
		this.energyUnits = energyUnits;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position:");
		builder.append(this.getPosition());
		builder.append("\t");
		builder.append("Energy units:");
		builder.append(this.getEnergyUnits());

		builder.append("\t");
		builder.append("Type:");
		builder.append(this.getSquareType());

		return builder.toString();
	}

	/**
	 * @return the row
	 */
	public int getRowPosition() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRowPosition(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getColumnPosition() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setColumnPosition(int col) {
		this.col = col;
	}

}
