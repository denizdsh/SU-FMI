package helper;

import java.util.ArrayList;
import java.util.function.Function;

public class Loops {
    /// @param andDo Function andDo accepts current value from collection, current index, and a boolean indicating whether the loop has finished
    public static <Value> void diveInto(ArrayList<ArrayList<Value>> collection, Function<FuncArgs<Value>, Void> andDo, Value defaultValue) {
        diveInto(collection, andDo, 0, defaultValue);
    }


    /// @param andDo Function andDo accepts current value from collection, current index, and a boolean indicating whether the loop has finished
    public static <Value> void diveInto(ArrayList<ArrayList<Value>> collection, Function<FuncArgs<Value>, Void> andDo, int row, Value prev) {
        if (row == collection.size()) {
            andDo.apply(new FuncArgs<>(prev, row - 1, true));
            return;
        }

        for (Value value : collection.get(row)) {
            andDo.apply(new FuncArgs<>(value, row, false));
            diveInto(collection, andDo, row + 1, value);
        }
    }
}
