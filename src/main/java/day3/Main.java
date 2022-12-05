package day3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        var data =
                Files.lines(Paths.get(ClassLoader.getSystemResource("day3/input.txt").toURI()))
                        .collect(Collectors.toList());
        var oneCounts = data.stream()
                .map(s -> s.split(""))
                .map(bits -> convertToNrs(bits))
                .reduce(Main::add)
                .get();

        System.out.println(Arrays.toString(oneCounts));
        System.out.println("oneCounts.length = " + data.size());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < oneCounts.length; i++) {
            builder.append(oneCounts[i] > (data.size() / 2) ? "1" : "0");
        }
        var bitresults = builder.toString();

        var voorbeeld = Integer.parseInt("11001", 2);
        System.out.println(voorbeeld + " " + ~voorbeeld);
        System.out.println(5 + " " + ~5);

        System.out.println(bitresults);

        var gammarate = Integer.parseInt(bitresults, 2);
        var epsilonrate = Integer.parseInt(flip(bitresults), 2);
        System.out.printf("gammarate %s, epsilonrate %s, result %s", gammarate, epsilonrate, gammarate * epsilonrate);
    }

    static Integer[] convertToNrs(String[] bitstring) {
        Integer[] result = new Integer[bitstring.length];
        for (int i = 0; i < bitstring.length; i++) {
            result[i] = Integer.parseInt(bitstring[i]);
        }
        return result;
    }

    static Integer[] add(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i] + right[i];
        }
        return result;
    }

    static String flip(String binary) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            result.append((binary.charAt(i) == '1' ? '0' : '1'));
        }
        return result.toString();
    }
}
