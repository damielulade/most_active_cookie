package most_active_cookie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CSVReader {

  public CSVReader() {
  }

  private Stream<String> handleInputStream(InputStream in) {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
    return bufferedReader.lines();
  }

  public Optional<String> getFileContents(InputStream in) {
    try (Stream<String> lines = handleInputStream(in)) {
      String result = lines.reduce("", (temp, line) -> temp + line);
      return Optional.of(result);
    } catch (Exception e) {
      System.err.println(e);
      return Optional.empty();
    }
  }

  public Map<String, Integer> parseCSVData(InputStream in) {
    Map<String, Integer> data = new HashMap<>();
    try (Stream<String> lines = handleInputStream(in)) {
      lines.skip(1).forEach((line) -> {
        String[] record = line.split(",", 2);
        // data.put(record[0], record[1].split(","));
        data.merge(record[0], 1, (t, u) -> Integer.sum(t, u));
      });
    }

    return data;
  }
}
