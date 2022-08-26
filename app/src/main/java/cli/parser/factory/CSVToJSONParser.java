package cli.parser.factory;

import cli.parser.Utils;
import cli.parser.dto.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CSVToJSONParser implements Parser {
    @Override
    public String parse(String fileName) {
        //"app/src/main/resources/Workbook2.csv"
        final List<Person> list = new ArrayList<>();
        try (final CSVReader reader = new CSVReader(new FileReader(fileName, StandardCharsets.ISO_8859_1))) {
            List<String[]> r = reader.readAll();
            boolean headerSkipped = false;
            for (final String[] row : r) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                list.add(generateCSVToJSON(row));
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

    private static Person generateCSVToJSON(String[] row) throws Exception {
        if (row.length == 6) {
            final Person person = new Person();
            person.setName(row[0]);
            person.setAddress(row[1]);
            person.setPostcode(row[2]);
            person.setPhone(row[3]);
            person.setCreditLimit(Double.parseDouble(row[4]));
            person.setBirthday(convertToDate(row[5]));
            return person;
        }
        throw new UnexpectedException("Parsing issue");
    }

    private static Date convertToDate(String s) throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(s);
    }
}
