package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day5 {
    public static void main(String[] args) throws Exception {
        new Day5().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day5";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());
    }
}
