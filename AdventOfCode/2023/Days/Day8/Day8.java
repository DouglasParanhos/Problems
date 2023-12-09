package Days.Day8;

import Days.Day;

import java.util.*;
import java.util.stream.Stream;

public class Day8 extends Day {

    private final String[] path = this.inputLines.get(0).split("");

    private final HashMap<String, Node> map = new HashMap<>();

    public Day8() {
        super(8);
        for(int i=2; i<this.inputLines.size(); i++) {
            String [] parts = this.inputLines.get(i).split("=");
            String key = parts[0].trim();
            List<String> leftRight = Stream.of(parts[1].split(",")).map(item -> item.replace("(", "").replace(")", "")).toList();

            map.put(key, new Node(key, leftRight.get(0).trim(), leftRight.get(1).trim()));
        }
    }

    @Override
    public Number execPart1() {
        Node iterator = map.get("CBA");
        int steps = 0;
        while(!iterator.key.endsWith("Z")) {
            if(path[steps % path.length].trim().equals("L")) {
                iterator = map.get(iterator.left);
            } else {
                iterator = map.get(iterator.right);
            }
            steps++;
        }
        return steps;
    }

    @Override
    public Number execPart2() {
        List<Node> iteratorNodes = new java.util.ArrayList<>(map.values().stream().filter(node -> node.key.endsWith("A")).toList());
        ArrayList<Long> numbers = new ArrayList<>();

        for(Node n: iteratorNodes) {
            int steps = 0;
            Node node = n;
            while(!node.key.endsWith("Z")) {
                if(path[steps % path.length].equals("L")) {
                    node = map.get(node.left);
                } else {
                    node = map.get(node.right);
                }

                steps++;
            }

            numbers.add((long) steps);
        }


        return lcm_of_array(numbers);
    }

    private static class Node {
        private final String key;
        private final String left;
        private final String right;

        private Node(String data, String left, String right) {
            this.key = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{key: " + key + ", left: " + left + ", right: " + right +" }";
        }
    }

    private static long lcm_of_array(ArrayList<Long> arr)
    {
        long lcm = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            long num1 = lcm;
            long num2 = arr.get(i);
            long gcd_val = gcd(num1, num2);
            lcm = (lcm * arr.get(i)) / gcd_val;
        }
        return lcm;
    }

    private static long gcd(long num1, long num2)
    {
        if (num2 == 0)
            return num1;
        return gcd(num2, num1 % num2);
    }
}
