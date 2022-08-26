package cli.parser.factory;

import cli.parser.Utils;

import java.util.List;

public class JSONConverter implements Converter {

    private final Parser parser;

    public JSONConverter(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String convert(List<String> fileContents) {
        return Utils.convertMapToJSON(parser.parse(fileContents));
    }
}
