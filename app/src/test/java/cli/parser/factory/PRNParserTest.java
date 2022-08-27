package cli.parser.factory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PRNParserTest {
    @Test
    public void assurePRNParserWorksWhenCorrectDataPassed() {
        final PRNParser prnParserTest = new PRNParser();
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name            Address               Postcode Phone         Credit Limit Birthday");
        fileContent.add("Anderson, Paul  Dorpsplein 3A         4532 AA  030 3458986       10909300 19651203");
        final List<Map<String, Object>> result = prnParserTest.parse(fileContent, 100);
        assertEquals(1, result.size());
    }

    @Test
    public void assurePRNParserFailsWhenIncorrectDataPassed() {
        final PRNParser prnParserTest = new PRNParser();
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name            Address               Postcode Phone         Credit Limit Birthday");
        fileContent.add("Anderson, Paul  Dorpsplein 3A         4532 AA  030 3458986       10909300    19651203");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            prnParserTest.parse(fileContent, 1);
        });
        String expectedMessage = "Error parsing date";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
