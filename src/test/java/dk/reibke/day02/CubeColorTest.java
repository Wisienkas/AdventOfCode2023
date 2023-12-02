package dk.reibke.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CubeColorTest {

    @ParameterizedTest
    @MethodSource("cubeColorData")
    void fromStringTest(String data, CubeColor expectedCubeColor) {
        CubeColor cubeColor = CubeColor.fromData(data);

        assertEquals(cubeColor, expectedCubeColor);
    }

    public static Stream<Arguments> cubeColorData() {
        return Stream.of(
                Arguments.of("8 green", CubeColor.GREEN),
                Arguments.of("3 blue", CubeColor.BLUE),
                Arguments.of("20 red", CubeColor.RED)
        );
    }
}