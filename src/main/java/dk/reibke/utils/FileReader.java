package dk.reibke.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class FileReader {

    public Stream<String> readLines(String file) throws URISyntaxException, IOException {
        URL resource = this.getClass().getClassLoader().getResource(file);
        Path path = Path.of(Objects.requireNonNull(resource, String.format("Unable to find file: [%s]", file)).toURI());

        return Files.lines(path);
    }
}
