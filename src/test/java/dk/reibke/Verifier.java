package dk.reibke;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collection;

public class Verifier {

    public static <T> void assertCollection(Collection<T> expected, Collection<T> actual) {
        if (expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected)) {
            return;
        }

        ArrayList<T> expectedDiff = new ArrayList<>(expected);
        ArrayList<T> actualDiff = new ArrayList<>(actual);
        expectedDiff.removeAll(actual);
        actualDiff.removeAll(expected);

        Assertions.fail(() -> String.format("""
                        Failed to assert:\s
                        expected: [%s]\s
                        actual  : [%s]\s
                        ----------------
                        expected not in actual: [%s]\s
                        actual not in expected: [%s]\s
                        """,
                expected, actual,
                expectedDiff, actualDiff));
    }
}
