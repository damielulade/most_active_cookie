package most_active_cookie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.text.html.Option;

public class CSVReader {

  public CSVReader() {
  }

  private Stream<String> handleInputStream(InputStream in) {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
    return bufferedReader.lines();
  }

  public Optional<String> getFileContents(InputStream in) {
    try {
      String result = handleInputStream(in).reduce("", (temp, line) -> temp + line);
      return Optional.of(result);
    } catch (Exception e) {
      System.err.println(e);
      return Optional.empty();
    }
  }
}
