package util;
import java.io.*;

public final class ConsoleReader {
  private static final String CHARSET_NAME = "shift-jis";
  private final BufferedReader reader;

  public ConsoleReader() {
    try {
      reader = new BufferedReader(new InputStreamReader(System.in, CHARSET_NAME));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
