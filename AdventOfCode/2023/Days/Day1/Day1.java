package Days.Day1;

import Days.Day;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Day1 extends Day {

    public Day1() {
        super();
        getInput("day1.txt");
    }

    public String execPart2() {
        int sum = 0;
        for(String line: this.inputLines) {
            sum += getNumberFromLineImproved(line);
        }

        return String.valueOf(sum);
    }

    private int getNumberFromLineImproved(String line) {
        if(line.equals("8sevenonefmfqhtx8lk")) {
            System.out.println("erro");
        }
        List<String> chars = List.of(line.split(""));
        String firstInt = "", lastInt = "";
        for (int i = 0; i < chars.size(); i++) {
            String aChar = chars.get(i);
            String number = "";
            if (isNumeric(aChar)) {
                number = aChar;
            } else {
                number = switch(aChar) {
                    case "e", "f", "n", "o", "s", "t" -> getNumberFromWord(chars, i);
                    default -> "";
                };

                if(number.isEmpty()) {
                    continue;
                }

                number = convertWord2Digit(number);
            }

            if (firstInt.isEmpty()) {
                firstInt = number;
                lastInt = number;
            } else {
                lastInt = number;
            }
        }
        System.out.println(line + ": " + firstInt + lastInt);
        return Integer.parseInt(firstInt + lastInt);
    }

    private String convertWord2Digit(String word) {
        Map<String, String> map = Stream.of(new String [][] {
                {"one", "1"},
                {"two", "2"},
                {"three", "3"},
                {"four", "4"},
                {"five", "5"},
                {"six", "6"},
                {"seven", "7"},
                {"eight", "8"},
                {"nine", "9"},
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        return map.get(word);
    }

    private static String getNumberFromWord(List<String> chars, int index) {
        String numberString = "";
        switch(chars.get(index)) {
            case "o" -> numberString = index + 2 <= chars.size() && chars.get(index + 1).equals("n") && chars.get(index + 2).equals("e") ? "one" : "";
            case "t" -> {
                if(index + 2 <= chars.size() && chars.get(index + 1).equals("w") && chars.get(index + 2).equals("o")) {
                    numberString = "two";
                } else if (index + 4 <= chars.size() && chars.get(index + 1).equals("h") && chars.get(index + 2).equals("r")
                        && chars.get(index + 3).equals("e") && chars.get(index + 4).equals("e")){
                    numberString = "three";
                }
            }
            case "f" -> {
                if(index + 3 <= chars.size()) {
                    if (chars.get(index + 1).equals("o") && chars.get(index + 2).equals("u") && chars.get(index + 3).equals("r")) {
                        numberString = "four";
                    } else if (chars.get(index + 1).equals("i") && chars.get(index + 2).equals("v") && chars.get(index + 3).equals("e")) {
                        numberString = "five";
                    }
                }
            }
            case "s" -> {
                if(index + 2 <= chars.size() && chars.get(index + 1).equals("i") && chars.get(index + 2).equals("x")) {
                    numberString = "six";
                } else if (index + 4 <= chars.size() && chars.get(index + 1).equals("e") && chars.get(index + 2).equals("v")
                        && chars.get(index + 3).equals("e") && chars.get(index + 4).equals("n")){
                    numberString = "seven";
                }
            }
            case "e" -> numberString = index + 4 <= chars.size() && chars.get(index + 1).equals("i") && chars.get(index + 2).equals("g")
                    && chars.get(index + 3).equals("h") && chars.get(index + 4).equals("t")? "eight" : "";
            case "n" -> numberString = index + 3 <= chars.size() && chars.get(index + 1).equals("i") && chars.get(index + 2).equals("n")
                    && chars.get(index + 3).equals("e") ? "nine" : "";
        }

        return numberString;
    }

    public String execPart1(){
        int sum = 0;
        for(String line: this.inputLines) {
            sum += getNumberFromLine(line);
        }

        return String.valueOf(sum);
    }

    private int getNumberFromLine(String line) {
        List<String> chars = List.of(line.split(""));
        String firstInt = "", lastInt = "";
        for (String aChar : chars) {
            if (isNumeric(aChar)) {
                if (firstInt.isEmpty()) {
                    firstInt = aChar;
                    lastInt = aChar;
                } else {
                    lastInt = aChar;
                }
            }
        }
        return Integer.parseInt(firstInt + lastInt);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
