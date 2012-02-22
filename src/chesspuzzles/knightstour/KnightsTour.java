package chesspuzzles.knightstour;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import chesspuzzles.algorithms.Problem;
import chesspuzzles.algorithms.Step;
import chesspuzzles.utils.*;

public class KnightsTour extends Problem {

	private int[][] board = new int[8][8];

	ILogger log;

    Stack<Point> moves;

    int tries = 0;

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

    public KnightsTour() {
		super();
		prepareBoard();
        moves = new Stack<Point>();
		try {
			log = new DefaultLogger("Log.txt");
		}
		catch(Exception ex) {
			log = new NullLogger();
		}
    }

	private void prepareBoard() {
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				board[i][j] = 0;
	}

	private boolean legal(int x, int y) {
		return (x >= 0 && x < 8 && y >= 0 && y < 8);
	}

	public boolean solved() {
		if(moves.size() >= 61) {
			log.println(solution());
            return true;
		}
		return false;
	}

	public List possibleSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
        if(moves.isEmpty()) {
            steps.add(new SetPiece(4, 4));
            steps.add(new SetPiece(4, 3));
            steps.add(new SetPiece(3, 4));
            steps.add(new SetPiece(3, 3));
            /*for(int i = 0; i < 8; ++i) {
                for(int j = 0; j < 8; ++j) {
                    steps.add(new SetPiece(i, j));
                }
            }*/
        }
        else {
            Point p = moves.peek();
            for(int i = 1; i <= 2; ++i) {
                for(int j = 1; j <= 4; ++j) {
                    int x = i * (j % 2 == 0 ? -1 : 1);
                    int y = (3 - i) * (j >= 3 ? -1 : 1);
                    if (legal(p.getX() + x, p.getY() + y) &&
                            board[p.getX() + x][p.getY() + y] == 0) {
                        steps.add(new SetPiece(p.getX() + x, p.getY() + y));
                    }
                }
            }
        }
		return steps;
	}

    public void addPiece(int x, int y, boolean add) {
		if (add) {
            moves.push(new Point(x, y));
			board[x][y] = moves.size();
        }
        else {
            board[x][y] = 0;
            moves.pop();
        }
    }

	public void execute(Step p) {
		SetPiece cp = (SetPiece) p;
		addPiece(cp.getX(), cp.getY(), true);
		++tries;
		//log.println("DoStep: (" + cp.getX() + "," + cp.getY() + ") " +
		//    moves.size() + " - " + ++tries);
	}

	public void undo(Step p) {
		SetPiece cp = (SetPiece) p;
		addPiece(cp.getX(), cp.getY(), false);
		++tries;
		//log.println("UndoStep: (" + cp.getX() + "," + cp.getY() + ") " +
		//    moves.size() + " - " + ++tries);
	}

	private int getSquare(int x, int y) {
		if(legal(x, y)) {
			return board[x][y];
		}
		else {
			return -1;
		}
	}

	public String solution() {
		String ret = "Tries: " + tries + "\r\n";
		for(int i = 0; i < 8; ++i) {
			for(int j = 0; j < 8; ++j) {
				ret += getSquare(i, j) + "\t";
			}
			ret += "\r\n";
		}
		return ret;
	}
}
