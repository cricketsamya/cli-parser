package cli.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public static String convertMapToJSON(final List<Map<String, Object>> personList) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        try {
            mapper.writeValue(out, personList);
            final byte[] data = out.toByteArray();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(new String(data));
            return gson.toJson(je);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("");
    }

    public static String convertMapToHTML(final List<Map<String, Object>> personList) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        try {
            mapper.writeValue(out, personList);
            final byte[] data = out.toByteArray();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement je = JsonParser.parseString(new String(data));
            return gson.toJson(je);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("");
    }

    public static Map<String, Object> convertRowToMap(String[] headerRow, String[] row, String dateParserFormat) throws Exception {
        if (row.length == 6) {
            Map<String, Object> map = new HashMap<>();
            map.put(headerRow[0], row[0].replace("\"", ""));
            map.put(headerRow[1], row[1]);
            map.put(headerRow[2], row[2]);
            map.put(headerRow[3], row[3]);
            map.put(headerRow[4], Double.parseDouble(row[4]));
            map.put(headerRow[5], convertToDate(row[5], dateParserFormat));
            return map;
        }
        throw new UnexpectedException("Parsing issue");
    }

    public static Date convertToDate(String s, String dateFormat) throws ParseException {
        final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.parse(s);
    }
}
