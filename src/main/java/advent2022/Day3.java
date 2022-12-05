package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day3 {

    public static void main(String[] args) throws Exception {
        new Day3().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day3";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());
        var totalscore = 0;
        var processed = 0;
        for (var line: lines) {
            var left = line.substring(0, line.length()/2);
            var right = line.substring(line.length()/2);
            var first = left.toCharArray();
            var second = right.toCharArray();
            System.out.printf("left=%s, right=%s  ", left, right);
            var inters = intersect(left, right);
            var score = score(inters);
            System.out.printf("intersect: %s, score=%d%n ",inters,score);
            totalscore += score;
            processed += 1;
        }
        System.out.println( "processed " + processed +  " lines, totalscore = " + totalscore);

        var score2 = 0;
        for (int i = 0; i<lines.size(); i = i+3) {
            var inter1 = intersect(lines.get(i), lines.get(i+1));
            var inter2 = intersect(lines.get(i+1), lines.get(i+2));
            System.out.println("inter1="+inter1+" and inter2="+inter2);
            inter1.retainAll(inter2);
            System.out.println("after retainAll=" + inter1);
            score2 += score(inter1);
        }
        System.out.println("badges=" + score2);
    }

     Set<Character> intersect(String s1, String s2) {
        var result = new HashSet<Character>();
        for (int i=0; i<s1.length(); i++) {
            if (s2.indexOf(s1.charAt(i)) >= 0) {
                result.add(s1.charAt(i));
            }
        }
        return result;
    }

    int score(Set<Character> intersect) {
        final var letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var total = 0;
        for (Character c: intersect) {
            total += 1+letters.indexOf(c);
        }
        return total;
    }
}
