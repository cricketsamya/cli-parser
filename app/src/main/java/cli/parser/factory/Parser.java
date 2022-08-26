package cli.parser.factory;

import java.util.List;

public interface Parser {
    public String parse(List<String> fileContents);
}
