package most_active_cookie.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CSVReader extends FileReader {

  public CSVReader() {
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

  public Map<String, Integer> parseFileData(InputStream in) {
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
