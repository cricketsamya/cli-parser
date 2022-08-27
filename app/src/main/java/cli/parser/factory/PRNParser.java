package cli.parser.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PRNParser extends AbstractParser implements Parser {
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final int SIZE_NAME = 16;
    private static final int SIZE_ADDRESS = 22;
    private static final int SIZE_POSTCODE = 9;
    private static final int SIZE_PHONE = 14;
    private static final int SIZE_CREDIT_LIMIT = 13;
    private static final int SIZE_BIRTHDAY = 8;

    @Override
    public List<Map<String, Object>> parse(List<String> fileContents, Integer currencyDivisor) {
        final List<Map<String, Object>> listOfParsedData = new ArrayList<>();
        final List<Integer> columnSizes = Arrays.asList(SIZE_NAME, SIZE_ADDRESS, SIZE_POSTCODE, SIZE_PHONE, SIZE_CREDIT_LIMIT, SIZE_BIRTHDAY);
        String headerRow[] = null;
        boolean headerSkipped = false;
        for (String line : fileContents) {
            if (line.trim().equals("")) {
                continue;
            }
            String[] rowSplit = splitStringBySizes(line, columnSizes);
            if (!headerSkipped) {
                headerRow = rowSplit;
                headerSkipped = true;
                continue;
            }
            listOfParsedData.add(convertRowToMap(headerRow, rowSplit, DATE_FORMAT, currencyDivisor));
        }
        return listOfParsedData;
    }

    public static String[] splitStringBySizes(String row, List<Integer> columnSizes) {
        List<String> list = new ArrayList<>();
        int chunkStart = 0;
        int chunkEnd = 0;
        for (Integer length : columnSizes) {
            chunkStart = chunkEnd;
            chunkEnd = chunkStart + length;
            String dataChunk = row.substring(chunkStart, chunkEnd);
            list.add(dataChunk.trim());
        }
        return list.toArray(new String[0]);
    }
}
