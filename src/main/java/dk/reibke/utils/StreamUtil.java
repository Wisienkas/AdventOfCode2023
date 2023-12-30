package dk.reibke.utils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil {

    public static <A, B> Stream<Pair<A, B>> zipStream(List<A> a, List<B> b) {
        return IntStream.range(0, Math.max(a.size(), b.size()))
                .mapToObj(counter -> new Pair<>(a.get(counter % a.size()), b.get(counter % b.size())));
    }
}
