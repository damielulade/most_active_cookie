package most_active_cookie.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

class CSVReaderTest {

    static final Logger logger = Logger.getLogger(CSVReaderTest.class.getName());
    static final CSVReader csvReader = new CSVReader();

    private InputStream getResource(String filename) throws Exception {
        InputStream in = CSVReaderTest.class.getResourceAsStream(filename);
        if (in == null)
            throw new NullPointerException(filename + " was not found.");
        return in;
    }

    @Test
    void canReadDataFromANonEmptyInputStream() throws Exception {
        String filename = "/cookie_log.csv";
        InputStream in = getResource(filename);

        Optional<String> fileContents = csvReader.getFileContents(in);

        assertTrue(fileContents.isPresent(), "The contents of the file could not be returned.");
    }

    @Test
    void canParseCSVFileInCorrectFormat() throws Exception {
        String filename = "/cookie_log.csv";
        InputStream in = getResource(filename);
        Map<String, Integer> data = csvReader.getCookieCount(in);
        assertFalse(data.isEmpty(), "Files were not found");

        String key = "SAZuXPGUrfbcn5UA";
        Integer valueExpected = 2;
        assertEquals(valueExpected, data.get(key));
    }

    @Test
    void canHandleAnEmptyCSVFile() throws Exception {
        String filename = "/empty_file.csv";
        InputStream in = getResource(filename);
        Optional<String> fileContents = csvReader.getFileContents(in);
        assertTrue(fileContents.isPresent());
        assertEquals("", fileContents.get());
    }

    @Test
    void canHandleOnlyHeadersInACSVFile() throws Exception {
        String filename = "/only_headers.csv";
        InputStream in = getResource(filename);

        Optional<String> fileContents = csvReader.getFileContents(in);
        assertTrue(fileContents.isPresent());

        String expected = "cookie,timestamp";
        assertEquals(expected, fileContents.get());
    }

}
