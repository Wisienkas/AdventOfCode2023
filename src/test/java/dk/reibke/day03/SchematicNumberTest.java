package dk.reibke.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchematicNumberTest {

    @Test
    void isTooFarLeft() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertFalse(schematicNumber.nearby(new SchematicSymbol("*", 0, 2)));
    }

    @Test
    void isTooFarRight() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertFalse(schematicNumber.nearby(new SchematicSymbol("*", 6, 2)));
    }

    @Test
    void isTooFarAbove() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertFalse(schematicNumber.nearby(new SchematicSymbol("*", 2, 0)));
    }

    @Test
    void isTooFarBelow() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertFalse(schematicNumber.nearby(new SchematicSymbol("*", 2, 4)));
    }

    @Test
    void isLeft() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 1, 2)));
    }

    @Test
    void isRight() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 5, 2)));
    }

    @Test
    void isAbove() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertAll(
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 2, 1))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 3, 1))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 4, 1)))
        );
    }
    @Test
    void isBelow() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertAll(
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 2, 3))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 3, 3))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 4, 3)))
        );
    }

    @Test
    void isDiagonal() {
        SchematicNumber schematicNumber = getSchematicNumber();
        assertAll(
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 1, 1))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 5, 1))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 1, 3))),
                () -> assertTrue(schematicNumber.nearby(new SchematicSymbol("*", 5, 3)))
        );
    }


    private static SchematicNumber getSchematicNumber() {
        return new SchematicNumber(343, 2, 5, 2);
    }

}