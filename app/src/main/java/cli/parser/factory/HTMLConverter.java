package cli.parser.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;

public class HTMLConverter implements Converter {

    private final Parser parser;
    private final Integer currencyDivisor;

    public HTMLConverter(Parser parser, Integer currencyDivisor) {
        this.parser = parser;
        this.currencyDivisor = currencyDivisor;
    }

    @Override
    public String convert(List<String> fileContents) {
        final List<Map<String, Object>> parsedData = parser.parse(fileContents, currencyDivisor);
        final StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<html><head></head><body><table>");
        htmlTable.append(addHeaders(parsedData));
        for (Map<String, Object> rows : parsedData) {
            for (String key : rows.keySet()) {
                htmlTable.append("<td>");
                htmlTable.append(rows.get(key));
                htmlTable.append("<td/>");
            }
        }
        htmlTable.append("</table></body></html>");
        final Document doc = Jsoup.parseBodyFragment(htmlTable.toString());
        return doc.body().html();
    }

    private StringBuilder addHeaders(final List<Map<String, Object>> data) {
        final StringBuilder header = new StringBuilder();
        header.append("<tr>");
        final Map<String, Object> row = data.get(1);
        for (String key : row.keySet()) {
            header.append("<th>");
            header.append(key);
            header.append("<th/>");
        }
        header.append("</tr>");
        return header;
    }
}
