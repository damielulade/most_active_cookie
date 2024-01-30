package most_active_cookie.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class FileReader {

  protected Stream<String> handleInputStream(InputStream in) {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
    return bufferedReader.lines();
  }

  public abstract Optional<String> getFileContents(InputStream in);

  public abstract Map<String, Integer> parseFileData(InputStream in);

}