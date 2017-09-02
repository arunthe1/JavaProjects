/**
 * 
 */
package com.mycompany.ladderandsnake.board;

/**
 * @author Arun
 * 
 *         This enum defines the possible type of the squares. Each type has
 *         specific roles to play. Normal - Usual square, no special function
 *         Pitstop - Usual square with energy levels attached SnakeStart -
 *         Represents the Head of Snake SnakeEnd - Represents the Tail of snake
 *         LadderStart - Represents the Bottom of the Ladder LadderEnd -
 *         Represents the Top of the Ladder Trampoline - Doubles the player's
 *         last dice role Elevator - Moves the player position upwards based on
 *         the last dice number Magic - Reverses the snake and ladder operation
 *         Memory - Moves the user to the last[dice] position
 * 
 * 
 */
public enum SquareType {
	Normal, Pitstop, SnakeStart, SnakeEnd, LadderStart, LadderEnd, Trampoline, Elevator, Magic, Memory
}
