package dk.reibke.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Cubes(int amount, CubeColor cubeColor) {

    public static final Pattern NUMBER_EXTRACT_REGEX = Pattern.compile("\\D*(\\d+)\\D*");

    public static Cubes fromString(String data) {
        CubeColor cubeColor = CubeColor.fromData(data);
        Matcher matcher = NUMBER_EXTRACT_REGEX.matcher(data);
        if (!matcher.find()) {
            throw new RuntimeException(String.format("Unable to find any number in: [%s]", data));
        }
        int amount = Integer.parseInt(matcher.group(1));

        return new Cubes(amount, cubeColor);
    }

}
