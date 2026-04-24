package problem03;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.BiFunction;

public class Statistics<Key, Value extends Number> {
    //    Seems right to test out HashMap in such an obvious use-case
    private final HashMap<Key, Value> dict = new HashMap<>();

    // A way to add custom addition functionality and a base value (0) for the generic Value
    private final Value baseValue;
    private BiFunction<Value, Value, Value> additionFunction;

    private void setAdditionFunction(BiFunction<Value, Value, Value> additionFunction) {
        this.additionFunction = additionFunction;
    }

    public Statistics(Value baseValue, BiFunction<Value, Value, Value> additionFunction) {
        this.baseValue = baseValue;
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

    public Value getTotal() {
//        `Value` extends `Number`. `Double` is at the top of `Number` subclasses.
        Value total = baseValue;

        for (Entry<Key, Value> kvp : dict.entrySet()) {
            total = additionFunction.apply(total, kvp.getValue());
        }

        return total;
    }

    public HashMap<Key, Double> getProbabilities() {
        HashMap<Key, Double> probabilities = new HashMap<Key, Double>();

        Value total = getTotal();

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
}
