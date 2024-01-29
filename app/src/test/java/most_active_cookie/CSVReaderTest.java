package most_active_cookie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Logger;

class CSVReaderTest {

    static final Logger logger = Logger.getLogger(CSVReaderTest.class.getName());

    private InputStream getResource(String filename) throws Exception {
        InputStream in = CSVReaderTest.class.getResourceAsStream(filename);
        if (in == null) throw new NullPointerException(filename + " was not found.");
        return in;
        
    }

    @Test
    void canReadDataFromANonEmptyInputStream() throws Exception {
        String filename = "/cookie_log.csv";
        InputStream in = getResource(filename);

        CSVReader csvReader = new CSVReader();
        Optional<String> fileContents = csvReader.getFileContents(in);

        assertTrue(fileContents.isPresent(), "The contents of the file could not be returned.");
        
        logger.info(fileContents.get());
    }
}
