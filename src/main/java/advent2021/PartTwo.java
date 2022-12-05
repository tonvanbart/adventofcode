package advent2021;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartTwo {

    public static void main(String[] args) throws Exception {
        var numbers = Files.lines(Paths.get(ClassLoader.getSystemResource("advent2021/input.txt").toURI()))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        final var windows = IntStream.range(0, numbers.size() - 2)
                .map(ix -> numbers.get(ix) + numbers.get(ix + 1) + numbers.get(ix + 2));
        System.out.println(new PartOne.Solver(Integer.MAX_VALUE).solve(windows.boxed()));

    }
}
