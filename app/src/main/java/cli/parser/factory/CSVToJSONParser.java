package cli.parser.factory;

import cli.parser.Utils;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVToJSONParser implements Parser {
    @Override
    public String parse(String fileName) {
        //"app/src/main/resources/Workbook2.csv"
        final List<Map<String, Object>> list = new ArrayList<>();
        String headerRow[] = null;
        try (final CSVReader reader = new CSVReader(new FileReader(fileName, StandardCharsets.ISO_8859_1))) {
            List<String[]> r = reader.readAll();
            boolean headerSkipped = false;
            for (final String[] row : r) {
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
        return Utils.convertPersonListToJSON(list);
    }
}
