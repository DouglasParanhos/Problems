package Days.Day2;

import Days.Day;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 extends Day {

    public Day2() {
        super(2);
    }
    @Override
    public String execPart1() {
        int sum = 0;

        for(String game: this.inputLines) {
            String[] parts = game.split(":");
            int gameNumber = Integer.parseInt(parts[0].split("Game ")[1]);

            if (gameIsPossible(parts[1].split(";"))) {
                sum += gameNumber;
            }
        }

        return sum + "";
    }

    private boolean gameIsPossible(String[] sets) {
        for (String set : sets) {
            if (!conditionsSatisfied(set)) {
                return false;
            }
        }

        return true;
    }

    private boolean conditionsSatisfied(String set) {
        String[] parts = set.split(",");
        Map<String, Integer> map = Stream.of(new String [][] {
                {"red", "12"},
                {"green", "13"},
                {"blue", "14"},
        }).collect(Collectors.toMap(data -> data[0], data -> Integer.parseInt(data[1])));

        for(String s: parts) {
            String[] singleColor = s.trim().split(" ");
            if(map.get(singleColor[1]) < Integer.parseInt(singleColor[0])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String execPart2() {
        int sum = 0;

        for(String game: this.inputLines) {
            String[] parts = game.split(":");
            System.out.println("Game: " + parts[0]);
            sum += getMinimumValue(parts[1].split(";"));
        }

        return sum + "";
    }

    private int getMinimumValue(String[] sets) {
        Map<String, Integer> minimumColorsConfiguration = Stream.of(new String [][] {
                {"red", "0"},
                {"green", "0"},
                {"blue", "0"},
        }).collect(Collectors.toMap(data -> data[0], data -> Integer.parseInt(data[1])));

        for(String s: sets) {
            Map<String, Integer> colorsConfiguration = getColorsConfiguration(s);

            for(Map.Entry<String, Integer> entry: colorsConfiguration.entrySet()) {
                int entryMinimumValue = minimumColorsConfiguration.get(entry.getKey());

                if(entry.getValue() > entryMinimumValue) {
                    minimumColorsConfiguration.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return minimumColorsConfiguration.values().stream().reduce(1, (subtotal, element) -> subtotal * element);
    }

    private Map<String, Integer> getColorsConfiguration(String set) {
        Map<String, Integer> map = new HashMap<>();
        String[] parts = set.split(",");

        for(String s: parts) {
            String[] singleColor = s.trim().split(" ");
            map.put(singleColor[1].trim(), Integer.parseInt(singleColor[0].trim()));
        }

        return map;
    }
}
