package advent2022;

import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day9 {
    public static void main(String[] args) throws Exception {
        new Day9().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day9";
        var file = "example";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());
        
        var head = new Pos(0,0);
        var tail = new Pos(0,0);
        var visited = new HashSet<Pos>();
        visited.add(tail);

        for (var line: lines) {
            var parts = line.split(" ");
            var nr = Integer.parseInt(parts[1]);
            if (parts[1].equals("L")) {
                handleLeft(nr, head, tail, visited);
            } else if (parts[1].equals("R")) {
                handleRight(nr, head, tail, visited);
            } else if (parts[1].equals("U")) {
                handleUp(nr, head, tail, visited);
            } else if (parts[1].equals("D")) {
                handleDown(nr, head, tail, visited);
            }
        }

    }

    private void handleLeft(int nr, Pos head, Pos tail, HashSet<Pos> visited) {

    }

    private void handleRight(int nr, Pos head, Pos tail, HashSet<Pos> visited) {

    }

    private void handleUp(int nr, Pos head, Pos tail, HashSet<Pos> visited) {

    }

    private void handleDown(int nr, Pos head, Pos tail, HashSet<Pos> visited) {

    }

    @Data
    @AllArgsConstructor
    static class Pos {
        Integer x;
        Integer y;
    }
}
