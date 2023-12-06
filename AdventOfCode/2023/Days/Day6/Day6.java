package Days.Day6;

import Days.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day6 extends Day {

    public Day6() {
        super(6);
    }

    @Override
    public Number execPart1() {
        int product = 1;
        List<String> timeSplit = Arrays.stream(this.inputLines.get(0).split(" ")).filter( item -> !item.isEmpty()).toList();
        List<String> distanceSplit = Arrays.stream(this.inputLines.get(1).split(" ")).filter( item -> !item.isEmpty()).toList();

        for(int i = 1; i < timeSplit.size(); i++) {
            product *= getPossibleWaysToWin(Integer.parseInt(timeSplit.get(i)), Integer.parseInt(distanceSplit.get(i)));
        }

        return product;
    }

    private int getPossibleWaysToWin(int time, int distance) {
        int count = 0;
        for(int i = 0; i < time; i++) {
            if(((time - i) * i) > distance) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Number execPart2() {
        return null;
    }
}
