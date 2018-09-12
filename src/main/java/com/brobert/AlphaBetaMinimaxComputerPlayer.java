/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;

import com.brobert.TwoDimensionalBoard.Token;

/**
 * TEST COMMENT INTELLIJ
 * @author brobert
 *
 */
public class AlphaBetaMinimaxComputerPlayer implements ComputerPlayer {

	private Token AIType;

	private final int DEPTH = 13;



	public AlphaBetaMinimaxComputerPlayer(Token type) {
		AIType = type;
	}





	class Move {
		Coordinate coord;
		int score;



		@Override
		public String toString() {
			return coord.toString() + " score [" + score + "]";
		}
	}



	/* (non-Javadoc)
	 * @see com.brobert.ComputerPlayer#play(com.brobert.TwoDimensionalBoard)
	 */
	@Override
	public Coordinate play(TwoDimensionalBoard boardState) {
		System.out.println("Computer is thinking...");
		Connect4Board board = (Connect4Board) boardState;
		Move bestMove = minimax(board, AIType, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return bestMove.coord;
	}



	private Move minimax(Connect4Board board, Token player, int depth, int alpha, int beta) {
		Move bestMove = new Move();
		int bestScore = (player == AIType) ? -1 : 1;
		bestMove.score = bestScore;
		Move currentMove;
		List<Coordinate> legalMoves = legalMoves(board);
		boolean humanWins = board.enoughInARow(opposite(AIType));
		boolean computerWins = board.enoughInARow(AIType);
		if (humanWins || computerWins || legalMoves.isEmpty() || depth == 0) {
			if (humanWins) {
				bestMove.score = Integer.MIN_VALUE;
			} else if (computerWins) {
				bestMove.score = Integer.MAX_VALUE;
			} else {
				bestMove.score = evaluate(board);
			}
		} else {
			bestMove.coord = legalMoves.get(0);
			for (Coordinate c : legalMoves) {
				board.placePiece(c.x, c.y, player);

				if (player == AIType) {
					currentMove = minimax(board, opposite(AIType), depth - 1, alpha, beta);
					if (currentMove.score > alpha) {
						alpha = currentMove.score;
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;
					}
					//minimizing
				} else {
					currentMove = minimax(board, AIType, depth - 1, alpha, beta);
					if (currentMove.score < beta) {
						beta = currentMove.score;
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;
					}
				}

				board.removeFrom(c.x);
				if (alpha >= beta) {
					break;
				}
			}
		}
		return bestMove;

	}



	/**
	 * @param board
	 * @return
	 */
	private List<Coordinate> legalMoves(Connect4Board board) {
		List<Coordinate> legalMoves = new ArrayList<>();
		for (int i = 0; i < board.width(); i++) {
			if (board.firstOpen(i + 1) >= 0) {
				legalMoves.add(new Coordinate(i + 1, 1));
			}
		}
		return legalMoves;
	}



	/**
	 * @param board
	 * @return
	 */
	private int evaluate(Connect4Board board) {
		int maxWinConditions = countWinConditions(board, AIType);
		int minWinConditions = countWinConditions(board, opposite(AIType));
		return maxWinConditions - minWinConditions;
	}



	/**
	 * @param aIType2
	 * @return
	 */
	private int countWinConditions(Connect4Board board, Token type) {
		int horizontalWinConditions = countHorizontalWinConditions(board, type);
		int verticalWinConditions = countVerticalWinConditions(board, type);
		int diagonalWinConditions = countDiagonalWinConditions(board, type);
		return horizontalWinConditions + verticalWinConditions + diagonalWinConditions;
	}



	/**
	 * @param type
	 * @return
	 */
	private int countVerticalWinConditions(Connect4Board board, Token type) {
		int vertWinConditions = 0;
		for (int i = 0; i < board.width(); i++) {
			int startingIdx = 0, count = 0;
			for (int j = 0; j < board.height() - 3; j++) {
				for (int k = 0; k < 4; k++) {
					Token here = board.at(i, startingIdx + k);
					if (here == type || here == Token.EMPTY) {
						count++;
					} else {
						break;
					}
				}
				if (count == 4) {
					vertWinConditions++;
				}
				count = 0;
				startingIdx++;
			}
			startingIdx = 0;
		}
		return vertWinConditions;
	}



	/**
	 * @param type
	 * @return
	 */
	private int countDiagonalWinConditions(Connect4Board board, Token type) {
		int diagWinConditions = 0;
		for (int i = 3; i < board.width(); i++) {
			for (int j = 0; j < board.height() - 3; j++) {
				if ((board.at(i, j) == type || board.at(i, j) == Token.EMPTY)
						&& (board.at(i - 1, j + 1) == type || board.at(i - 1, j + 1) == Token.EMPTY)
						&& (board.at(i - 2, j + 2) == type || board.at(i - 2, j + 2) == Token.EMPTY)
						&& (board.at(i - 3, j + 3) == type || board.at(i - 3, j + 3) == Token.EMPTY)) {
					diagWinConditions++;
				}
			}
		}
		for (int i = 3; i < board.width(); i++) {
			for (int j = 3; j < board.height(); j++) {
				if ((board.at(i, j) == type || board.at(i, j) == Token.EMPTY)
						&& (board.at(i - 1, j - 1) == type || board.at(i - 1, j - 1) == Token.EMPTY)
						&& (board.at(i - 2, j - 2) == type || board.at(i - 2, j - 2) == Token.EMPTY)
						&& (board.at(i - 3, j - 3) == type || board.at(i - 3, j - 3) == Token.EMPTY)) {
					diagWinConditions++;
				}
			}
		}
		return diagWinConditions;
	}



	/**
	 * @param type
	 * @return
	 */
	private int countHorizontalWinConditions(Connect4Board board, Token type) {
		int horizontalWinConditions = 0;
		for (int i = 0; i < board.height(); i++) {
			int startingIdx = 0, count = 0;
			for (int j = 0; j < board.width() - 3; j++) {
				for (int k = 0; k < 4; k++) {
					Token here = board.at(startingIdx + k, i);
					if (here == type) {
						count++;
					} else {
						break;
					}
				}
				if (count == 4) {
					horizontalWinConditions++;
				}
				count = 0;
				startingIdx++;
			}
			startingIdx = 0;
		}
		return horizontalWinConditions;
	}



	/**
	 * @param player
	 * @return
	 */
	private Token opposite(Token player) {
		return (player == Token.X) ? Token.O : Token.X;
	}

}
