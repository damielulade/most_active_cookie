package most_active_cookie.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CSVReader extends FileReader {

  public CSVReader() {
  }

  public Map<String, Integer> getCookieCount(InputStream in) {
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
