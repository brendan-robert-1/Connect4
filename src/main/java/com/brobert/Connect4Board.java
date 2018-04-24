package com.brobert;

public class Connect4Board implements TwoDimensionalBoard {

	private final int HEIGHT = 6, WIDTH = 7;

	private Token[][] squares = new Token[WIDTH][HEIGHT];



	public Connect4Board() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				squares[j][i] = Token.EMPTY;
			}
		}
	}



	@Override
	public int width() {
		return WIDTH;
	}



	@Override
	public Token at(int x, int y) {
		return squares[x][y];
	}



	@Override
	public void placePiece(int x, int y, Token pieceType) {
		if (x < 1 || x > WIDTH) {
			throw new IllegalArgumentException(x + " is out of range of the width: " + WIDTH);
		}
		int heightIdx = firstOpen(x);
		squares[x - 1][heightIdx] = pieceType;
	}



	public int firstOpen(int column) {
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



	@Override
	public boolean isFull() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (at(j, i).equals(Token.EMPTY)) {
					return false;
				}
			}
		}
		return true;
	}



	@Override
	public boolean enoughInARow(Token computer) {
		boolean horizontal = horizontalWin(computer);
		boolean vertical = verticalWin(computer);
		boolean diagonal = diagnoalWin(computer);
		return horizontal || vertical || diagonal;
	}



	/**
	 * @param computer
	 * @return
	 */
	private boolean diagnoalWin(Token player) {
		for (int i = 3; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT - 3; j++) {
				if (at(i, j) == player && at(i - 1, j + 1) == player && at(i - 2, j + 2) == player && at(i - 3, j + 3) == player) {
					return true;
				}
			}
		}

		for (int i = 3; i < WIDTH; i++) {
			for (int j = 3; j < HEIGHT; j++) {
				if (at(i, j) == player && at(i - 1, j - 1) == player && at(i - 2, j - 2) == player && at(i - 3, j - 3) == player) {
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * @param computer
	 * @return
	 */
	private boolean verticalWin(Token token) {
		for (int i = 0; i < WIDTH; i++) {
			int startingIdx = 0, count = 0;
			for (int j = 0; j < HEIGHT - 3; j++) {
				for (int k = 0; k < 4; k++) {
					Token here = at(i, startingIdx + k);
					if (here == token) {
						count++;
					} else {
						break;
					}
				}
				if (count == 4) {
					System.out.println("Vertical win for " + token);
					return true;
				}
				count = 0;
				startingIdx++;
			}
			startingIdx = 0;
		}
		return false;
	}



	/**
	 * @param token
	 * @return
	 */
	private boolean horizontalWin(Token token) {
		for (int i = 0; i < HEIGHT; i++) {
			int startingIdx = 0, count = 0;
			for (int j = 0; j < WIDTH - 3; j++) {

				//Starting at the starting Idx go rigth 4
				for (int k = 0; k < 4; k++) {
					Token here = at(startingIdx + k, i);
					if (here == token) {
						count++;
					} else {
						break;
					}
				}
				if (count == 4) {
					return true;
				}
				count = 0;
				startingIdx++;
			}
			startingIdx = 0;
		}
		return false;
	}



	@Override
	public int height() {
		return HEIGHT;
	}



	/**
	 * @param x
	 */
	public void removeFrom(int x) {
		for (int i = 0; i < HEIGHT; i++) {
			if (squares[x - 1][i] != Token.EMPTY) {
				squares[x - 1][i] = Token.EMPTY;
				break;
			}
		}
	}

}
