package cli.parser.factory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CSVParserTest {
    @Test
    public void assureCSVParserWorksWhenCorrectDataPassed() {
        final CSVParser csvParserTest = new CSVParser();
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/01/1987");
        final List<Map<String, Object>> result = csvParserTest.parse(fileContent, 1);
        assertEquals(1, result.size());
    }

    @Test
    public void assureCSVParserFailsWhenIncorrectDataPassed() {
        final CSVParser csvParserTest = new CSVParser();
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/01/1987,teset");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            csvParserTest.parse(fileContent, 1);
        });
        String expectedMessage = "Length of data is not 6";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void assureCSVParserFailsWhenIncorrectDatePassed() {
        final CSVParser csvParserTest = new CSVParser();
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/13/aa");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            csvParserTest.parse(fileContent, 1);
        });
        String expectedMessage = "Error parsing date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
