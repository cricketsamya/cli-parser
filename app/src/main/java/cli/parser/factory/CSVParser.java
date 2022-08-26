package cli.parser.factory;

import cli.parser.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVParser implements Parser {
    @Override
    public List<Map<String, Object>> parse(List<String> fileContents) {
        final List<Map<String, Object>> list = new ArrayList<>();
        String headerRow[] = null;
        try {
            boolean headerSkipped = false;
            for (String value : fileContents) {
                String[] row = value.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (!headerSkipped) {
                    headerRow = row;
                    headerSkipped = true;
                    continue;
                }
                list.add(Utils.convertRowToMap(headerRow, row, "dd/MM/yyyy"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
}
