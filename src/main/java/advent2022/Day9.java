package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.Math.abs;

public class Day9 {

    Set<Pos> visited = new HashSet<>();
    public static void main(String[] args) throws Exception {
        new Day9().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day9";
//        var file = "example";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));

        var head = new Pos(0,0);
        var tail = new Pos(0,0);
        visited.add(new Pos(0,0));

        for (var line: lines) {
            var parts = line.split(" ");
            var nr = Integer.parseInt(parts[1]);
            if (parts[0].equals("L")) {
                handleLeft(nr, head, tail);
            } else if (parts[0].equals("R")) {
                handleRight(nr, head, tail);
            } else if (parts[0].equals("U")) {
                handleUp(nr, head, tail);
            } else if (parts[0].equals("D")) {
                handleDown(nr, head, tail);
            }
        }
        System.out.println("visited.size() = " + visited.size());
//        printBoard();

    }

    private void handleLeft(int nr, Pos head, Pos tail) {
        for (int i = 0; i < nr; i++) {
            head.x = head.x -1;
            if (!head.adjacent(tail)) {
                tail.x--;
                if (head.y!=tail.y) tail.y = head.y;
                visited.add(new Pos(tail.x, tail.y));
            }
        }
    }

    private void handleRight(int nr, Pos head, Pos tail) {
        for (int i = 0; i < nr; i++) {
            head.x = head.x+1;
            if (!head.adjacent(tail)) {
                tail.x++;
                if (head.y != tail.y) tail.y = head.y;
                visited.add(new Pos(tail.x, tail.y));
            }
        }
    }

    private void handleUp(int nr, Pos head, Pos tail) {
        for (int i = 0; i < nr; i++) {
            head.y = head.y+1;
            if (!head.adjacent(tail)) {
                tail.y++;
                if (head.x != tail.x) tail.x=head.x;
                visited.add(new Pos(tail.x, tail.y));
            }
        }
    }

    private void handleDown(int nr, Pos head, Pos tail) {
        for (int i = 0; i < nr; i++) {
            head.y = head.y-1;
            if (!head.adjacent(tail)) {
                tail.y--;
                if (head.x != tail.x) tail.x = head.x;
                visited.add(new Pos(tail.x, tail.y));
            }
        }
    }

    private void printBoard() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (i==0 && j == 0) {
                    System.out.print("s ");
                }
                else if (visited.contains(new Pos(j,i))) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    @Data
    @AllArgsConstructor
    static class Pos {
        Integer x;
        Integer y;
        boolean adjacent(Pos that) {
            // overlap
            if (this.x == that.x && this.y == that.y) return true;
            // straight
            if (this.x == that.x && abs(this.y - that.y) == 1) return true;
            if (this.y == that.y && abs(this.x - that.x) == 1) return true;
            // diagonal
            if (abs(this.x - that.x) == 1 && abs(this.y-that.y) == 1) return true;
            return false;
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }
}
