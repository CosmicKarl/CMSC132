package reversi;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

/**
 * 
 * @author Jacob Knapo 
 * 		   CMSC 132 Herman 
 * 	       2/24/2015 
 *         Proj 2 Reversi Board. This houses all the rules for Reversi the game
 *
 */

public class Reversi {
	Piece[][] board;
	Piece turn;

	/**
	 * Constructor
	 */
	public Reversi() {
		board = new Piece[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = Piece.NONE;
			}
		}
		turn = Piece.BLACK;
	}
	
	//Deep copy constructer
	public Reversi(Reversi otherGame) {
		this.board = new Piece[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				this.board[i][j] = otherGame.board[i][j];
			}
		}

		this.turn = otherGame.turn;

	}
	
	
	//sets what colors turn it is
	public void setTurn(Piece type) throws IllegalArgumentException {
		if (type == Piece.NONE) {
			throw new IllegalArgumentException();
		}
		this.turn = type;
	}

	public Piece getTurn() {
		return this.turn;
	}
	
	//Puts a piece at a specific square
	public void setSquare(int row, int col, Piece type)
			throws NoSuchElementException {
		if (((row > 7) || (row < 0)) || ((col > 7) || (col < 0))) {
			throw new NoSuchElementException();
		}
		board[row][col] = type;
	}
	
	//retursns what Peice is at a specific square
	public Piece getSquare(int row, int col) throws NoSuchElementException {
		if (((row > 7) || (row < 0)) || ((col > 7) || (col < 0))) {
			throw new NoSuchElementException();
		}
		return board[row][col];
	}
	
	/**
	 * Count the Number of pieces on the board
	 * @param type
	 * @return # of pieces on the board
	 */
	public int count(Piece type) {
		int cnt = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (type == board[i][j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	/**
	 * Resets board to new game position
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void reset(Piece type) throws IllegalArgumentException {
		if (type == Piece.NONE) {
			throw new IllegalArgumentException();
		}
		Reversi temp = new Reversi();
		this.board = temp.board;
		this.setSquare(4, 4, Piece.BLACK);
		this.setSquare(3, 3, Piece.BLACK);
		this.setSquare(3, 4, Piece.WHITE);
		this.setSquare(4, 3, Piece.WHITE);
		this.turn = type;

	}
	
	/**
	 * toString method that prints out row and columns
	 * pieces on board
	 */
	public String toString() {
		String s = new String();
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {
					s = s + (i) + " " + this.getSquare(i, j).toString() + " ";
				} else if (j == 7) {
					s = s + this.getSquare(i, j).toString();
					s = s + "\n";
				} else {
					s = s + this.getSquare(i, j).toString() + " ";
				}
			}
		}
		return s + "  0 1 2 3 4 5 6 7\n";
	}

	/**
	 * This puts the next piece to look based on the dir. Ex: since
	 * 2 is directly above piece we just new to keep increase the row value to
	 * look at the next piece above it.
	 * @param row
	 * @param col
	 * @param dir
	 * @return a Pos object holding the col and row of next peice in dir
	 */
	private Pos dirAcc(int row, int col, int dir) {
		switch (dir) {
		case 1:
			row++;
			col--;
			break;
		case 2:
			row++;
			break;
		case 3:
			row++;
			col++;
			break;
		case 4:
			col--;
			break;
		case 5:
			col++;
			break;
		case 6:
			row--;
			col--;
			break;
		case 7:
			row--;
			break;
		case 8:
			row--;
			col++;
			break;
		}
		return new Pos(row, col, dir);
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param Pos
	 * @return Dir: 1 2 3 p=piece 4 p 5 6 7 8 Enter in an integer direction to
	 *         start searching for a piece of other same color to trap. If blank
	 *         space is found return negative Pos.x,y values saying no trap was
	 *         found
	 */
	private Pos findMove(int row, int col, int dir) {
		Piece look = (this.getTurn() == Piece.BLACK) ? (Piece.WHITE)
				: (Piece.BLACK); // THis is the Piece we are looking to trap
		int r = row, c = col;
		int state = 0;

		// While inside board
		while ((r < 7) || (r > 0)) {
			while ((c < 7) || (c > 0)) {

				// This update the postion of where we are looking based on dir
				// given
				Pos temp = dirAcc(r, c, dir);
				r = temp.getX();
				c = temp.getY();

				/*
				 * This is a state machine: First state check if next color in
				 * dir is opposite of turn Second state check if trap was found
				 * or if still searching
				 */
				switch (state) {
				case 0:
					// On Border of board with out finding any opposite color so
					// no trap
					temp = dirAcc(r, c, dir);
					int futx = temp.getX(); // future x value in this dir
					int futy = temp.getY(); // future y value in this dir

					if (futx > 7 || futx < 0 || futy > 7 || futy < 0) {
						return new Pos(-1, -1, 0);

					}
					if (this.getSquare(r, c) == this.getTurn()
							|| this.getSquare(r, c) == Piece.NONE) {
						return new Pos(-1, -1, 0); // not valid move in this dir

					} else {
						state = 1; // Next Piece value in this Dir was opposite
									// of turn
					}
					break;
				case 1:
					temp = dirAcc(r, c, dir);
					futx = temp.getX(); // future x value in this dir
					futy = temp.getY(); // future y value in this dir
					// Found another piece with same value as turn to trap with
					// so output where you found it;
					if (this.getSquare(r, c) == this.getTurn()) {
						return new Pos(r, c, dir);

						// On Border of board so no way there can be a trap
						// Return: No trap
					} else if (futx > 7 || futx < 0 || futy > 7 || futy < 0) {
						return new Pos(-1, -1, 0);

						// Found another opposite color keep looking in same dir
					} else if (this.getSquare(r, c) == look) {
						state = 1; // Dont really need

						// Found dead a none square so no trap was found in this
						// dir
					} else if (this.getSquare(r, c) == Piece.NONE) {
						return new Pos(-1, -1, 0);
					}
					break;

				}
			}
		}
		// Went passed border while searching so no trap was found
		return new Pos(-1, -1, 0);

	}

	/**
	 *  This inner class allow me to return a two integers when a position and dir of a
	 *   trap piece that is found
	 * @author Jacob Knapo
     *	This inner class return two integers when a position(col=x & row=y) and dir of a
	 *   trap piece that is found. (0,0) is top right.
	 */
	class Pos {
		private int x, y, dir;
		
		Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		Pos(Pos p) {
			this.x = p.getX();
			this.y = p.getY();
			this.dir = p.getDir();
		}

		private int getX() {
			return x;
		}

		private int getY() {
			return y;
		}

		private int getDir() {
			return dir;
		}
		
		//Determines if pos is off of board
		private boolean isTrap() {
			if (this.getX() < 0 || this.getY() < 0) {
				return false;
			}
			return true;
		}

		// Because only this class can access this equals metthod
		// I am going to assume an object of type p is passed to it
		// so i can just compare x and y vals
		private boolean equals(Pos p) {
			return this.x == p.getX() && this.y == p.getY();
		}

	}

	/**
	 * Determines if placing type onto the board will cause a flip
	 * @param row
	 * @param col
	 * @param type
	 * @return True: if peice at row,col will cause a flip
	 * 		   False: otherwise	
	 */
	public boolean validMove(int row, int col, Piece type) {
		// If inside board and not a NONE piece.
		if (((row <= 7) && (row >= 0)) && ((col <= 7) && (col >= 0))
				&& (type != Piece.NONE)
				&& this.getSquare(row, col) == Piece.NONE) {

			// Checks each direction from current piece until finds trap
			for (int i = 1; i < 9; i++) {
				// If trap found in this dir return true
				if (findMove(row, col, i).isTrap()) {
					return true;
				}
			}
			// No trap found in any dir
			return false;
		}
		return false; // making dat compiler happy
	}

	/**
	 * For every piece in the dir towards the trap completing piece
	 * flip to other color 
	 * @param current
	 * @param trap
	 */
	private void flip(Pos current, Pos trap) {
		
		while (!(current.equals(trap))) {
			//Create a new pos in dir of trap
			current = new Pos(this.dirAcc(current.getX(), current.getY(),
					trap.getDir()));
			//Flip next piece in trap.dir
			this.setSquare(current.getX(), current.getY(), this.getTurn());
		}
	}

	/**
	 * Determines if a valid move is available. If not do nothing.
	 * If there is a valid move then find all the moves and
	 * and flip the respective peices  
	 * @param row
	 * @param col
	 */
	public void move(int row, int col) {
		//Check for valid move if so continue
		if (this.validMove(row, col, this.getTurn())) {
			//Place piece at row,col
			this.setSquare(row, col, this.getTurn());
			//Go through all 8 dirs and find traps then flip them
			for (int i = 1; i < 9; i++) {
				Pos current = new Pos(row, col, 0); // Current piece being
													// placed on board
				Pos trap = findMove(row, col, i); // Piece to complete trap
				if (trap.isTrap()) {
					flip(current, trap);
				}
			}
			Piece next_turn = (this.getTurn() == Piece.BLACK) ? (Piece.WHITE)
					: (Piece.BLACK); // This will be the color of whos next turn
										// it is
			this.setTurn(next_turn);
		}
	}

	public void move(int row, int col, Piece type) {
		if (this.getTurn() == type && this.validMove(row, col, this.getTurn())) {
			this.setSquare(row, col, type);
			for (int i = 1; i < 9; i++) {
				Pos current = new Pos(row, col, 0); // Current piece being
													// placed on board
				Pos trap = findMove(row, col, i); // Piece to complete trap
				if (trap.isTrap()) {
					flip(current, trap);
				}
			}
			Piece next_turn = (this.getTurn() == Piece.BLACK) ? (Piece.WHITE)
					: (Piece.BLACK); // This will be the color of whos next turn
										// it is
			this.setTurn(next_turn);
		}
	}

}
