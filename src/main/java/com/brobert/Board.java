package com.brobert;

public class Board implements TwoDimensionalBoard {

	private final int HEIGHT = 6, WIDTH = 7;

	private Token[][] squares = new Token[WIDTH][HEIGHT];



	public Board() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				squares[j][i] = Token.EMPTY;
			}
		}
	}



	@Override
	public Token at(int x, int y) {
		return squares[x][y];
	}



	@Override
	public void placePiece(int column, Token pieceType) {
		if (column < 1 || column > WIDTH) {
			throw new IllegalArgumentException(column + " is out of range of the width: " + WIDTH);
		}
		int heightIdx = firstOpen(column);
		System.out.println(heightIdx);
		squares[column - 1][heightIdx] = pieceType;
	}



	private int firstOpen(int column) {
		int first = -1;
		for (int i = 0; i < HEIGHT; i++) {
			if (squares[column - 1][i] == Token.EMPTY) {
				first++;
			} else {
				break;
			}
		}
		return first;
	}



	@Override
	public void printBoard() {
		for (int i = 0; i < WIDTH; i++) {
			System.out.print("  " + (i + 1) + " ");
		}
		System.out.print("\n");
		for (int w = 0; w < WIDTH; w++) {
			System.out.print("----");
		}
		System.out.print("-\n");
		for (int i = 0; i < HEIGHT; i++) {
			System.out.print("|");
			for (int j = 0; j < WIDTH; j++) {
				if (squares[j][i] == Token.X) {
					System.out.print(" X ");
				} else if (squares[j][i] == Token.O) {
					System.out.print(" O ");
				} else {
					System.out.print("   ");
				}
				if (j != HEIGHT) {
					System.out.print("|");
				}
			}
			System.out.print("|");
			if (i != HEIGHT) {
				System.out.print("\n");
				for (int w = 0; w < WIDTH; w++) {
					System.out.print("----");
				}
				System.out.print("-");
				System.out.print("\n");
			}

		}
		System.out.print("\n");
	}

}
