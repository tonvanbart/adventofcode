package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws Exception {
        new Day5().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day5";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());

        var ixSeparator = lines.indexOf("");
        System.out.println("ixSeparator = " + ixSeparator);

        // parse boxes
        List<String> boxlines = new ArrayList<>();
        for (var i=0; i<ixSeparator; i++) {
            boxlines.add(lines.get(i));
        }
        Collections.reverse(boxlines);
//        for (var bline: boxlines) {
//            System.out.println(bline);
//        }
        var stacks = parse(boxlines);
        var stacks2 = parse(boxlines);

        // parse  and execute instructions
        for (int i = ixSeparator+1; i< lines.size(); i++) {
//            System.out.println("MATCHING:"+lines.get(i));
            var pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            var mt = pattern.matcher(lines.get(i));
            mt.matches();
//            System.out.println("mt.pattern() = " + mt.pattern());
//            System.out.println("mt.matches() = " + mt.matches());
            System.out.println("want to move "+mt.group(1)+" from "+ mt.group(2)+" to "+mt.group(3));
            var number = Integer.parseInt(mt.group(1));
            var from = Integer.parseInt(mt.group(2));
            var to = Integer.parseInt(mt.group(3));

            doMove(number, from, to, stacks);
            doMoveMulti(number, from, to, stacks2);
        }

        // peek at the top of each stack
        for (int i=0; i<stacks.length; i++) {
            System.out.print(stacks[i].peek());
        }
        System.out.println();
        for (int i=0; i<stacks2.length; i++) {
            System.out.print(stacks2[i].peek());
        }
        System.out.println();
    }

    private void doMove(int number, int from, int to, Stack<Character>[] stacks) {
        // zero based index!!
        var realfrom = from -1;
        var realto = to -1;
        for (int i=0; i<number; i++) {
            var crate = stacks[realfrom].pop();
            System.out.println("move "+crate+" from "+from+" to "+to);
            stacks[realto].push(crate);
        }
    }

    private void doMoveMulti(int number, int from, int to, Stack<Character>[] stacks) {
        // zero based index!!
        var realfrom = from -1;
        var realto = to -1;
        Stack<Character> between = new Stack<>();
        for (int i=0; i<number; i++) {
            between.push(stacks[realfrom].pop());
        }
        for (int i=0; i<number; i++) {
            stacks[realto].push(between.pop());
        }
    }

    /** convert boxlines to array of stacks (stacks contain strings) */
    Stack<Character>[] parse(List<String> boxlines) {
        // nr of stacks
        var stacklabels = boxlines.get(0).split("\\s+");
        var countStr = stacklabels[stacklabels.length - 1];
        var count = Integer.parseInt(countStr);
        System.out.println(count + " stacks");

        // init result
        Stack<Character>[] stacks = new Stack[count];
        for (int i=0; i<count; i++) {
            stacks[i] = new Stack<>();
        }

        // parse remaining lines, letters are in position 1, 5, 9, 13, ....
        for (int i=1; i<boxlines.size(); i++) {
            var stackix = 0;
            for (int pos = 1; pos<boxlines.get(i).length(); pos+=4) {
                if (boxlines.get(i).charAt(pos) != ' ') {
                    stacks[stackix].push(boxlines.get(i).charAt(pos));
                }
                stackix++;
            }
        }

        for (int i = 0; i<stacks.length; i++) {
            System.out.println(stacks[i]);
        }

        return stacks;
    }
}
