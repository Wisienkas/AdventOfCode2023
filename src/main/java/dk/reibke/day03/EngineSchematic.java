package dk.reibke.day03;

import dk.reibke.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class EngineSchematic {


    private final List<SchematicNumber> schematicNumbers;
    private final List<SchematicSymbol> schematicSymbols;

    public EngineSchematic(Stream<String> lines) {
        AtomicInteger lineNumber = new AtomicInteger(0);
        Pair<List<SchematicNumber>, List<SchematicSymbol>> schematics = lines
                .map(line -> getSchematics(line, lineNumber))
                .reduce((a, b) -> new Pair<>(
                        merge(a.first(), b.first()),
                        merge(a.second(), b.second())
                ))
                .orElseThrow(() -> new RuntimeException("Unable to find any Schematics at all!"));
        this.schematicNumbers = schematics.first();
        this.schematicSymbols = schematics.second();
    }

    public Long sumOfSchematicNumbers() {
        return schematicNumbers.stream()
                .filter(schematicNumber -> schematicSymbols.stream().anyMatch(schematicNumber::nearby))
                .mapToLong(SchematicNumber::number)
                .sum();
    }

    public Long sumGearRatios() {
        return schematicSymbols.stream()
                .filter(SchematicSymbol::isGear)
                .map(schematicSymbol ->
                        schematicNumbers.stream()
                                .filter(schematicNumber -> schematicNumber.nearby(schematicSymbol))
                                .toList()
                )
                .filter(list -> list.size() == 2)
                .mapToLong(list -> (long) list.get(0).number() * list.get(1).number())
                .sum();
    }

    private <T> List<T> merge(List<T> a, List<T> b) {
        List<T> list = new ArrayList<>(a);
        list.addAll(b);

        return list;
    }

    private static Pair<List<SchematicNumber>, List<SchematicSymbol>> getSchematics(String line, AtomicInteger lineNumber) {
        var schematicLineAnalyzer = new SchematicLineAnalyzer(line, lineNumber.getAndIncrement());
        List<SchematicNumber> schematicNumbers = schematicLineAnalyzer.findSchematicNumbers();
        List<SchematicSymbol> schematicSymbols = schematicLineAnalyzer.findSchematicSymbols();

        return new Pair<>(schematicNumbers, schematicSymbols);
    }
}
