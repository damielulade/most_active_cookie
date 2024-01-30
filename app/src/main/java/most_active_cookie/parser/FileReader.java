package most_active_cookie.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FileReader {

  protected Stream<String> handleInputStream(InputStream in) {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
    return bufferedReader.lines();
  }

  public Optional<String> getFileContents(InputStream in) {
    try (Stream<String> lines = handleInputStream(in)) {
      String result = lines.collect(Collectors.joining("\n"));
      return Optional.of(result);
    } catch (Exception e) {
      System.err.println(e);
      return Optional.empty();
    }
  }

}