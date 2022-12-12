package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;

public class Day10 {

    int regx = 1; // middle of sprite.
    int cycle = 0;
    int total = 0;
    int position = 0; // current pixel 0 <= pos <= 39

    public static void main(String[] args) throws Exception {
        new Day10().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day10";
//        var file = "example";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        for (var line: lines) {
            if (cycle > 221) break;
            var parts = line.split(" ");
            if (parts[0].equals("noop")) {
                // at start of cycle
                cycle++;
                during(cycle);
                //at end of cycle, instruction finish
            } else if (parts[0].equals("addx")) {
                // at start of cycle
                cycle++;
                during(cycle);
                // at start of second cycle
                cycle++;
                during(cycle);
                // at end of cycle, instruction finishes
//                System.out.println("add " + Integer.parseInt(parts[1]));
                regx = regx + Integer.parseInt(parts[1]);
            }
        }
        System.out.println("total = " + total);
    }

    private void during(int cycle) {
//        System.out.println("during("+cycle+")");
        if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
//            System.out.println("cycle = " + cycle + ",regx = " + regx + ", adding "+ cycle*regx);
            total = total + (cycle*regx);
        }
        if (regx-1 <= position && position <= regx+1) {
            // inside sprite
            System.out.print("#");
        } else {
            System.out.print(" ");
        }
        position++;
        if (position > 39) {
            System.out.println();
            position = 0;
        }
    }
}
