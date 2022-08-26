package cli.parser.factory;

import java.util.List;

public interface Converter {
    String convert(List<String> fileContents);
}
