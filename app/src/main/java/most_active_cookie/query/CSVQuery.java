package most_active_cookie.query;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CSVQuery {

  public Map<String, List<String>> data;

  public CSVQuery(Map<String, List<String>> data) {
    this.data = data;
  }

  public Map<String, List<String>> filterByDate(String date) {
    Map<String, List<String>> transform = data
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, e -> {
          List<String> timestamps = e.getValue();
          timestamps.removeIf(timestamp -> !timestamp.contains(date));
          return timestamps;
        }));

    return transform;
  }
}
