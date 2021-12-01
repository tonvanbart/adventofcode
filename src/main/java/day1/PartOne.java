package day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PartOne {

    public static void main(String[] args) throws Exception {
        var lines = Files.lines(Paths.get(ClassLoader.getSystemResource("day1/input.txt").toURI()));
        var solver = new Solver(Integer.MAX_VALUE);
        final var solution = solver.solve(lines.map(Integer::parseInt));
        System.out.printf("Result: %s", solution);
    }

    static class Solver {
        private Integer previous;

        Solver(Integer previous) {
            this.previous = previous;
        }

        Integer solve(Stream<Integer> numbers) {
            return numbers.map(i -> {
                var result = (i > previous ? 1 : 0);
                previous = i;
                return result;
            })
                    .reduce(Integer::sum)
                    .get();
        }
    }
}
