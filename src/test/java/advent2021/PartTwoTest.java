package advent2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PartTwoTest {


    @Test
    void tryit() {
        final var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        IntStream.range(0, numbers.size() - 2)
                .map(ix -> numbers.get(ix) + numbers.get(ix + 1) + numbers.get(ix + 2))
                .forEach(i -> System.out.printf("%s\n", i));
    }
}
