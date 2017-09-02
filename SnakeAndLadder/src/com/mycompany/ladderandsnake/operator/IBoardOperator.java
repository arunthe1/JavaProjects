package com.mycompany.ladderandsnake.operator;

import com.mycompany.ladderandsnake.player.Player;

public interface IBoardOperator {

	/**
	 * This function should perform the action of moving the player to the position
	 * after the computation. Basically Board Operator is the linkage between the
	 * source and target squares on the board. Board square as such does not know
	 * where to move the player. Only it knows what type of square and reference to
	 * the operator to perform the operation.
	 * 
	 * @return bool to indicate success or failure
	 * @throws Exception
	 *             - in case of invalid operation is encountered
	 */
	boolean performAction(Player player, boolean reduceEnergy) throws Exception;

	/**
	 * This function should implement the functional validation of the operator.
	 * Eg., Snake head and tail should not be in same row, head > tail etc. Inputs
	 * to be validated to make the operator is logically correct.
	 * 
	 * @return - boolean to indicate valid or invalid
	 * @throws Exception
	 *             - if input is invalid
	 */
	boolean isValid() throws Exception;

	/**
	 * This function should return the identifier of the operator Each operator can
	 * implement their own way of choosing the identifier.
	 * 
	 * @return
	 */
	String getIdentifier();
}
