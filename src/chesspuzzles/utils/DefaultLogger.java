package chesspuzzles.utils;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DefaultLogger implements ILogger {

	private PrintStream printStream_ = null;

	public DefaultLogger(String fileName) throws IOException {
		File file = new File(fileName);
		printStream_ = new PrintStream(new FileOutputStream(file, false));
		log(Priority.NORMAL, "Created:" + (new Date()));
	}

	public void log(int priority, String msg) {
		printStream_.println(Integer.toString(priority) + "\t"
				+ (new Date()).toString() + "\t" + msg);
	}

	public void log(String msg) {
		log(Priority.MIN, msg);
	}

	public void print(String str) {
		printStream_.print(str);
	}

	public void println(String str) {
		printStream_.println(str);
	}

	public void println(int i) {
		printStream_.println(i);
	}
}
