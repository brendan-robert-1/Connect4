package com.brobert;

public interface TwoDimensionalBoard {

	enum Token {
		X, O, EMPTY;
	}
	void placePiece(int column, Token pieceType);
	
	void printBoard();
	
	Token at(int x, int y);
}
