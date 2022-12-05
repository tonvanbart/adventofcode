package advent2021.day2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PartOne {

    public static void main(String[] args) throws Exception {
        var instructions =
                Files.lines(Paths.get(ClassLoader.getSystemResource("day2/input.txt").toURI()))
                        .collect(Collectors.toList());
        Submarine submarine = new Submarine();
        instructions.forEach(submarine::processInstruction);
        submarine.report();

        SubmarineTwo submarineTwo = new SubmarineTwo();
        instructions.forEach(submarineTwo::processInstruction);
        submarineTwo.report();;
    }

    /** Day Two, part one */
    static class Submarine {
        Integer depth = 0;
        Integer position = 0;

        void processInstruction(String instruction) {
            String[] parts = instruction.split(" ");
            Command command = Command.valueOf(parts[0].toUpperCase());
            Integer amount = Integer.parseInt(parts[1]);
            switch (command) {
                case UP: depth = depth - amount; break;
                case DOWN:  depth = depth + amount; break;
                case FORWARD: position = position + amount; break;
                default: // ignore
            }
        }

        void report() {
            System.out.printf("One: depth=%s, position=%s, product=%s\n", depth, position, depth*position);
        }
    }

    /** Day two, part two */
    static class SubmarineTwo {
        Integer aim = 0;
        Integer position = 0;
        Integer depth = 0;

        void processInstruction(String instruction) {
            String[] parts = instruction.split(" ");
            Command command = Command.valueOf(parts[0].toUpperCase());
            Integer amount = Integer.parseInt(parts[1]);
            switch (command) {
                case UP: aim = aim - amount; break;
                case DOWN:  aim = aim + amount; break;
                case FORWARD:
                    position = position + amount;
                    depth = depth + (aim * amount);
                    break;
                default: // ignore
            }
        }

        void report() {
            System.out.printf("Two: depth=%s, position=%s, product=%s\n", depth, position, depth*position);
        }
    }

    static enum Command {
        UP, DOWN, FORWARD;
    }
}
