package com.brobert;

import java.util.Scanner;

import com.brobert.TwoDimensionalBoard.Token;

public class Conect4Game extends Game {

	public Conect4Game() {
		setBoard(new Connect4Board());
	}



	//start the game
	@Override
	public void start() {
		human(Token.X);
		ComputerPlayer player = new AlphaBetaMinimaxComputerPlayer(getComputer());
		Scanner scanner = new Scanner(System.in);
		getBoard().printBoard();
		while (!isOver()) {
			playerTurn(scanner);
			//computerTurn(player, getHuman());
			if (!isOver()) {
				computerTurn(player, getComputer());
			}
		}

	}



	/**
	 * @param scanner
	 */
	@Override
	public void playerTurn(Scanner scanner) {
		System.out.println("Enter X:");
		int x = getInt(scanner);
		int y = 0;

		while (!isValidCoordinate(x, y)) {
			System.out.println("Invalid coordinate... Please try again.");
			System.out.println("Enter X: ");
			x = getInt(scanner);
		}
		Coordinate play = new Coordinate(x, y);
		getBoard().placePiece(play.x, play.y, getHuman());
		printTurn(getHuman(), play);
		turns++;
	}



	@Override
	public boolean isValidCoordinate(int x, int y) {
		Connect4Board board = (Connect4Board) getBoard();
		return board.firstOpen(x) >= 0;
	}



	@Override
	public void printTurn(Token square, Coordinate coordinate) {
		System.out.println();
		System.out.println("Turn #" + turns + ": " + square + " placed a token at " + coordinate);
		getBoard().printBoard();
		System.out.println();
	}

}
