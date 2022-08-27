package cli.parser.factory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HTMLConverterTest {

    @Test
    public void assureHTMLConverterWorksWhenCorrectCSVDataPassed() {
        final HTMLConverter htmlConverter = new HTMLConverter(new CSVParser(), 1);
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name,Address,Postcode,Phone,Credit Limit,Birthday");
        fileContent.add("\"Johnson, John\",Voorstraat 32,3122gg,020 3849381,1000000,01/01/1987");
        final String result = htmlConverter.convert(fileContent);
        assertTrue(result.length() > 0);
        assertEquals("<table>\n" +
                        " <tbody>\n" +
                        "  <tr>\n" +
                        "   <th>Credit Limit</th>\n" +
                        "   <th>Address</th>\n" +
                        "   <th>Phone</th>\n" +
                        "   <th>Birthday</th>\n" +
                        "   <th>Postcode</th>\n" +
                        "   <th>Name</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "   <td>1000000.0</td>\n" +
                        "   <td>Voorstraat 32</td>\n" +
                        "   <td>020 3849381</td>\n" +
                        "   <td>Thu Jan 01 00:00:00 CET 1987</td>\n" +
                        "   <td>3122gg</td>\n" +
                        "   <td>Johnson, John</td>\n" +
                        "  </tr>\n" +
                        " </tbody>\n" +
                        "</table>"
                , result);
    }

    @Test
    public void assureHTMLConverterWorksWhenCorrectPRNDataPassed() {
        final HTMLConverter htmlConverter = new HTMLConverter(new PRNParser(), 100);
        List<String> fileContent = new ArrayList<>();
        fileContent.add("Name            Address               Postcode Phone         Credit Limit Birthday");
        fileContent.add("Anderson, Paul  Dorpsplein 3A         4532 AA  030 3458986       10909300 19651203");
        final String result = htmlConverter.convert(fileContent);
        assertTrue(result.length() > 0);
        assertEquals("<table>\n" +
                        " <tbody>\n" +
                        "  <tr>\n" +
                        "   <th>Credit Limit</th>\n" +
                        "   <th>Address</th>\n" +
                        "   <th>Phone</th>\n" +
                        "   <th>Birthday</th>\n" +
                        "   <th>Postcode</th>\n" +
                        "   <th>Name</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "   <td>109093.0</td>\n" +
                        "   <td>Dorpsplein 3A</td>\n" +
                        "   <td>030 3458986</td>\n" +
                        "   <td>Fri Dec 03 00:00:00 CET 1965</td>\n" +
                        "   <td>4532 AA</td>\n" +
                        "   <td>Anderson, Paul</td>\n" +
                        "  </tr>\n" +
                        " </tbody>\n" +
                        "</table>"
                , result);
    }
}
