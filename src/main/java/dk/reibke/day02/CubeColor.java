package dk.reibke.day02;

import java.util.stream.Stream;

public enum CubeColor {
    RED,
    BLUE,
    GREEN;

    public static CubeColor fromData(String data) {
        return Stream.of(values())
                .filter(cubeColor -> data.toLowerCase().contains(cubeColor.name().toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find any cube color in: [%s]", data)));
    }
}
