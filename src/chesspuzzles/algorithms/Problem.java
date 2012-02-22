package chesspuzzles.algorithms;

import java.util.List;

public abstract class Problem {

	public abstract boolean solved();

	public abstract List possibleSteps();

	public abstract void execute(Step p);

	public abstract void undo(Step p);

	public abstract String solution();
}