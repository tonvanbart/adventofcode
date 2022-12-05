package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day4 {
    public static void main(String[] args) throws Exception {
        new Day4().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day4";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());
        var count = 0;
        var overlaps = 0;
        for (var line: lines) {
            System.out.println("line = " + line);
            var ranges = line.split(",");
            var range1 =  Range.range(ranges[0].split("-"));
            var range2 =  Range.range(ranges[1].split("-"));
            if (range1.fullyContains(range2) || range2.fullyContains(range1)) {
                count++;
            }
            if (range1.overlaps(range2) || range1.fullyContains(range2)) {
                overlaps++;
            }
        }
        System.out.println("count = "+count);
        System.out.println("overlaps = " + overlaps);
    }

    @Data
    @AllArgsConstructor
    static class Range {
        int low, high;

        public static Range range(String[] split) {
//            System.out.println("Range("+split[0] + "," + split[1]+")");
            final var i = Integer.parseInt(split[0]);
            final var i1 = Integer.parseInt(split[1]);
            return new Range(i, i1);
        }

        boolean fullyContains(Range other) {
            var result = this.low <= other.low && this.high >= other.high;
//            System.out.printf("[%d,%d].fullyContains([%d,%d]): %s%n", this.low, this.high, other.low, other.high, result);
            return result;
        }

        boolean overlaps(Range other) {
            var result = (this.low >= other.low && this.low <= other.high) ||
                    (this.high >= other.low && this.high <= other.high);
            System.out.printf("[%d,%d].overlaps([%d,%d]): %s%n", this.low, this.high, other.low, other.high, result);
            return result;
        }
    }
}
