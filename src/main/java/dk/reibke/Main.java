package dk.reibke;

import dk.reibke.day01.CalibrationDocument;
import dk.reibke.utils.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        RunDay01();
    }

    private static void RunDay01() throws URISyntaxException, IOException {
        var calibrationDocument = new CalibrationDocument();
        Stream<String> lines = new FileReader().readLines("day01/input01.txt");
        Long sumLines = calibrationDocument.sumLines(lines, calibrationDocument::findDigitInLine);
        System.out.printf("Result for day 01, task 01 = [%s]%n", sumLines);

        lines = new FileReader().readLines("day01/input01.txt");
        Long sumFancyLines = calibrationDocument.sumLines(lines, calibrationDocument::findGenericDigitsInLine);
        System.out.printf("Result for day 01, task 02 = [%s]%n", sumFancyLines);
    }
}