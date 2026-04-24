package problem03;

import helper.FuncArgs;
import helper.Loops;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;

public class Divisibility {
    private ArrayList<Pair<Integer, Integer>> numberRanges; // A data structure to define the ranges of each digit {[1,6], [4, 8], [3, 7], [1, 9], [1, 8]}

    private Divisibility(ArrayList<Pair<Integer, Integer>> ranges) {
        setNumberRanges(ranges);
    }

    private void setNumberRanges(ArrayList<Pair<Integer, Integer>> ranges) {
        for (Pair<Integer, Integer> range : ranges) {
            if (range.getKey() > range.getValue()) {
                throw new IllegalArgumentException(String.format("Invalid range: %s is greater than %s!%n", range.getKey(), range.getValue()));
            }
        }

        this.numberRanges = new ArrayList<>(ranges);
    }

    public static Divisibility ofRanges(ArrayList<Pair<Integer, Integer>> ranges) {
        return new Divisibility(ranges);
    }

    public static Divisibility ofNumber(int number) {
        return new Divisibility(parseNumberToRanges(number));
    }

    public Statistics<Integer, Long> divisibleByTwo() {
        return numbersInRangesDivisibleByTwo(this.numberRanges);
    }

    public Statistics<Long, Long> divisibleByThree() {
        return numbersInRangesDivisibleByThree(this.numberRanges);
    }

    public Statistics<Long, Long> divisibleByThree(ArrayList<Pair<Integer, ArrayList<Integer>>> presetNumbers) {
        // Heavy operation. Supports optimisation by passing specific combinations of numbers in given slots to escape heavy calculations when composing methods to
        return numbersInRangesDivisibleByThree(this.numberRanges, presetNumbers);
    }

    public Statistics<Integer, Long> divisibleByFour() {
        return numbersInRangesDivisibleByFour(this.numberRanges);
    }

    public Statistics<Integer, Long> divisibleByFive() {
        return numbersInRangesDivisibleByFive(this.numberRanges);
    }

    public Statistics<Long, Long> divisibleByTwelve() {
        return numbersInRangesDivisibleByTwelve(this.numberRanges);
    }

//    TODO add more divisibility checking functions

    public static Statistics<Integer, Long> numbersInRangesDivisibleByTwo(ArrayList<Pair<Integer, Integer>> ranges) {
        Pair<Integer, Integer> lastPair = ranges.getLast();

        return divisibilityOfSingleRange(lastPair, 2);
    }

    public static Statistics<Long, Long> numbersInRangesDivisibleByThree(ArrayList<Pair<Integer, Integer>> ranges) {
        return numbersInRangesDivisibleByThree(ranges, new ArrayList<>());
    }

    public static Statistics<Long, Long> numbersInRangesDivisibleByThree(ArrayList<Pair<Integer, Integer>> ranges, ArrayList<Pair<Integer, ArrayList<Integer>>> presetNumbers) {
        Statistics<Long, Long> statistics = initStatistics(ranges);

        //  Fill with empty lists
        ArrayList<ArrayList<Integer>> jaggedList = new ArrayList<>(ranges.size());

        for (int i = 0; i < ranges.size(); i++) {
            jaggedList.add(new ArrayList<>());
        }

        for (Pair<Integer, ArrayList<Integer>> preset : presetNumbers) {
            jaggedList.set(
                    ranges.size() - preset.getKey() - 1,
                    new ArrayList<>(preset.getValue())
            );
        }

        for (int i = 0; i < ranges.size(); i++) {
            //  Omit overwriting preset numbers
            if (!jaggedList.get(i).isEmpty()) {
                continue;
            }
            //  Fill the rest of the jagged array with the numbers from the ranges
            for (int current = ranges.get(ranges.size() - i - 1).getKey(); current <= ranges.get(ranges.size() - i - 1).getValue(); current++) {
                jaggedList.get(i).add(current);
            }
        }

        // A way to keep track of state across recursion iterations while keeping Loops.diveInto super simple and abstract
        // Spent 1+ hours on method `numbersInRangesDivisibleByThree`...
        // My girlfriend is mad at me.
        // It was fun but not worth it.
        AtomicIntegerArray sumArray = new AtomicIntegerArray(jaggedList.size()); // Normal variable are not accepted in Lambdas in Java
        AtomicLongArray numberArray = new AtomicLongArray(jaggedList.size());

        Loops.diveInto(jaggedList, (FuncArgs<Integer> args) -> {
            int i = args.idx();
            int v = args.value();

            if (!args.isFinished()) {
                int oldSum = i > 0 ? sumArray.get(i - 1) : 0;
                long oldNumber = i > 0 ? numberArray.get(i - 1) : 0;

                sumArray.set(i, oldSum + v);
                numberArray.set(i, ((Math.powExact(10L, i)) * v) + oldNumber);

                return null;
            }

            statistics.add(numberArray.get(i), sumArray.get(i) % 3 == 0 ? 1L : 0L);

            return null;
        }, 0);


        return statistics;
    }

    public static Statistics<Integer, Long> numbersInRangesDivisibleByFour(ArrayList<Pair<Integer, Integer>> ranges) {
        Pair<Integer, Integer> lastPair = ranges.getLast();

        if (ranges.size() < 2) {
            return divisibilityOfSingleRange(lastPair, 4);
        }

        Pair<Integer, Integer> beforeLastPair = ranges.get(ranges.size() - 2);

        Statistics<Integer, Long> statistics = initStatistics(ranges);

        int tempNum;
        for (int currentBLP = beforeLastPair.getKey(); currentBLP <= beforeLastPair.getValue(); ++currentBLP) {
            for (int currentLP = lastPair.getKey(); currentLP <= lastPair.getValue(); ++currentLP) {
                tempNum = (currentBLP * 10 + currentLP);
                if (tempNum % 4 == 0) {
                    statistics.add(tempNum, 1L);
                }
            }
        }

        return statistics;
    }

    public static Statistics<Integer, Long> numbersInRangesDivisibleByFive(ArrayList<Pair<Integer, Integer>> ranges) {
        Pair<Integer, Integer> lastPair = ranges.getLast();

        return divisibilityOfSingleRange(lastPair, 5);
    }

    public static Statistics<Long, Long> numbersInRangesDivisibleByTwelve(ArrayList<Pair<Integer, Integer>> ranges) {
        // Divisibility of 12 is equivalent to the conjunction of divisibility of 3 and 4.
        if (ranges.size() < 2) {
            throw new IllegalArgumentException("Divisibility by 12 is not valid for numbers with less than 2 digits!");
        }

        Statistics<Integer, Long> s4 = numbersInRangesDivisibleByFour(ranges);

        // Specifics of the results of division by four... (this simple task got too complicated already and will not be further optimised)
        ArrayList<Pair<Integer, ArrayList<Integer>>> divisibleByFourPreset = new ArrayList<>(List.of(
                new Pair<>(ranges.size() - 2, new ArrayList<>(List.of(0))),
                new Pair<>(ranges.size() - 1, new ArrayList<>(s4.getDictView().keySet()))
        ));

        Statistics<Long, Long> s12 = numbersInRangesDivisibleByThree(ranges, divisibleByFourPreset);

        return s12;
    }

    public static Statistics<Integer, Long> divisibilityOfSingleRange(Pair<Integer, Integer> range, int divisor) {
        Statistics<Integer, Long> statistics = initStatistics(new ArrayList<>(List.of(range)));

        for (int current = range.getKey(); current < range.getValue(); current++) {
            if (current % divisor != 0) {
                continue;
            }

            statistics.add(current, 1L);
        }

        return statistics;
    }

    private static long getTotalCombinations(ArrayList<Pair<Integer, Integer>> ranges) {
        long product = 1;
        for (Pair<Integer, Integer> range : ranges) {
            product *= range.getValue() - range.getKey() + 1;
        }

        return product;
    }

    private static <Key> Statistics<Key, Long> initStatistics(ArrayList<Pair<Integer, Integer>> ranges) {
        Statistics<Key, Long> statistics = Statistics.forLongs();
        statistics.setTotal(getTotalCombinations(ranges));
        return statistics;
    }

    private static ArrayList<Pair<Integer, Integer>> parseNumberToRanges(int number) {
        ArrayList<Pair<Integer, Integer>> ranges = new ArrayList<>();

        int tempDigit;
        while (number > 0) {
            tempDigit = number % 10;
            ranges.addLast(new Pair<>(tempDigit, tempDigit));

            number /= 10;
        }

        return ranges;
    }
}