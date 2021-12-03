package advent.day3;

import java.io.InputStream;
import java.util.*;
import java.util.function.BiPredicate;

public class Part2 {
    /*
       Advent of code day 3 - part 2
       https://adventofcode.com/2021/day/3
    */
    public static int getDiagnosis(){

        InputStream input = advent.day3.Part2.class.getResourceAsStream("input.txt");
        List<String> signalPulses = new ArrayList<>();
        assert input != null;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                signalPulses.add(scanner.nextLine());
            }
        }
        return calculateValue(signalPulses, (one, zero) -> one >= zero) * calculateValue(signalPulses, (one, zero) -> one < zero);
    }

    private static int calculateValue(List<String> pulses, BiPredicate<Integer, Integer> predicateTakeOneBit) {
        int length = pulses.get(0).length();
        char[] result = new char[length];

        LinkedList<String> linkedList = new LinkedList<>(pulses);
        List<String> zeros = new ArrayList<>();
        List<String> ones = new ArrayList<>();


        for (int i = 0; i < length; i++) {
            while (!linkedList.isEmpty()) {
                String pulse = linkedList.pop();
                char[] binaryChars = pulse.toCharArray();
                if (binaryChars[i] == '0') {
                    zeros.add(pulse);
                } else {
                    ones.add(pulse);
                }
            }
            if (predicateTakeOneBit.test(ones.size(), zeros.size())) {
                result[i] = '1';
                linkedList.addAll(ones);
            } else {
                result[i] = '0';
                linkedList.addAll(zeros);
            }

            if (linkedList.size() == 1) {
                result = linkedList.get(0).toCharArray();
                break;
            }
            ones = new ArrayList<>();
            zeros = new ArrayList<>();
        }
        return Integer.valueOf(String.valueOf(result), 2);
    }
}
