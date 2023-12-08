package Days.Day8;

import Days.Day;

import java.util.HashMap;
import java.util.List;
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
        Node iterator = map.get("AAA");
        int steps = 0;
        while(!iterator.key.equals("ZZZ")) {
            if(path[steps % path.length].equals("L")) {
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
        return null;
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
            return "key: " + key + ", left: " + left + ", right: " + right;
        }
    }
}
