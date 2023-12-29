package dk.reibke.day05;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslatorFactory {
    public static final Pattern TRANSLATOR_REGEX = Pattern.compile("(?<source>[a-zA-Z]+)-to-(?<destination>[a-zA-Z]+) map:");
    public static final Predicate<String> IS_TRANSLATOR_LINE = TRANSLATOR_REGEX.asPredicate();

    public static Translator createTranslator(String line, Iterator<String> iterator) {
        Matcher matcher = TRANSLATOR_REGEX.matcher(line);
        if (!matcher.find()) {
            throw new RuntimeException(String.format("Unable to find any match in: [%s]", line));
        }
        String source = matcher.group("source");
        String destination = matcher.group("destination");

        MapType sourceType = MapType.valueOf(source.toUpperCase());
        MapType destinationType = MapType.valueOf(destination.toUpperCase());

        List<Translation> translationList = TranslationFactory.createTranslationList(iterator);

        return new Translator(sourceType, destinationType, translationList);
    }
}
