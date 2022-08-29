package cli.parser.factory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JSONConverterTest {

    @Test
    public void assureJSONConverterWorksWhenCorrectCSVDataPassed() {
        final JSONConverter jsonConverter = new JSONConverter(new CSVParser(), 1);
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/01/1987");
        final String result = jsonConverter.convert(fileContent);
        assertTrue(result.length() > 0);
        assertEquals("[\n" +
                        "  {\n" +
                        "    \"Credit Limit\": 1000000.0,\n" +
                        "    \"Address\": \"Voorstraat 32\",\n" +
                        "    \"Phone\": \"020 3849381\",\n" +
                        "    \"Birthday\": 536454000000,\n" +
                        "    \"Postcode\": \"3122gg\",\n" +
                        "    \"Name\": \"Johnson, John\"\n" +
                        "  }\n" + "]"
                , result);
    }

    @Test
    public void assureJSONConverterWorksWhenCorrectPRNDataPassed() {
        final JSONConverter jsonConverter = new JSONConverter(new PRNParser(), 100);
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name            Address               Postcode Phone         Credit Limit Birthday");
        fileContent.add("Anderson, Paul  Dorpsplein 3A         4532 AA  030 3458986       10909300 19651203");
        final String result = jsonConverter.convert(fileContent);
        assertTrue(result.length() > 0);
        assertEquals("[\n" +
                        "  {\n" +
                        "    \"Credit Limit\": 109093.0,\n" +
                        "    \"Address\": \"Dorpsplein 3A\",\n" +
                        "    \"Phone\": \"030 3458986\",\n" +
                        "    \"Birthday\": -128739600000,\n" +
                        "    \"Postcode\": \"4532 AA\",\n" +
                        "    \"Name\": \"Anderson, Paul\"\n" +
                        "  }\n" + "]"
                , result);
    }

    @Test
    public void assureJSONConverterFailsWhenIncorrectDatePassed() {
        final JSONConverter jsonConverter = new JSONConverter(new CSVParser(), 1);
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/01/fff");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            jsonConverter.convert(fileContent);
        });
        String expectedMessage = "Error parsing date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
