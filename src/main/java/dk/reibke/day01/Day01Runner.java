package dk.reibke.day01;

import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Day01Runner {

    public static void run() throws URISyntaxException, IOException {
        var calibrationDocument = new CalibrationDocument();
        Stream<String> lines = new FileReader().readLines("day01/input01.txt");
        Long sumLines = calibrationDocument.sumLines(lines, calibrationDocument::findDigitInLine);
        System.out.printf("Result for day 01, task 01 = [%s]%n", sumLines);

        lines = new FileReader().readLines("day01/input01.txt");
        Long sumFancyLines = calibrationDocument.sumLines(lines, calibrationDocument::findGenericDigitsInLine);
        System.out.printf("Result for day 01, task 02 = [%s]%n", sumFancyLines);
    }
}
