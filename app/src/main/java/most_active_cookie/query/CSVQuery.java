package most_active_cookie.query;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CSVQuery {

  public Map<String, List<String>> data;

  public CSVQuery(Map<String, List<String>> data) {
    this.data = data;
  }

  public Map<String, Integer> filterByDateAndCount(String date) {
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

  public List<String> getMostActiveCookie(String date) {
    Map<String, Integer> transform = filterByDateAndCount(date);
    int maxCount = Collections.max(transform.values());
    return transform
        .entrySet()
        .stream()
        .filter(e -> e.getValue().equals(maxCount))
        .map(Entry::getKey)
        .collect(Collectors.toList());
  }
}
