package most_active_cookie.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

class CSVReaderTest {

    static final Logger logger = Logger.getLogger(CSVReaderTest.class.getName());
    static final FileReader csvReader = new CSVReader();

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

        // logger.info(fileContents.get());
    }

    @Test
    void canParseCSVFileInCorrectFormat() throws Exception {
        String filename = "/cookie_log.csv";
        InputStream in = getResource(filename);
        Map<String, Integer> data = csvReader.parseFileData(in);
        assertFalse(data.isEmpty(), "Files were not found");

        String keyExample = "SAZuXPGUrfbcn5UA";
        Integer valueExample = 2;
        assertEquals(valueExample, data.get(keyExample));

        // logger.info(data.get(keyExample)[0]);
        // assertTrue(data.get(keyExample)[0].equals(valueExample[0]));
        // logger.info(fileContents.get());
    }

}
