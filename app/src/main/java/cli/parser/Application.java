package cli.parser;

import cli.parser.factory.*;
import org.apache.commons.lang3.NotImplementedException;

import java.io.BufferedReader;
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
                    final Converter c = new JSONConverter(new CSVParser());
                    System.out.println(c.convert(fileLines));
                } else if (args[1].equalsIgnoreCase("html")) {
                    throw new NotImplementedException("Not Yet Implemented");
                }
            } else if (args[0].equalsIgnoreCase("prn")) {
                if (args[1].equalsIgnoreCase("json")) {
                    final Converter c = new JSONConverter(new PRNParser());
                    System.out.println(c.convert(fileLines));
                } else if (args[1].equalsIgnoreCase("html")) {
                    throw new NotImplementedException("Not Yet Implemented");
                }
            }
        } catch (Exception e) {
            // TODO: Add error handler
        }
    }


}
