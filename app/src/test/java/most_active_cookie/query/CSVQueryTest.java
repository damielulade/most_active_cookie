package most_active_cookie.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import most_active_cookie.parser.CSVReader;

public class CSVQueryTest {

  static final Logger logger = Logger.getLogger(CSVQueryTest.class.getName());
  public static CSVQuery query = new CSVQuery();
  public static Map<String, List<String>> data;

  private static InputStream getResource(String filename) throws Exception {
    InputStream in = CSVQueryTest.class.getResourceAsStream(filename);
    if (in == null)
      throw new NullPointerException(filename + " was not found.");
    return in;
  }

  @BeforeAll
  public static void setup() {
    String filename = "/cookie_log.csv";

    try (InputStream in = getResource(filename)) {
      data = new CSVReader().parseFile(in);
      // query = new CSVQuery(data);
    } catch (Exception e) {
      // e.printStackTrace();
      System.err.println(e);
      System.exit(1);
    }
  }

  @Test
  public void canFindTheMostActiveCookie() {
    List<String> result = query.getMostActiveCookie(data, "2018-12-09");
    System.err.println();
    assertEquals(1, result.size());
    assertEquals("AtY0laUfhglK3lC7", result.get(0));
  }
  
  @Test
  public void canReturnMultipleActiveCookies() {
    List<String> result = query.getMostActiveCookie(data, "2018-12-08");
    System.err.println();
    assertEquals(3, result.size());
    
    List<String> expected = Arrays.asList("SAZuXPGUrfbcn5UA", "4sMM2LxV07bPJzwf","fbcn5UAVanZf6UtG");
    assertTrue(result.containsAll(expected));
  }
  
  @Test
  public void returnsNothingIfDateNotFound() {
    List<String> result = query.getMostActiveCookie(data, "2018-12-06");
    System.err.println();
    System.err.println(result.get(0));
    assertTrue(result.isEmpty());
  }
}
