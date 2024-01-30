package most_active_cookie.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CSVQuery {

  private static final List<String> EMPTY_LIST = new ArrayList<>();
  public CSVQuery() {
  }

  public Map<String, Integer> filterByDateAndCount(final Map<String, List<String>> data, String date) {
    Map<String, Integer> transform = data
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, e -> {
          List<String> timestamps = e.getValue();
          return (int) timestamps.stream().filter(timestamp -> timestamp.contains(date)).count();
        }));

    return transform;
  }

  public List<String> getMostActiveCookie(final Map<String, List<String>> data, String date) {
    Map<String, Integer> transform = filterByDateAndCount(data, date);
    int maxCount = Collections.max(transform.values());
    if (maxCount == 0) {
      return EMPTY_LIST;
    }

    return transform
        .entrySet()
        .stream()
        .filter(e -> e.getValue().equals(maxCount))
        .map(Entry::getKey)
        .collect(Collectors.toList());
  }
}
