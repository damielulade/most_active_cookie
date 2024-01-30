package most_active_cookie.query;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CSVQuery {

  public CSVQuery() {
  }

  public Map<String, Integer> filterByDateAndCount(Map<String, List<String>> data, String date) {
    Map<String, Integer> transform = data
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, e -> {
          List<String> timestamps = e.getValue();
          timestamps.removeIf(timestamp -> !timestamp.contains(date));
          return timestamps.size();
        }));

    return transform;
  }

  public List<String> getMostActiveCookie(Map<String, List<String>> data, String date) {
    Map<String, Integer> transform = filterByDateAndCount(data, date);
    int maxCount = Collections.max(transform.values());
    return transform
        .entrySet()
        .stream()
        .filter(e -> e.getValue().equals(maxCount))
        .map(Entry::getKey)
        .collect(Collectors.toList());
  }
}
