package chesspuzzles.utils;

public interface ILogger {

	public interface Priority {

		public static final int MIN = 1;
		public static final int NORMAL = 2;
		public static final int MAX = 3;
	}

	public void log(int priority, String msg);

	public void log(String msg);

	public void print(String str);

	public void println(String str);

	public void println(int i);
}
