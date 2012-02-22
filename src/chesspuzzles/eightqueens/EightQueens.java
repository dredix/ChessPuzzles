package chesspuzzles.eightqueens;

import java.util.List;
import java.util.ArrayList;
import chesspuzzles.algorithms.Problem;
import chesspuzzles.algorithms.Step;
import chesspuzzles.utils.*;

public class EightQueens extends Problem {

	private int[][] board = new int[8][8];
	private int column = 0;
	ILogger log;
	private int solutions = 0;

	private class SetPiece implements Step {

		private int x = 0;
		private int y = 0;

		public SetPiece(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}
	}

	public EightQueens() {
		super();
		prepareBoard();
		try {
			log = new DefaultLogger("Log.txt");
		} catch (Exception ex) {
			log = new NullLogger();
		}
	}

	private void prepareBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = 0;
			}
		}
	}

	private void addQueen(int x, int y, boolean add) {
		int i;
		if (add) {
			board[x][y] = 2;
		} else {
			board[x][y] = 0;
		}
		for (i = 0; i < 8; i++) {
			if (i != y) {
				changeSquare(x, i, add);
			}
			if (i != x) {
				changeSquare(i, y, add);
			}
		}
		for (i = 1; legal(x + i, y + i); i++) {
			changeSquare(x + i, y + i, add);
		}
		for (i = 1; legal(x + i, y - i); i++) {
			changeSquare(x + i, y - i, add);
		}
		for (i = 1; legal(x - i, y + i); i++) {
			changeSquare(x - i, y + i, add);
		}
		for (i = 1; legal(x - i, y - i); i++) {
			changeSquare(x - i, y - i, add);
		}

		if (!add) {
			for (i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == 2) {
						addQueen(i, j, true);
					}
				}
			}
		}
	}

	private void changeSquare(int x, int y, boolean add) {
		if (add && board[x][y] == 0) {
			board[x][y] = 1;
		} else if (!add && board[x][y] == 1) {
			board[x][y] = 0;
		}
	}

	private boolean legal(int x, int y) {
		return (x >= 0 && x < 8 && y >= 0 && y < 8);
	}

	public boolean solved() {
		if (column == 8) {
			log.println(++solutions);
			log.println(solution());
			return true;
		}
		return false;
	}

	public List possibleSteps() {
		ArrayList<Step> l = new ArrayList<Step>();
		for (int i = 0; i < 8; ++i) {
			if (legal(i, column) && board[i][column] == 0) {
				l.add(new SetPiece(i, column));
			}
		}
		return l;
	}

	public void execute(Step p) {
		SetPiece cp = (SetPiece) p;
		addQueen(cp.getX(), cp.getY(), true);
		column++;
	}

	public void undo(Step p) {
		SetPiece cp = (SetPiece) p;
		addQueen(cp.getX(), cp.getY(), false);
		column--;
	}

	private int getSquare(int x, int y) {
		if (legal(x, y)) {
			return board[x][y];
		} else {
			return -1;
		}
	}

	public String solution() {
		String ret = "";
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				ret += getSquare(i, j) + "\t";
			}
			ret += "\r\n";
		}
		return ret;
	}
}