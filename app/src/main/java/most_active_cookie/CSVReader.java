package most_active_cookie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class CSVReader {

  public CSVReader() { }

  private Optional<String> parseInputStream(InputStream in) {
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
      StringBuilder stringBuilder = new StringBuilder();

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
      return Optional.of(stringBuilder.toString());

    } catch (Exception e) {
      System.err.println(e);
      return Optional.empty();
    }
  }

  public Optional<String> getFileContents(InputStream in) {
    return parseInputStream(in);
  }

}
