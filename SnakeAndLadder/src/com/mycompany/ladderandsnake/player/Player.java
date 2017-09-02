/**
 * 
 */
package com.mycompany.ladderandsnake.player;

import java.util.Stack;

import com.mycompany.ladderandsnake.board.Square;

/**
 * @author Arun
 *
 */
public class Player {

	private float energyUnits;

	public boolean isMagic;

	private Stack<Square> playerMoves = new Stack<Square>();

	private String playerName;

	private static int playerCount = 0;

	private static Object playerCountLock = new Object();

	private Square currentPosition = null;

	/**
	 * 
	 */
	public Player() {
		setEnergyUnits(0);
		isMagic = false;

		synchronized (playerCountLock) {
			++playerCount;
			setPlayerName("Player" + playerCount);
		}
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

	public void boostEnergy(float energyUnits) {
		this.energyUnits += energyUnits;
	}

	public void addMove(Square currentSquare) {
		if (currentSquare != null) {
			if (currentSquare.getPosition() != 0) {
				playerMoves.push(currentSquare);
			}
		}

	}

	public Square getLastSquare() {
		Square lastSquare = null;

		if (playerMoves.size() > 0) {
			lastSquare = playerMoves.pop();
		}

		return lastSquare;
	}

	public Square getLastSquareBeforeMoves(int moves) {
		Square lastSquare = null;

		if (moves > 0) {
			for (int loop = 0; loop < moves; ++loop) {
				if (playerMoves.size() > 0) {
					lastSquare = playerMoves.pop();
				} else {
					lastSquare = null;
					break;
				}
			}
		}

		return lastSquare;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");
		builder.append(this.getPlayerName());
		builder.append(":");
		builder.append(this.getCurrentPosition().getPosition());
		builder.append(":");
		builder.append(this.getEnergyUnits());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the currentPosition
	 */
	public Square getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition
	 *            the currentPosition to set
	 */
	public void setCurrentPosition(Square currentPosition) {

		this.setCurrentPosition(currentPosition, true);
	}

	public void setCurrentPosition(Square currentPosition, boolean reduceEnergy) {
		this.currentPosition = currentPosition;
		this.addMove(currentPosition);

		if (reduceEnergy == true) {
			--this.energyUnits;
		}
	}
}
