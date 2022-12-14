package cli.parser.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVParser extends AbstractParser implements Parser {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public List<Map<String, Object>> parse(final List<String> fileContents, final Integer currencyDivisor) {
        final List<Map<String, Object>> listOfParsedData = new ArrayList<>();
        String headerRow[] = null;
        boolean headerSkipped = false;
        for (String value : fileContents) {
            String[] row = value.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (!headerSkipped) {
                headerRow = row;
                headerSkipped = true;
                continue;
            }
            listOfParsedData.add(convertRowToMap(headerRow, row, DATE_FORMAT, currencyDivisor));
        }
        return listOfParsedData;
    }
}
