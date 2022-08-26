package cli.parser;

import cli.parser.factory.CSVToJSONParser;
import cli.parser.factory.PRNToJSONParser;
import cli.parser.factory.Parser;
import org.apache.commons.lang3.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        try {
            List<String> fileLines = new ArrayList<>();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                fileLines.add(line);
            }
            if (args[0].equalsIgnoreCase("csv")) {
                if (args[1].equalsIgnoreCase("json")) {
                    final Parser p = new CSVToJSONParser();
                    System.out.println(p.parse(fileLines));
                } else if (args[1].equalsIgnoreCase("html")) {
                    throw new NotImplementedException("Not Yet Implemented");
                }
            } else if (args[0].equalsIgnoreCase("prn")) {
                if (args[1].equalsIgnoreCase("json")) {
                    final Parser p = new PRNToJSONParser();
                    System.out.println(p.parse(fileLines));
                } else if (args[1].equalsIgnoreCase("html")) {
                    throw new NotImplementedException("Not Yet Implemented");
                }
            }
        } catch (Exception e) {
            // TODO: Add error handler
        }
    }


}
