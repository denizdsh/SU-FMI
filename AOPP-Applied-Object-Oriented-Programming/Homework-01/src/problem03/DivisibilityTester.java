package problem03;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DivisibilityTester {
    static void main(String[] args) {
        Divisibility div = Divisibility.ofRanges(new ArrayList<>(List.of(
                new Pair<>(1, 6),
                new Pair<>(4, 8),
                new Pair<>(3, 7),
                new Pair<>(1, 9),
                new Pair<>(1, 8)
        )));

        Statistics<Long, Long> s12 = div.divisibleByTwelve();

        System.out.println("Numbers in range divisible by 12");
        s12.printIf(kvp -> kvp.getValue() > 0, false);
    }
}
