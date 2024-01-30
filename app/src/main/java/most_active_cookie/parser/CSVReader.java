package most_active_cookie.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader extends FileReader {

  public CSVReader() {
  }

  public Map<String, List<String>> parseFile(InputStream in) {
    Map<String, List<String>> data = new HashMap<>();
    try (Stream<String> lines = handleInputStream(in)) {
      lines.skip(1).forEach((line) -> {
        String[] record = line.split(",", 2);
        data.computeIfAbsent(record[0], k -> new ArrayList<>()).add(record[1]);
      });
    }

    return data;
  }

  public Map<String, Integer> getCookieCount(InputStream in) {
    return parseFile(in)
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().size()));
  }

}
