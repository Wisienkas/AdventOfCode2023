package dk.reibke.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CubesTest {

    @ParameterizedTest
    @MethodSource("cubeData")
    void fromStringTest(String data, int expectedAmount, CubeColor expectedCubeColor) {
        Cubes cubes = Cubes.fromString(data);

        assertEquals(expectedAmount, cubes.amount());
        assertEquals(expectedCubeColor, cubes.cubeColor());
    }

    public static Stream<Arguments> cubeData() {
        return Stream.of(
                Arguments.of("8 green", 8, CubeColor.GREEN),
                Arguments.of(" 6 blue", 6, CubeColor.BLUE),
                Arguments.of("20 red", 20, CubeColor.RED)
        );
    }
}