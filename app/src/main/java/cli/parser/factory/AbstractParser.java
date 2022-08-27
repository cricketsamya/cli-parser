package cli.parser.factory;

import cli.parser.exception.CustomParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractParser {
    private static final int INDEX_NAME = 0;
    private static final int INDEX_ADDRESS = 1;
    private static final int INDEX_POSTCODE = 2;
    private static final int INDEX_PHONE = 3;
    private static final int INDEX_CREDIT_LIMIT = 4;
    private static final int INDEX_BIRTHDAY = 5;
    private static final int MAX_NUMBER_OF_COLUMNS = 6;

    public static Map<String, Object> convertRowToMap(String[] headerRow, String[] row, String dateParserFormat, Integer currencyDivisor) throws ParseException {
        if (row.length == MAX_NUMBER_OF_COLUMNS) {
            Map<String, Object> mapOfRow = new HashMap<>();
            mapOfRow.put(headerRow[INDEX_NAME], row[INDEX_NAME].replace("\"", ""));
            mapOfRow.put(headerRow[INDEX_ADDRESS], row[INDEX_ADDRESS]);
            mapOfRow.put(headerRow[INDEX_POSTCODE], row[INDEX_POSTCODE]);
            mapOfRow.put(headerRow[INDEX_PHONE], row[INDEX_PHONE]);
            mapOfRow.put(headerRow[INDEX_CREDIT_LIMIT], Double.parseDouble(row[INDEX_CREDIT_LIMIT]) / currencyDivisor);
            mapOfRow.put(headerRow[INDEX_BIRTHDAY], convertToDate(row[INDEX_BIRTHDAY], dateParserFormat));
            return mapOfRow;
        }
        throw new IllegalStateException("Length of data is not 6");
    }

    public static Date convertToDate(String s, String dateFormat) throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.parse(s);
    }
}
