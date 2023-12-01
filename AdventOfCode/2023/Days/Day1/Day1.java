package Days.Day1;

import Days.Day;

import java.util.List;

public final class Day1 extends Day {

    public Day1() {
        super();
        getInput("day1.txt");
    }

    public String execPart2() {

        return "";
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
