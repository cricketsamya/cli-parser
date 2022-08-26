package cli.parser;

import cli.parser.factory.CSVToJSONParser;
import cli.parser.factory.PRNToJSONParser;
import cli.parser.factory.Parser;

public class Application {

    public static void main(String[] args) {

        final Parser p = new CSVToJSONParser();
        System.out.println(p.parse("app/src/main/resources/Workbook2.csv"));

        final Parser p1 = new PRNToJSONParser();
        System.out.println(p1.parse("app/src/main/resources/Workbook2.prn"));

    }


}
