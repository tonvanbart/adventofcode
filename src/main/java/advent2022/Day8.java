package advent2022;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {
    public static void main(String[] args) throws Exception {
        new Day8().run();
    }

    private void run() throws Exception {
        var pfx = "src/main/resources/advent2022/day8";
        var file = "input";
        final var lines = Files.readAllLines(Path.of(pfx, file));
        System.out.println("lines.size() = " + lines.size());

        Integer[][] trees = new Integer[lines.size()][lines.get(0).length()];
        for (int row = 0; row<lines.size(); row++) {
            var line = lines.get(row);
            for (int col = 0; col<line.length(); col++) {
                trees[row][col] = Integer.valueOf(String.valueOf(lines.get(row).charAt(col)));
            }
        }

        print(trees);
        int count = 0;
        for (int row = 0; row<trees.length; row++) {
            for(int col=0; col<trees[row].length; col++) {
                if (isVisible(row, col, trees)) {
                    count++;
                }
            }
        }
        System.out.println("count = " + count);
        System.out.println("score(1, 2, trees) = " + score(1, 2, trees));

        int max = 0;
        for (int row=0; row<trees.length; row++) {
            for (int col=0; col<trees[row].length; col++) {
                int score = score(row, col, trees);
                if (score > max) max = score;
            }
        }
        System.out.println("max = " + max);
    }

    private int score(int row, int col, Integer[][] trees) {
        int up,down,left,right;
        up=down=left=right=0;
        for (int ri=row-1; ri>=0; ri--) {
            up++;
            if (trees[ri][col] >= trees[row][col]) break;
        }
        for (int ri=row+1; ri< trees.length; ri++) {
            down++;
            if (trees[ri][col] >= trees[row][col]) break;
        }
        for (int ci = col-1; ci>=0; ci--) {
            left++;
            if (trees[row][ci] >= trees[row][col]) break;
        }
        for (int ci=col+1; ci<trees[row].length; ci++) {
            right++;
            if (trees[row][ci] >= trees[row][col]) break;
        }

        return up*down*left*right;
    }

    private boolean isVisible(int row, int col, Integer[][] trees) {
        Integer candidate = trees[row][col];
        // edges
        if (row == 0 || row == trees.length-1 || col ==0 || col == trees[row].length-1) {
            return true;
        }

        boolean visible = true;
        var msg = candidate + " at ["+row+","+col+"] is visible ";

        // vertical check
        for (int rwi = 0; rwi < row; rwi++) {
            if (trees[rwi][col] >= candidate) {
                visible = false;
            }
        }
        if (visible) {
            System.out.println(msg + "from the top");
            // we're good
            return true;
        }
        visible = true;
        for (int rwi = row+1; rwi< trees.length; rwi++) {
            if (trees[rwi][col] >= candidate) {
                visible = false;
            }
        }
        if (visible) {
            System.out.println(msg + "from the bottom");
            return true;
        }

        // horizontal check
        visible = true;
        for (int coli = 0; coli < col; coli++) {
            if (trees[row][coli] >= candidate) {
                visible = false;
            }
        }
        if (visible) {
            System.out.println(msg + "from the left");
            return true;
        }

        for (int coli = col+1; coli < trees[row].length; coli++) {
            if (trees[row][coli] >= candidate) {
                return false;
            }
        }
        System.out.println(msg + "from the right");
        return true;
    }

    void print(Integer[][] trees) {
        for (int row = 0; row < trees.length; row++) {
            System.out.println(Arrays.stream(trees[row]).map(String::valueOf).collect(Collectors.joining(",")));
        }
    }
}
