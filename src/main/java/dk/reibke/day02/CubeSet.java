package dk.reibke.day02;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public record CubeSet(int red, int blue, int green) {


    public static CubeSet fromString(String data) {
        var builder = new CubeSetBuilder();
        Stream.of(data.split(","))
                .map(Cubes::fromString)
                .forEach(builder::setColor);

        return builder.build();
    }

    public CubeSet mergeWithMax(CubeSet other) {
        return new CubeSet(
                Math.max(red(), other.red()),
                Math.max(blue(), other.blue()),
                Math.max(green(), other.green())
        );
    }

    private static class CubeSetBuilder {

        private final Map<CubeColor, Integer> map = new HashMap<>();

        public CubeSetBuilder(){}

        public CubeSetBuilder setColor(Cubes cubes) {
            this.map.put(cubes.cubeColor(), cubes.amount());
            return this;
        }

        public CubeSet build() {
            return new CubeSet(
                    map.getOrDefault(CubeColor.RED, 0),
                    map.getOrDefault(CubeColor.BLUE, 0),
                    map.getOrDefault(CubeColor.GREEN, 0)
            );
        }
    }

}
