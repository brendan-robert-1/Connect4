/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;

import com.brobert.TwoDimensionalBoard.Token;

/**
 * @author brobert
 *
 */
public class AlphaBetaMinimaxComputerPlayer implements ComputerPlayer {

	private Token AIType;



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
		Connect4Board board = (Connect4Board) boardState;
		Move bestMove = miniax(board, AIType, 8, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return bestMove.coord;
	}



	private Move miniax(Connect4Board board, Token player, int depth, int alpha, int beta) {
		Move bestMove = new Move();
		int bestScore = (player == AIType) ? -1 : 1;
		bestMove.score = bestScore;
		Move currentMove;
		List<Coordinate> legalMoves = legalMoves(board);

		if (board.enoughInARow(opposite(AIType)) || board.enoughInARow(AIType) || legalMoves.isEmpty() || depth == 0) {
			bestMove.score = evaluate(board);
		} else {
			bestMove.coord = legalMoves.get(0);
			for (Coordinate c : legalMoves) {
				board.placePiece(c.x, c.y, player);

				board.removeFrom(c.x);
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
	private int evaluate(TwoDimensionalBoard board) {
		return 0;
	}



	/**
	 * @param player
	 * @return
	 */
	private Token opposite(Token player) {
		return (player == Token.X) ? Token.O : Token.X;
	}

}
