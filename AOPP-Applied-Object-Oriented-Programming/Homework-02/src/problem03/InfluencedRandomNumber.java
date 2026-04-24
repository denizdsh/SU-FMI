package problem03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class InfluencedRandomNumber { // Working in a `Probabilty in [0.0, 1.0] decimal format, since no restrictions were mentioned
    // (if restrictions were known, optimisations could be made by dodging floating point calculations by using [0, 100] instead, for example).
//    Solution is made to work with any given ascending range of whole numbers. E.g. [1,3], [-4, 4], [0].
    private final static int LOWER_BOUND = 1;
    private final static int HIGHER_BOUND = 3;
    //    Arrays were most recently studied, so I assume that the point of this task is to practice using them (and not more complex structures).
    private final static double[] probabilities = new double[HIGHER_BOUND - LOWER_BOUND + 1];
    private final static double defaultProbability;

    //    Simulate caching via updating and flagging manually on state change
    private static double[] probabilitySpectrumCache;
    private static boolean isSpectrumCacheValid = false;

    static {
        // Configurative setup, since the problem documentation specifically requires a `static` method.
        setProbabilityOfNumber(1, 0.2);
        setProbabilityOfNumber(2, 0.3);
        setProbabilityOfNumber(3, 0.5);

        double predefinedProbabilitySum = 0;
        int defaultProbabilitesLength = 0;

        // Calculate the probability for a non-defined number (!!not mentioned in the problem documentation)
        for (double p : probabilities) {
            if (p > 0) {
                predefinedProbabilitySum += p;
            } else {
                ++defaultProbabilitesLength;
            }
        }

        defaultProbability = (1 - predefinedProbabilitySum) / defaultProbabilitesLength;
    }

    //    Main is inside of this file per task documentation
    static void main(String[] args) {
        System.out.printf("Random number: %d%n", drawRandomNumber());

        System.out.println("\nTest with 10_000 iterations:");
        test(10_000);
        System.out.println("\nTest with 60_000 iterations:");
        test(60_000);
    }

    public static void test(int iterationsCount) {
        Statistics<Integer, Integer> statistics = Statistics.forIntegers();

        for (int i = 0; i < iterationsCount; i++) {
            statistics.add(drawRandomNumber(), 1);
        }

        statistics.printProbabilities();
    }

    public static int drawRandomNumber() {
        Random rnd = new Random();
//        Random [0.0, 1.0] - 0.0 to 1.0 inclusive
        double value = rnd.nextDouble(0.0, Math.nextAfter(1.0, Double.MAX_VALUE));

        double[] spectrum = getProbabilitySpectrum();

        int idx = spectrum.length - 1;

        for (int i = 1; i < spectrum.length; i++) {
            if (value < spectrum[i]) {
                idx = i - 1;
                break;
            }
        }

        return getNumberFromProbabilityIndex(idx);
    }

    private static int getProbabilityIndexOfNumber(int number) {
        int idx = number - LOWER_BOUND;

        if (idx < 0 || idx >= probabilities.length) {
            throw new ArrayIndexOutOfBoundsException(String.format("The number %d is out of bounds [%d, %d].", number, LOWER_BOUND, HIGHER_BOUND));
        }

        return idx;
    }

    private static int getNumberFromProbabilityIndex(int idx) {
        return idx + LOWER_BOUND;
    }

    private static double getProbabilityOfNumber(int number) {
        return probabilities[getProbabilityIndexOfNumber(number)];
    }

    private static void setProbabilityOfNumber(int number, double newProbability) {
        int idx = getProbabilityIndexOfNumber(number);
        double currentProbability = probabilities[idx];

        if (Arrays.stream(probabilities).sum() - currentProbability + newProbability > 1) {
            throw new IllegalArgumentException(String.format("Invalid probability %f of number %d. The sum of all probabilities must be 1.", newProbability, number));
        }

        probabilities[idx] = newProbability;

        isSpectrumCacheValid = false;
    }

    private static void updateProbabilitySpectrum() {
//        A "spectrum" of the probability of each number depicted as a range of values such as [0.0, 0.154) and [0.154, 0.5)
//        The beginning of the range of each number sits at its corresponding index in the `spectrum` array
//        P(1) = 0.2 , P(2) = 0.3 , P(3) = 0.5 corresponds to [0.0, 0.2, 0.5] meaning '1' starts at 0.0 and ends at 0.2. '3' starts at 0.5 and ends at 1.0
        double totalProbability = 0;

        double[] spectrum = new double[probabilities.length];

        for (int i = 0; i < probabilities.length; i++) {
            spectrum[i] = totalProbability;
            totalProbability += probabilities[i] > 0 ? probabilities[i] : defaultProbability;
        }

        probabilitySpectrumCache = spectrum;

        isSpectrumCacheValid = true;
    }

    private static double[] getProbabilitySpectrum() {
        if (isSpectrumCacheValid) {
            return probabilitySpectrumCache;
        }

        updateProbabilitySpectrum();
        return probabilitySpectrumCache;
    }
}
