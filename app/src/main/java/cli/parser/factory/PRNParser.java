package cli.parser.factory;

import cli.parser.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PRNParser implements Parser {
    @Override
    public List<Map<String, Object>> parse(List<String> fileContents) {
        final List<Map<String, Object>> list = new ArrayList<>();
        final List<Integer> columnSizes = Arrays.asList(16, 22, 9, 14, 13, 8);
        try {
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
                list.add(Utils.convertRowToMap(headerRow, rowSplit, "yyyyMMdd"));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
