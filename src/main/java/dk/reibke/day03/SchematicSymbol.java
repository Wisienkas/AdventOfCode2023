package dk.reibke.day03;

public record SchematicSymbol(String symbol, int index, int line) {
    public boolean isGear() {
        return symbol.contentEquals("*");
    }
}
