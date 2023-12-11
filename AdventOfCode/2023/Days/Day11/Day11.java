package Days.Day11;

import Days.Day;

import java.math.BigInteger;
import java.util.*;
import java.util.List;

public class Day11 extends Day {

    private final String[][] matrix;
    Set<Integer> duplicatedRows = new HashSet<>();
    Set<Integer> duplicatedColumns = new HashSet<>();

    public Day11() {
        super(11);
        int x = this.inputLines.size();
        int y = this.inputLines.get(0).split("").length;
        matrix = new String[x][y];

        List<List<String>> temp = new ArrayList<>();
        this.inputLines.forEach(line -> temp.add(Arrays.stream(line.split("")).toList()));

        for(int i = 0; i < temp.size(); i++) {
            if(temp.get(i).stream().allMatch(point -> point.equals("."))) {
                duplicatedRows.add(i);
            }
            for(int j = 0; j < temp.get(0).size(); j++) {
                matrix[i][j] = temp.get(i).get(j);
            }
        }


        for(int j = 0; j < temp.get(0).size(); j++) {
            boolean columnAllSame = true;
            for(int i = 0; i < temp.get(0).size(); i++) {
                if (!temp.get(i).get(j).equals(".")) {
                    columnAllSame = false;
                    break;
                }
            }
            if(columnAllSame) {
                duplicatedColumns.add(j);
            }
        }
    }

    @Override
    public Number execPart1() {
        List<Point> galaxies = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j].equals("#")) {
                    galaxies.add(new Point(i,j));
                }
            }
        }

        long sum = 0;
        for(int i = 0; i < galaxies.size() - 1; i++) {
            sum += calculateAllDistancesGalaxy(galaxies.get(i), galaxies.subList(i + 1, galaxies.size()), 1);
        }

        return sum;
    }

    private long calculateAllDistancesGalaxy(Point p, List<Point> galaxies, int valueToMultiply) {
        long distance = 0;
        for(Point other: galaxies) {
            distance += Math.abs(other.x - p.x) + Math.abs(other.y - p.y);
            distance += valueToMultiply * duplicatedRows.stream().filter(row -> row < Math.max(other.x, p.x) && row > Math.min(other.x, p.x)).count();
            distance += valueToMultiply * duplicatedColumns.stream().filter(row -> row < Math.max(other.y, p.y) && row > Math.min(other.y, p.y)).count();
        }

        return distance;
    }

    @Override
    public Number execPart2() {
        List<Point> galaxies = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j].equals("#")) {
                    galaxies.add(new Point(i,j));
                }
            }
        }

        BigInteger sum = BigInteger.ZERO;
        for(int i = 0; i < galaxies.size() - 1; i++) {
            sum = sum.add(BigInteger.valueOf(calculateAllDistancesGalaxy(galaxies.get(i), galaxies.subList(i + 1, galaxies.size()), 999999)));
        }

        return sum;
    }

    private static class Point {
        int x, y;

        // List<Integer> distances = new ArrayList<>();
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
