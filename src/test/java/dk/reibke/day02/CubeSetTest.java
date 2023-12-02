package dk.reibke.day02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CubeSetTest {

    @ParameterizedTest
    @MethodSource("cubeSetData")
    void fromStringTest(String data, CubeSet expectedCubeSet) {
        CubeSet cubeSet = CubeSet.fromString(data);

        assertEquals(expectedCubeSet, cubeSet);
    }

    @Test
    void mergeMaxTest() {
        var a = new CubeSet(5,8,3);
        var b = new CubeSet(3,20,3);

        CubeSet cubeSet = a.mergeWithMax(b);

        assertEquals(5, cubeSet.red());
        assertEquals(20, cubeSet.blue());
        assertEquals(3, cubeSet.green());
    }

    public static Stream<Arguments> cubeSetData() {
        return Stream.of(
                Arguments.of("8 green, 6 blue, 20 red", new CubeSet(20, 6, 8)),
                Arguments.of("3 green, 6 red", new CubeSet(6, 0, 3)),
                Arguments.of("20 red", new CubeSet(20, 0, 0))
        );
    }
}