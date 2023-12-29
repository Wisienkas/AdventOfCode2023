package dk.reibke.day03;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dk.reibke.utils.Regexes.NUMBER_REGEX;

public class SchematicLineAnalyzer {

    public static final Pattern SYMBOL_REGEX = Pattern.compile("([^0-9a-zA-Z\\\\.])");
    private final String line;
    private final int lineNumber;

    public SchematicLineAnalyzer(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public List<SchematicNumber> findSchematicNumbers() {
        Matcher matcher = NUMBER_REGEX.matcher(line);
        return matcher.results()
                .map(this::toSchematicNumber)
                .toList();
    }

    private SchematicNumber toSchematicNumber(MatchResult matchResult) {
        int number = Integer.parseInt(matchResult.group());
        return new SchematicNumber(number, matchResult.start(), matchResult.end(), lineNumber);
    }

    public List<SchematicSymbol> findSchematicSymbols() {
        Matcher matcher = SYMBOL_REGEX.matcher(line);
        return matcher.results()
                .map(this::toSchematicSymbol)
                .toList();
    }

    private SchematicSymbol toSchematicSymbol(MatchResult matchResult) {
        return new SchematicSymbol(matchResult.group(), matchResult.start(), lineNumber);
    }
}
