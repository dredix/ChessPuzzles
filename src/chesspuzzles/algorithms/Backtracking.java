package chesspuzzles.algorithms;

import java.util.List;
import java.util.Iterator;
//import chesspuzzles.knightstour.*;
import chesspuzzles.eightqueens.*;

public class Backtracking {

	private Problem problem;

	public Backtracking(Problem problem) {
		this.problem = problem;
	}

	public boolean solve() {
		if (problem.solved()) {
			return true;
		} else {
			List steps = problem.possibleSteps();
			for (Iterator i = steps.iterator(); i.hasNext();) {
				Step p = (Step) i.next();
				problem.execute(p);
				if (solve()) {
					return true;
				} else {
					problem.undo(p);
				}
			}
			return false;
		}
	}

	public String solution() {
		return problem.solution();
	}

	public static void main(String[] args) {
		//Backtracking bt = new Backtracking(new KnightsTour());
		Backtracking bt = new Backtracking(new EightQueens());
		if (bt.solve()) {
			System.out.println("It could be solved!!!");
		} else {
			System.out.println("It could not be solved...");
		}
		System.out.println("Solution:\n" + bt.problem.solution());
	}
}