/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author brobert
 *
 */
public class RandomComputerPlayer implements ComputerPlayer {

	@Override
	public Coordinate play(TwoDimensionalBoard boardState) {
		Connect4Board board = (Connect4Board) boardState;
		List<Coordinate> validPlaces = getValidCoordinates(board);
		int randomNum = ThreadLocalRandom.current().nextInt(0, validPlaces.size());
		return validPlaces.get(randomNum);

	}



	/**
	 * @param boardState
	 * @return
	 */
	private List<Coordinate> getValidCoordinates(Connect4Board boardState) {
		List<Coordinate> validCoords = new ArrayList<>();
		for (int i = 1; i <= boardState.width(); i++) {
			if (boardState.firstOpen(i) >= 0) {
				validCoords.add(new Coordinate(i, 0));
			}
		}
		return validCoords;
	}

}
