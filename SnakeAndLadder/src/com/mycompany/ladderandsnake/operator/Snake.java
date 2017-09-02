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
public class Snake implements IBoardOperator {

	private Square head = null;
	private Square tail = null;
	private int hungerLevel = 0;
	private String identifier = null;

	/**
	 * 
	 */
	public Snake(Square Head, Square Tail, int Hunger) {

		if (Head == null || Tail == null)
			return;

		head = Head;
		tail = Tail;
		hungerLevel = Hunger;
		StringBuilder builder = new StringBuilder();
		builder.append("S-");
		builder.append(head.getPosition());
		builder.append("-");
		builder.append(tail.getPosition());
		builder.append("-");
		builder.append(hungerLevel);
		identifier = builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycompany.ladderandsnake.IBoardOperator#performAction()
	 */
	@Override
	public boolean performAction(Player player, boolean reduceEnergy) throws Exception {

		// TODO: need to take care of magic

		boolean returnValue = false;
		if (hungerLevel <= 0)
			return false;
		else {
			--hungerLevel;

			if (player.getCurrentPosition().getPosition() != tail.getPosition()) {
				if (!player.isMagic) {
					player.setCurrentPosition(tail, reduceEnergy);
					returnValue = true;

				} else {
					player.setCurrentPosition(head, reduceEnergy);
					returnValue = true;
				}

			} else {
				// magic
				if (player.isMagic) {

					player.setCurrentPosition(head, reduceEnergy);
					returnValue = true;
				} else {
					player.setCurrentPosition(tail, reduceEnergy);
					returnValue = true;
				}
			}

		}

		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycompany.ladderandsnake.IBoardOperator#isValid()
	 */
	@Override
	public boolean isValid() throws Exception {
		// head > tail
		// head != tail
		// head and tail are not in same row

		if (head == null || tail == null) {
			throw new Exception("Invalid Configuration for Snake");
		}
		int headPosition = head.getPosition();
		int tailPosition = tail.getPosition();

		if (headPosition <= tailPosition) {
			return false;
		}

		if (head.getRowPosition() <= tail.getRowPosition()) {
			return false;
		}

		return true;
	}

	@Override
	public String getIdentifier() {

		return this.identifier;
	}

}
