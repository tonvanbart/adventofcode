package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) throws Exception {
        new Day6().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day6";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());
        String input = lines.get(0);
        for (int i = 4; i<input.length()+1; i++) {
            var test = input.substring(i - 4, i);
            if (isUnique(test)) {
                System.out.println("unique at "+i);
                break;
            }
        }
        for (int i = 14; i<input.length()+1; i++) {
            var test = input.substring(i - 14, i);
            if (unique14(test)) {
                System.out.println("unique14 at "+i);
                break;
            }
        }
    }
    
    boolean isUnique(String str) {
//        System.out.println("isUnique("+str+")");
        Set<Character> chars = new HashSet<>();
        for (Character c: str.toCharArray()) {
            chars.add(c);
        }
        return chars.size() == 4;
    }

    boolean unique14(String str) {
        System.out.println("isUnique("+str+")");
        Set<Character> chars = new HashSet<>();
        for (Character c: str.toCharArray()) {
            chars.add(c);
        }
        return chars.size() == 14;
    }
}
