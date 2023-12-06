package Days.Day6;

import Days.Day;

import java.util.Arrays;
import java.util.List;

public class Day6 extends Day {

    List<String> timeSplit;
    List<String> distanceSplit;

    public Day6() {
        super(6);
        timeSplit = Arrays.stream(this.inputLines.get(0).split(" ")).filter(item -> !item.isEmpty()).toList();
        distanceSplit = Arrays.stream(this.inputLines.get(1).split(" ")).filter(item -> !item.isEmpty()).toList();
    }

    @Override
    public Number execPart1() {
        long product = 1;

        for(int i = 1; i < timeSplit.size(); i++) {
            product *= getPossibleWaysToWin(Long.parseLong(timeSplit.get(i)), Long.parseLong(distanceSplit.get(i)));
        }

        return product;
    }

    private long getPossibleWaysToWin(long time, long distance) {
        long count = 0;
        for(long i = 0; i < time; i++) {
            if(((time - i) * i) > distance) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Number execPart2() {
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();

        for(int i = 1; i < timeSplit.size(); i++) {
            time.append(timeSplit.get(i));
            distance.append(distanceSplit.get(i));
        }

        return getPossibleWaysToWin(Long.parseLong(time.toString()), Long.parseLong(distance.toString()));
    }
}
