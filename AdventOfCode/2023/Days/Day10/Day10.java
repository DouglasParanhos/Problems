package Days.Day10;

import Days.Day;

import java.util.ArrayList;
import java.util.List;

public class Day10 extends Day
{
    private final String[][] matrix;

    private int xInicial, yInicial;

    public Day10() {
        super(10);
        int x = this.inputLines.size();
        int y = this.inputLines.get(0).split("").length;
        matrix = new String[x][y];

        for(int i = 0; i < x; i++) {
            String[] line = this.inputLines.get(i).split("");
            for(int j = 0; j < y; j++) {
                matrix[i][j] = line[j];
                if(line[j].equals("S")) {
                    xInicial = i;
                    yInicial = j;
                }
            }
        }
    }

    @Override
    public Number execPart1() {
        List<Integer> totalSteps = new ArrayList<>();

        totalSteps.add(getPath(xInicial - 1, yInicial, xInicial, yInicial,1));
        totalSteps.add(getPath(xInicial, yInicial -1, xInicial, yInicial,1));
        // totalSteps.add(getPath(xInicial + 1, yInicial, xInicial, yInicial,1));
        // totalSteps.add(getPath(xInicial, yInicial + 1, xInicial, yInicial,1));


        int max = totalSteps.stream().mapToInt(value -> value).max().getAsInt() / 2;
        return max % 2 == 0 ? max : max + 1;
    }

    private int getPath(int i, int j, int iAnt, int jAnt, int sum) {

        int tempI, tempJ;
        while(i != -1 && j != -1 && !matrix[i][j].equals("S")) {
            // System.out.println(matrix[i][j]);
            tempI = i;
            tempJ = j;
            try {
                if (iAnt == i) {
                    switch (matrix[i][j]) {
                        case "L", "J" -> i--;
                        case "7", "F" -> i++;
                        case "-" -> j = jAnt < j ? j + 1 : j - 1;
                        default -> {
                            i = -1;
                            j = -1;
                        }
                    }
                } else {
                    switch (matrix[i][j]) {
                        case "L", "F" -> j++;
                        case "7", "J" -> j--;
                        case "|" -> i = iAnt < i ? i + 1 : i - 1;
                        default -> {
                            i = -1;
                            j = -1;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("error");
                return -1;
            }

            iAnt = tempI;
            jAnt = tempJ;
            sum++;
        }

        if (i == -1 && j == -1) {
            System.out.println("no path");
            return -1;
        }

        return sum;
    }

    @Override
    public Number execPart2() {
        return null;
    }

}
