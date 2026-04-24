package problem03;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;

//    A utility class I wrote for Homework-02. Reusable after slight changes.
public class Statistics<Key, Value extends Number> {
    // Seems right to test out HashMap in such an obvious use-case
    private final HashMap<Key, Value> dict = new HashMap<>();

    // A way to add custom addition functionality and a base value for total (0) for the generic Value
    private Value total;
    private BiFunction<Value, Value, Value> additionFunction;

    public Map<Key, Value> getDictView() {
        return Collections.unmodifiableMap(this.dict);
    }

    private void setAdditionFunction(BiFunction<Value, Value, Value> additionFunction) {
        this.additionFunction = additionFunction;
    }

    public Value getTotal() {
        return this.total;
    }

    public void setTotal(Value total) {
        this.total = total;
    }

    public Statistics(Value total, BiFunction<Value, Value, Value> additionFunction) {
        this.total = total;
        setAdditionFunction(additionFunction);
    }

    public static <Key> Statistics<Key, Long> forLongs() {
        return new Statistics<Key, Long>(0L, Long::sum);
    }

    public static <Key> Statistics<Key, Integer> forIntegers() {
        return new Statistics<Key, Integer>(0, Integer::sum);
    }

    public static <Key> Statistics<Key, Double> forDoubles() {
        return new Statistics<Key, Double>(0.0, Double::sum);
    }

    public void add(Key key, Value value) {
        dict.put(key, dict.get(key) == null ? value : additionFunction.apply(dict.get(key), value));
    }

    public void printIf(Function<Entry<Key, Value>, Boolean> pred, Boolean printEach) {
        int passingCounter = 0;

        for (Entry<Key, Value> kvp : dict.entrySet()) {
            if (!pred.apply(kvp)) {
                continue;
            }
            if (printEach) {
                System.out.printf("%s: %s;%n", kvp.getKey(), kvp.getValue());
            }
            ++passingCounter;
        }

        System.out.printf("Total entries: %d%n", total.intValue());
        System.out.printf("Passed: %d ~ %.2f%%%n", passingCounter, (passingCounter / total.doubleValue()) * 100);
    }

    /*
     public HashMap<Key, Double> getProbabilities() {
        HashMap<Key, Double> probabilities = new HashMap<Key, Double>();

        for (Entry<Key, Value> kvp : dict.entrySet()) {
            probabilities.put(kvp.getKey(), kvp.getValue().doubleValue() / total.doubleValue());
        }

        return probabilities;
    }

    public void printProbabilities() {
        HashMap<Key, Double> probabilities = getProbabilities();

        for (Entry<Key, Double> kvp : probabilities.entrySet()) {

            System.out.printf("%s: %s;%n",
                    kvp.getKey(),
                    NumberFormat.getPercentInstance().format(kvp.getValue())
            );
        }
    }
    * */
}
