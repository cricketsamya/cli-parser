package cli.parser.factory;

import java.util.List;
import java.util.Map;

public interface Parser {
    public List<Map<String, Object>> parse(List<String> fileContents);
}
