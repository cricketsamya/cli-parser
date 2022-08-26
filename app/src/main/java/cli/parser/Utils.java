package cli.parser;

import cli.parser.dto.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

public class Utils {

    public static String convertPersonListToJSON(final List<Person> personList) {
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
}
