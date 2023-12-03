package Days.Day3;

import Days.Day;

import java.util.Objects;

public class Day3 extends Day {

    private static final String[][] matrix = new String[140][140];

    public Day3() {
        super(3);
        convertInputToMatrix();
    }

    @Override
    public String execPart1() {
        int sum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(isNumeric(matrix[i][j])) {
                    String number = getNumber(i, j);
                    if(isAdjacentToChar(i, j, j + (number.length() - 1))) {
                        sum += Integer.parseInt(number);
                    }
                    j += number.length() - 1;
                }
            }
        }
        return sum + "";
    }

    private static String getNumber(int i, int j) {
        StringBuilder number = new StringBuilder();
        while(j < matrix[i].length && isNumeric(matrix[i][j])){
            number.append(matrix[i][j]);
            j++;
        }

        return number.toString();
    }

    private boolean isAdjacentToChar(int x, int jBegin, int jEnd) {

        int lineBegin = Math.max(x - 1, 0);
        int lineEnd = Math.min(x + 1, matrix[x].length - 1);
        int columnBegin = Math.max(jBegin - 1, 0);
        int columnEnd = Math.min(jEnd + 1, matrix[x].length - 1);


        for(int i = lineBegin; i <= lineEnd; i++) {
            for(int j = columnBegin; j <= columnEnd; j++) {
                boolean isOwnNumber = i == x && j >= jBegin && j <= jEnd;

                if(!isNumeric(matrix[i][j]) && !Objects.equals(matrix[i][j], ".") && !isOwnNumber) {
                    return true;
                }
            }
        }

        return false;
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

    @Override
    public String execPart2() {
        return null;
    }

    private void convertInputToMatrix() {

        for(int i = 0; i < inputLines.size(); i++) {
            String[] s = inputLines.get(i).split("");
            System.arraycopy(s, 0, matrix[i], 0, s.length);
        }
    }
}
