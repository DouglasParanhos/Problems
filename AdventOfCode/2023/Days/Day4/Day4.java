package Days.Day4;

import Days.Day;

import java.util.*;

public class Day4 extends Day {

    public Day4(){
        super(4);
    }
    @Override
    public Integer execPart1() {
        int sum = 0;
        for(String s: this.inputLines) {
            sum += getSum(s.split(":")[1].split("\\|"));
        }
        return sum;
    }

    private int getSum(String[] cards) {
        List<Integer> winningNumbers = new ArrayList<>(List.of(cards[0].split(" ")))
                .stream().filter(number -> !number.isEmpty())
                .map(Integer::parseInt).toList();
        List<Integer> numbersToCheck = new ArrayList<>(List.of(cards[1].split(" ")))
                .stream().filter(number -> !number.isEmpty())
                .map(Integer::parseInt).toList();

        int winningNumbersFound = winningNumbers.stream().filter(numbersToCheck::contains).toList().size();

        return winningNumbersFound > 0 ? (int) Math.pow(2, winningNumbersFound - 1) : 0;
    }

    @Override
    public Long execPart2() {
        Map<Integer, Long> copies = new HashMap<>();

        for(int i = 0; i < inputLines.size(); i++) {
            copies.put(i, 1L);
        }

        for(int i = 0; i < inputLines.size(); i++) {
            int totalWinningNumbers = getTotalWinningNumbers(inputLines.get(i));

            if(i == inputLines.size()) {
                break;
            }

            for(int j = i+1; j <= i + totalWinningNumbers; j++) {
                if(j >= inputLines.size()) {
                    break;
                }

                copies.put(j, copies.get(j) + copies.get(i));
            }
        }

        return copies.values().stream().reduce(Long::sum).orElse(0L);
    }

    private int getTotalWinningNumbers(String line) {
        String[] parts = line.split(":")[1].split("\\|");

        List<Integer> winningNumbers = new ArrayList<>(List.of(parts[0].split(" ")))
                .stream().filter(number -> !number.isEmpty())
                .map(Integer::parseInt).toList();
        List<Integer> numbersToCheck = new ArrayList<>(List.of(parts[1].split(" ")))
                .stream().filter(number -> !number.isEmpty())
                .map(Integer::parseInt).toList();

        return winningNumbers.stream().filter(numbersToCheck::contains).toList().size();
    }
}
