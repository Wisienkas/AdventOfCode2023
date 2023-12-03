package dk.reibke.day03;

public record SchematicNumber(int number, int startIndex, int endIndex, int line) {
    public boolean nearby(SchematicSymbol schematicSymbol) {
        return isWithinSameOrAdjacentLine(schematicSymbol)
                && isWithinSameOrAdjacentIndex(schematicSymbol);
    }

    private boolean isWithinSameOrAdjacentIndex(SchematicSymbol schematicSymbol) {
        return schematicSymbol.index() >= startIndex() - 1 &&
                schematicSymbol.index() <= endIndex(); // Due to endIndex being "Exclusive"
    }

    private boolean isWithinSameOrAdjacentLine(SchematicSymbol schematicSymbol) {
        return Math.abs(schematicSymbol.line() - line()) <= 1;
    }
}
