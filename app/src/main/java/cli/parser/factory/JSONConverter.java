package cli.parser.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JSONConverter implements Converter {
    private static final Logger LOG = LoggerFactory.getLogger(JSONConverter.class);
    private final Parser parser;
    private final Integer currencyDivisor;

    public JSONConverter(Parser parser, Integer currencyDivisor) {
        this.parser = parser;
        this.currencyDivisor = currencyDivisor;
    }

    @Override
    public String convert(List<String> fileContents) {
        final List<Map<String, Object>> personList = parser.parse(fileContents, currencyDivisor);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        try {
            mapper.writeValue(out, personList);
            final byte[] data = out.toByteArray();
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final JsonElement je = JsonParser.parseString(new String(data));
            return gson.toJson(je);
        } catch (final IOException e) {
            LOG.error("Error with mapping values to JSON", e);
        }
        throw new IllegalStateException("Cannot generate JSON from File");
    }
}
