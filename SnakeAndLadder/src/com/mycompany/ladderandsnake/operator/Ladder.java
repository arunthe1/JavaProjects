/**
 * 
 */
package com.mycompany.ladderandsnake.operator;

import com.mycompany.ladderandsnake.board.Square;
import com.mycompany.ladderandsnake.player.Player;

/**
 * @author Arun
 *
 */
public class Ladder implements IBoardOperator {

	private Square bottom = null;
	private Square top = null;
	private String identifier = null;
	private boolean isBeingUsed = false;

	public boolean isBeingUsed() {
		return isBeingUsed;
	}

	public void setBeingUsed(boolean isBeingUsed) {
		this.isBeingUsed = isBeingUsed;
	}

	/**
	 * 
	 */
	public Ladder(Square Bottom, Square Top) {
		if (Bottom == null || Top == null)
			return;

		bottom = Bottom;
		top = Top;

		StringBuilder builder = new StringBuilder();
		builder.append("L-");
		builder.append(bottom.getPosition());
		builder.append("-");
		builder.append(top.getPosition());
		identifier = builder.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycompany.ladderandsnake.IBoardOperator#performAction()
	 */
	@Override
	public boolean performAction(Player player, boolean reduceEnergy) throws Exception {

		// can not move the player if the prev player is still on the ladder
		if (isBeingUsed) {

			return false;
		}

		isBeingUsed = true;

		if (player.getCurrentPosition().getPosition() == top.getPosition()) {
			// possiblity of magic operation
			if (player.isMagic) {

				player.setCurrentPosition(bottom, reduceEnergy);
			}
		} else {
			if (!player.isMagic) {
				player.setCurrentPosition(top, reduceEnergy);
			}

		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycompany.ladderandsnake.IBoardOperator#isValid()
	 */
	@Override
	public boolean isValid() throws Exception {
		// bottom < top
		// bottom != top
		// bottom and top are not in same row

		if (bottom == null || top == null) {
			throw new Exception("Invalid Configuration for Ladder");
		}
		int low = bottom.getPosition();
		int high = top.getPosition();

		if (low >= high) {
			return false;
		}

		if (bottom.getRowPosition() >= top.getRowPosition()) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycompany.ladderandsnake.IBoardOperator#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return this.identifier;
	}

}
