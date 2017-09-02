/**
 * 
 */
package com.mycompany.ladderandsnake;

import java.util.Scanner;

import com.mycompany.ladderandsnake.board.Board;

/**
 * @author Arun
 *
 */
public class Main {

	private Board myBoard;

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main obj = new Main();
		try {
			obj.runTheShow();
		} catch (Exception e) {
			// TODO Do not print the stack trace
			e.printStackTrace();
		}

	}

	private void runTheShow() {
		int TotalNumberOfSquares = 0;
		int TotalNumberOfUsers = 0;

		try (Scanner in = new Scanner(System.in)) {
			System.out.print("Please enter Total number of Squares : ");
			String temp = in.nextLine();
			TotalNumberOfSquares = Integer.parseInt(temp);

			System.out.print("Please enter Total number of Users : ");
			temp = in.nextLine();
			TotalNumberOfUsers = Integer.parseInt(temp);

			myBoard = new Board(TotalNumberOfSquares, TotalNumberOfUsers);

			System.out.println("Please enter Other Configurations one line at a time");
			do {
				temp = in.nextLine();
				if (temp.equalsIgnoreCase(System.lineSeparator()))
					break;
				else if (temp.isEmpty())
					break;
				else {
					setBoardConfiguration(myBoard, temp);
					System.out.println(temp);
				}

			} while (true);

			while (!myBoard.isBoardComplete()) {
				for (int j = 1; j < TotalNumberOfUsers; j++) {
					// Code to make the play happen on its own
					// int nextDiceRoll = (int) (6 * Math.random());
					// if (nextDiceRoll < 1)
					// nextDiceRoll = 1;

					myBoard.PrintBoardCurrentPosition();
					int nextDiceRoll = 0;
					// System.out.println(nextDiceRoll);
					temp = in.nextLine();
					nextDiceRoll = Integer.parseInt(temp);
					myBoard.play(nextDiceRoll);

				}

			}
		} catch (Exception e) {
			System.out.println("Input format validation error :" + e.getMessage());

		}
	}

	// Function to parse the configuration and set on the board.
	private void setBoardConfiguration(Board board, String configuration) throws Exception {
		if (configuration.isEmpty())
			return;

		if (board == null)
			return;

		String[] args = configuration.split(" ");

		// compare with uppercase, so number of cases will be reduced
		switch (args[0].toUpperCase()) {

		case "S":
			// Snake is expected to have S <end> <start> <hunger>
			if (args.length == 4) {
				int tail = Integer.parseInt(args[1]);
				int head = Integer.parseInt(args[2]);
				int hunger = Integer.parseInt(args[3]);
				board.setSnake(head, tail, hunger);
			}
			break;
		case "L":

			// Ladder is expected to have L <start> <end>
			if (args.length == 3) {
				int bottom = Integer.parseInt(args[1]);
				int top = Integer.parseInt(args[2]);
				board.setLadder(bottom, top);
			}
			break;
		case "ME":

			// Memory expected to have only the position
			if (args.length == 2) {
				int position = Integer.parseInt(args[1]);
				board.setMemory(position);

			}
			break;
		case "MA":

			// Memory expected to have only the position
			if (args.length == 2) {
				int position = Integer.parseInt(args[1]);
				board.setMagic(position);

			}
			break;

		case "T":

			// Memory expected to have only the position
			if (args.length == 2) {
				int position = Integer.parseInt(args[1]);
				board.setTrampoline(position);

			}
			break;
		case "E":

			// Memory expected to have only the position
			if (args.length == 2) {
				int position = Integer.parseInt(args[1]);
				board.setElevator(position);

			}
			break;
		case "P":

			// Memory expected to have only the position
			if (args.length == 3) {
				int position = Integer.parseInt(args[1]);
				float power = Float.parseFloat(args[2]);
				board.setPitstop(position, power);

			}
			break;
		default:
			printUsage();
			break;

		}
	}

	private void printUsage() {
		System.out.println("Invalid input parameter passed");
		System.out.println("For Snake use S tail head hunber");
		System.out.println("For Ladder use L bottom top");
		System.out.println("For Memory use ME postion");
		System.out.println("For Magic use MA postion");
		System.out.println("For Elevator use E postion");
		System.out.println("For Trampoline use T postion");
		System.out.println("For Pitstop use P postion power");
		System.exit(1);
	}
}
