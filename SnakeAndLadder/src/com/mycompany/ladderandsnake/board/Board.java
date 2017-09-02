package com.mycompany.ladderandsnake.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.ladderandsnake.operator.IBoardOperator;
import com.mycompany.ladderandsnake.operator.Ladder;
import com.mycompany.ladderandsnake.operator.Snake;
import com.mycompany.ladderandsnake.player.Player;

public class Board {

	private Square squareArray[][] = null;
	private int row = 0;
	private int col = 0;
	private int TotalNumberOfSquares = 0;
	private Player playerArray[] = null;
	private boolean isBoardComplete = false;

	private Map<Integer, IBoardOperator> operatorMap = null;
	private Map<Player, Ladder> ladderMap = null;
	private Logger logger = Logger.getLogger(Board.class.getName());

	public Board(int boardSize, int numberOfPlayers) throws Exception {
		if (boardSize > 0) {
			row = (int) Math.sqrt(boardSize);
			col = row;

			// providing boardsize like 163 should be avoided
			if ((row * col) != boardSize) {
				logger.log(Level.INFO, "Invalid Board size");
				throw new Exception("Invalid Board Size");
			}

			// we are good with row and col
			squareArray = new Square[row][col];
			TotalNumberOfSquares = boardSize;
		} else {
			// TODO: Create new exception type
			logger.log(Level.INFO, "Invalid Board size");
			throw new Exception("Invalid Board Size");
		}

		// Number of players in this game should be > 1 typically.
		// Considering someone who just wants to spend time with the game,
		// we are not restricting the number of players to > 1.
		// Even a single player can play continuously, checking his luck.
		// Not typical but not impossible.
		if (numberOfPlayers > 0) {
			playerArray = new Player[numberOfPlayers];
		} else {
			logger.log(Level.INFO, "Invalid number of players");
			throw new Exception("Invalid number of players");
		}

		operatorMap = Collections.synchronizedMap(new HashMap<Integer, IBoardOperator>());
		ladderMap = Collections.synchronizedMap(new HashMap<Player, Ladder>());
		initializeBoard();
		initializePlayers();
	}

	// Init the board with the default values
	// as per requirement we have to have zig-zag numbering
	private void initializeBoard() {
		int count = 0;
		for (int i = 0; i < row; i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < col; j++) {
					squareArray[i][j] = new Square();
					squareArray[i][j].setPosition(++count);
					squareArray[i][j].setRowPosition(i);
					squareArray[i][j].setColumnPosition(j);
					// squareArray[i][j].setEnergyUnits(10);
				}
			} else {
				for (int j = col - 1; j >= 0; j--) {
					squareArray[i][j] = new Square();
					squareArray[i][j].setPosition(++count);
					squareArray[i][j].setRowPosition(i);
					squareArray[i][j].setColumnPosition(j);
					// squareArray[i][j].setEnergyUnits(5);
				}
			}

		}

		squareArray[0][0].setSquareType(SquareType.Pitstop);
		squareArray[0][0].setEnergyUnits(getInitialEnergy());
		logger.log(Level.FINER, "default energy units:" + getInitialEnergy());
	}

	// Initial energy goes with the formula. Round the value makes sense because
	// each roll requires 1 unit
	// not fraction.
	private float getInitialEnergy() {
		float initialEnergy = 1;
		initialEnergy = (float) ((Math.pow(TotalNumberOfSquares, 2)) / 3);
		return Math.round(initialEnergy);
	}

	// init the player and put him in square 1
	private void initPlayer(Player player) {
		if (player != null) {
			Square initialSquare = this.getSquareatPosition(1);
			if (initialSquare != null) {
				player.setCurrentPosition(initialSquare, false);
				this.performEnergyBoost(player, initialSquare);
			}

		}
	}

	private void initializePlayers() {
		for (int i = 0; i < playerArray.length; i++) {
			playerArray[i] = new Player();
			this.initPlayer(playerArray[i]);
		}
	}

	// Associated operators are snake, ladder, pitstop.
	private IBoardOperator getAssociatedOperator(Square currentSquare) {
		if (operatorMap.isEmpty() == false) {
			return (operatorMap.getOrDefault(currentSquare.getPosition(), null));
		}

		return null;
	}

	private Square getSquareatPosition(int position) {
		Square returnObj = null;
		// row is 1 index based.

		if (position > TotalNumberOfSquares)
			return returnObj;

		int localPosition = position - 1;

		if (localPosition >= 0) {
			int localrow = localPosition / row;
			int localcol = 0;

			// even rows - column starts from 0, so computation holds good
			localcol = (localPosition - (localrow * row));

			if (localrow % 2 != 0) {
				// odd rows - column starts from col-1
				localcol = (col - 1) - localcol;
			}

			if (localcol < 0) {
				return returnObj;
			}

			if (localrow >= 0 && localcol >= 0 && localrow < row && localcol < col) {
				if (squareArray[localrow][localcol] != null)
					returnObj = squareArray[localrow][localcol];
			}
		}

		return returnObj;
	}

	public void setSnake(int headPosition, int tailPosition, int hunger) throws Exception {
		if ((headPosition < 1 || tailPosition < 1 || hunger < 0) || (headPosition <= tailPosition)) {
			logger.log(Level.INFO, "Invalid snake configuration");
			throw new Exception("Invalid Snake Configuration");
		}

		Square snakeHead = this.getSquareatPosition(headPosition);
		if (snakeHead == null) {
			logger.log(Level.INFO, "Invalid snake configuration");
			throw new Exception("Invalid Snake Configuration");
		}

		Square snakeTail = this.getSquareatPosition(tailPosition);

		if (snakeTail == null) {
			logger.log(Level.INFO, "Invalid snake configuration");
			throw new Exception("Invalid Snake Configuration");
		}

		if (snakeHead.getSquareType() != SquareType.Normal && snakeHead.getSquareType() != SquareType.SnakeStart) {
			logger.log(Level.INFO, "Invalid snake configuration");
			throw new Exception("Invalid Snake Configuration");
		}

		snakeHead.setSquareType(SquareType.SnakeStart);

		if (snakeTail.getSquareType() != SquareType.Normal && snakeTail.getSquareType() != SquareType.SnakeEnd) {
			logger.log(Level.INFO, "Invalid snake configuration");
			throw new Exception("Invalid Snake Configuration");
		}
		snakeTail.setSquareType(SquareType.SnakeEnd);

		Snake obj = new Snake(snakeHead, snakeTail, hunger);
		if (obj.isValid()) {
			operatorMap.put(snakeHead.getPosition(), obj);
			operatorMap.put(snakeTail.getPosition(), obj);
		}
	}

	public void setLadder(int bottomPosition, int topPosition) throws Exception {
		if ((bottomPosition < 1 || topPosition < 1) || (bottomPosition >= topPosition)) {
			logger.log(Level.INFO, "Invalid Ladder configuration");
			throw new Exception("Invalid Ladder Configuration");
		}

		Square ladderBottom = this.getSquareatPosition(bottomPosition);
		if (ladderBottom == null) {
			logger.log(Level.INFO, "Invalid Ladder configuration");
			throw new Exception("Invalid Ladder Configuration");
		}

		Square ladderTop = this.getSquareatPosition(topPosition);

		if (ladderTop == null) {
			logger.log(Level.INFO, "Invalid ladder configuration");
			throw new Exception("Invalid Ladder Configuration");
		}

		if (ladderBottom.getSquareType() != SquareType.Normal
				&& ladderBottom.getSquareType() != SquareType.LadderStart) {
			logger.log(Level.INFO, "Invalid ladder configuration");
			throw new Exception("Invalid Ladder Configuration");
		}

		if (ladderTop.getSquareType() != SquareType.Normal && ladderTop.getSquareType() != SquareType.LadderEnd) {
			logger.log(Level.INFO, "Invalid ladder configuration");
			throw new Exception("Invalid Ladder Configuration");
		}

		ladderBottom.setSquareType(SquareType.LadderStart);

		ladderTop.setSquareType(SquareType.LadderEnd);

		Ladder obj = new Ladder(ladderBottom, ladderTop);

		if (obj.isValid()) {
			operatorMap.put(ladderBottom.getPosition(), obj);
			operatorMap.put(ladderTop.getPosition(), obj);
		}

	}

	private Square setSquareType(int Position, SquareType type) throws Exception {
		if (Position < 1) {
			logger.log(Level.INFO, "Invalid configuration");
			throw new Exception("Invalid  Configuration");
		}

		Square square = this.getSquareatPosition(Position);
		if (square == null) {
			logger.log(Level.INFO, "Invalid configuration");
			throw new Exception("Invalid Configuration");
		}
		if (square.getSquareType() != SquareType.Normal
				&& square.getSquareType() != type) {
			logger.log(Level.INFO, "Invalid configuration");
			throw new Exception("Invalid Configuration");
		}
		square.setSquareType(type);
		return square;

	}

	public void setMagic(int position) throws Exception {
		this.setSquareType(position, SquareType.Magic);
	}

	public void setMemory(int position) throws Exception {
		this.setSquareType(position, SquareType.Memory);
	}

	public void setElevator(int position) throws Exception {
		this.setSquareType(position, SquareType.Elevator);
	}

	public void setTrampoline(int position) throws Exception {
		this.setSquareType(position, SquareType.Trampoline);
	}

	public void setPitstop(int position, float energy) throws Exception {
		if (energy < 0.0f) {
			throw new Exception("Invalid energy range");
		}
		Square square = this.setSquareType(position, SquareType.Pitstop);
		if (square != null) {
			square.setEnergyUnits(energy);
		}
	}

	public void setNormal(int position) throws Exception {
		this.setSquareType(position, SquareType.Normal);
	}

	private boolean performElevation(Player player, int DiceRoll, Square newSquare) {
		boolean returnValue = false;
		if (player != null && newSquare != null && DiceRoll > 0) {
			Square localObj = null;
			int newRowPosition = newSquare.getRowPosition() + DiceRoll;
			int localcol = newSquare.getColumnPosition();

			if (newRowPosition >= row) {
				newRowPosition = row - 1;
			}

			if (newRowPosition >= 0 && localcol >= 0) {
				if (squareArray[newRowPosition][localcol] != null)
					localObj = squareArray[newRowPosition][localcol];
			}

			if (localObj != null) {
				player.setCurrentPosition(localObj);
				returnValue = true;
			}
		}

		return returnValue;
	}

	private boolean performTrampoline(Player player, int DiceRoll, Square newSquare) {
		boolean returnValue = false;
		if (player != null && newSquare != null && DiceRoll > 0) {
			Square localObj = null;
			int newPosition = newSquare.getPosition() + DiceRoll;

			localObj = this.getSquareatPosition(newPosition);

			if (localObj != null) {
				player.setCurrentPosition(localObj);
				returnValue = true;
			}

		}

		return returnValue;
	}

	private boolean performMemory(Player player, int DiceRoll, Square newSquare) {
		boolean returnValue = false;
		if (player != null && newSquare != null && DiceRoll > 0) {
			Square localObj = null;

			// When user is redirected here, it means he is landed on the memory cell
			// which means memory cell is his current cell.
			// so set the position to current cell, before calculating the memory
			player.setCurrentPosition(newSquare, false);
			localObj = player.getLastSquareBeforeMoves(DiceRoll);

			if (localObj != null) {
				player.setCurrentPosition(localObj);
				returnValue = true;
			} else {
				player.setCurrentPosition(this.getSquareatPosition(1));
				returnValue = true;
			}

		}

		return returnValue;
	}

	private boolean performEnergyBoost(Player player, Square newSquare) {
		boolean returnValue = false;
		if (player != null && newSquare != null) {

			if (newSquare.getSquareType() == SquareType.Pitstop) {
				player.boostEnergy(newSquare.getEnergyUnits());
				returnValue = true;
			}

		}

		return returnValue;
	}

	private boolean isPlayerEnergetic(Player player) {
		if (player != null) {
			if (player.getEnergyUnits() >= 1) {
				return true;
			}
		}

		return false;
	}

	private int currentPlayerIndex = 1;

	// main should call only play with dice roll
	// who is playing is controlled by the board
	public void play(int DiceRoll) throws Exception {
		play(currentPlayerIndex, DiceRoll);
		++currentPlayerIndex;
		if (currentPlayerIndex > playerArray.length) {
			currentPlayerIndex = 1;
		}

	}

	private void play(int player, int DiceRoll) throws Exception {
		if (player > playerArray.length) {
			throw new Exception("Invalid user id");
		}

		if (playerArray[player - 1] == null) {
			throw new Exception("Invalid user id");
		}

		if (DiceRoll > 6 || DiceRoll < 1) {
			throw new Exception("Invalid Dice Roll");
		}

		play(playerArray[player - 1], DiceRoll);
	}

	public void play(Player player, int DiceRoll) {

		if (isPlayerEnergetic(player) == false) {

			initPlayer(player);
		}

		Square currentSquare = player.getLastSquare();

		// now increment the roll

		int newPosition = DiceRoll;
		if (currentSquare != null) {
			newPosition += currentSquare.getPosition();
		}

		Square newSquare = this.getSquareatPosition(newPosition);

		if (newSquare != null) {

			// code to handle the ladder being used.
			// once the flag is set in previous run of this same user,
			// we have to unset the flag to ensure it is usable by others.
			Ladder ladderobj = ladderMap.get(player);
			if (ladderobj != null) {
				ladderobj.setBeingUsed(false);
				ladderMap.remove(player);
			}

			// Elevator, magic, memory, pitstop are properties of
			// single square. so they need to be handled separately

			boolean needShift = false;
			SquareType newSquareType = newSquare.getSquareType();
			switch (newSquareType) {
			case Elevator:
				needShift = this.performElevation(player, DiceRoll, newSquare);
				break;
			case Magic:
				player.isMagic = !player.isMagic;
				break;
			case Memory:
				needShift = this.performMemory(player, DiceRoll, newSquare);
				break;
			case Trampoline:
				needShift = this.performTrampoline(player, DiceRoll, newSquare);
				break;
			case Pitstop:
				this.performEnergyBoost(player, newSquare);
				break;
			default:
				break;

			}

			if (needShift) {
				newSquare = player.getCurrentPosition();
			}

			// Snake and Ladder connects 2 different squares, so they are considered
			// as operators.
			IBoardOperator operator = null;
			operator = getAssociatedOperator(newSquare);

			if (operator != null) {

				try {
					boolean result = operator.performAction(player, !needShift);

					if (result) {
						newSquare = player.getCurrentPosition();
						if (operator.getClass() == Ladder.class) {
							ladderMap.put(player, (Ladder) operator);
						}
					} else {
						player.setCurrentPosition(newSquare, !needShift);
					}
				} catch (Exception e) {
					// Todo - set meaningful error message.
					System.out.println("Exception occured:" + e.getMessage());
					// e.printStackTrace();
				}
			} else {
				player.setCurrentPosition(newSquare, !needShift);
				// if (player.getEnergyUnits() <= 1) {
				// player.setCurrentPosition(this.getSquareatPosition(1), false);
				// }
			}

			StringBuilder builder = new StringBuilder();
			if (currentSquare != null) {
				builder.append(currentSquare.getPosition());
			}
			builder.append("->");
			builder.append(newPosition);
			if (newPosition != newSquare.getPosition() || newSquareType == SquareType.Magic) {
				builder.append("->");
				builder.append(newSquareType.toString().charAt(0));
				builder.append(newSquare.getPosition());
			}

			System.out.println(builder.toString());
		} else {
			// player has played but not possible to make the move.
			// This was not mentioned as requirement. but typical snake-ladder goes by this
			// rule
			// if player is in 98, he can reach 100 only if he gets 2, not otherwise

			player.setCurrentPosition(player.getCurrentPosition());
			int current = player.getCurrentPosition().getPosition();
			System.out.println(current + "->" + current);
		}

		if (player.getCurrentPosition().getPosition() == TotalNumberOfSquares) {
			PrintBoardCurrentPosition();
			setBoardComplete(true);
		}
	}

	/**
	 * @return the isBoardComplete
	 */
	public boolean isBoardComplete() {
		return isBoardComplete;
	}

	/**
	 * @param isBoardComplete
	 *            the isBoardComplete to set
	 */
	public void setBoardComplete(boolean isBoardComplete) {
		this.isBoardComplete = isBoardComplete;
	}

	// Function to print the current position of the board with respect to players
	public void PrintBoardCurrentPosition() {
		StringBuilder builder = new StringBuilder();
		for (int i = currentPlayerIndex - 1; i < playerArray.length; i++) {
			Player obj = playerArray[i];
			if (obj != null) {
				if (obj.getCurrentPosition().getPosition() == TotalNumberOfSquares) {
					builder.append("[");
					builder.append(obj.getPlayerName());
					builder.append(".W]");
				} else {
					builder.append(obj);
				}
				if (!(i >= playerArray.length - 1 && currentPlayerIndex == 1)) {
					builder.append(",");
				}

			}
		}

		for (int j = 0; j < currentPlayerIndex - 1; j++) {
			Player obj = playerArray[j];
			if (obj != null) {
				builder.append(obj);
			}
			if (j < currentPlayerIndex - 2) {
				builder.append(",");
			}
		}

		System.out.println(builder.toString());

	}

}
