package cli.parser;

import cli.parser.factory.*;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(CSVParser.class);
    private static final int INDEX_FILE_TYPE = 0;
    private static final int INDEX_CONVERTER_TYPE = 1;
    private static final int PRN_CURRENCY_DIVISOR = 100;
    private static final int CSV_CURRENCY_DIVISOR = 1;

    public static void main(String[] args) {
        final List<String> fileLines = new ArrayList<>();
        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (final Exception e) {
            LOG.error("Error reading file from stdin", e);
        }
        if (fileLines.size() > 0) {
            if (args.length == 2) {
                if (args[INDEX_FILE_TYPE].equalsIgnoreCase("csv")) {
                    if (args[INDEX_CONVERTER_TYPE].equalsIgnoreCase("json")) {
                        final Converter jsonConverter = new JSONConverter(new CSVParser(), CSV_CURRENCY_DIVISOR);
                        System.out.println(jsonConverter.convert(fileLines));
                    } else if (args[INDEX_CONVERTER_TYPE].equalsIgnoreCase("html")) {
                        final Converter htmlConverter = new HTMLConverter(new CSVParser(), CSV_CURRENCY_DIVISOR);
                        System.out.println(htmlConverter.convert(fileLines));
                    }
                } else if (args[INDEX_FILE_TYPE].equalsIgnoreCase("prn")) {
                    if (args[INDEX_CONVERTER_TYPE].equalsIgnoreCase("json")) {
                        final Converter jsonConverter = new JSONConverter(new PRNParser(), PRN_CURRENCY_DIVISOR);
                        System.out.println(jsonConverter.convert(fileLines));
                    } else if (args[INDEX_CONVERTER_TYPE].equalsIgnoreCase("html")) {
                        final Converter htmlConverter = new HTMLConverter(new PRNParser(), PRN_CURRENCY_DIVISOR);
                        System.out.println(htmlConverter.convert(fileLines));
                    }
                }
            }
        }
    }
}


