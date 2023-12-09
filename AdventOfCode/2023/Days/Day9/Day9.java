package Days.Day9;

import Days.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9 extends Day {

    public Day9() {
        super(9);
    }

    @Override
    public Number execPart1() {
        long sum = 0;
        for(String s: this.inputLines) {
            sum += getNextNumber(s);
        }
        return sum;
    }

    private long getNextNumber(String line) {
        return getNextNumberFromList(Stream.of(line.split(" ")).map(Long::parseLong).collect(Collectors.toList()));
    }

    private Long getNextNumberFromList(List<Long> list) {
        if(list.stream().distinct().count() <= 1) {
            return list.get(0);
        }

        List<Long> newList = new ArrayList<>();
        for(int i=1; i < list.size(); i++) {
            newList.add(list.get(i) - list.get(i-1));
        }
        return list.get(list.size() - 1) + getNextNumberFromList(newList);
    }

    @Override
    public Number execPart2() {
        long sum = 0;
        for(String s: this.inputLines) {
            sum += getPreviousNumber(s);
        }
        return sum;
    }

    private long getPreviousNumber(String line) {
        return getPreviousNumberFromList(Stream.of(line.split(" ")).map(Long::parseLong).collect(Collectors.toList()));
    }

    private Long getPreviousNumberFromList(List<Long> list) {
        if(list.stream().distinct().count() <= 1) {
            return list.get(0);
        }

        List<Long> newList = new ArrayList<>();
        for(int i=1; i < list.size(); i++) {
            newList.add(list.get(i) - list.get(i-1));
        }
        return list.get(0) - getPreviousNumberFromList(newList);
    }
}
