package Days.Day5;

import Days.Day;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 extends Day {

    private final List<Long> seeds = new ArrayList<>(
            Stream.of(
                    this.inputLines.get(0).split(":")[1].split(" "))
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(Long::parseLong).collect(Collectors.toList())
            );

    private final Map<String, List<Conversion>> conversionLists = new HashMap<>();

    private List<String> getPath() {
        return List.of("seed-to-soil",
                "soil-to-fertilizer",
                "fertilizer-to-water",
                "water-to-light",
                "light-to-temperature",
                "temperature-to-humidity",
                "humidity-to-location");
    }

    public Day5() {
        super(5);
        String[] conversionTypes = new String[]{
                "seed-to-soil",
                "soil-to-fertilizer",
                "fertilizer-to-water",
                "water-to-light",
                "light-to-temperature",
                "temperature-to-humidity",
                "humidity-to-location",
        };
        Arrays.stream(conversionTypes).forEachOrdered(item -> conversionLists.put(item, new ArrayList<>()));
        this.inputLines.remove(0);
        populateConversionLists();
    }

    private void populateConversionLists() {
        List<Conversion> iteratee = new ArrayList<>();
        for(String s: this.inputLines) {
            if(s.isEmpty()) {
                continue;
            }

            if(s.contains("-")) {
                iteratee = conversionLists.get(s.split(" ")[0]);
            } else {
                String[] parts = s.split(" ");
                iteratee.add(new Conversion(Long.parseLong(parts[1]), Long.parseLong(parts[0]), Long.parseLong(parts[2])));
            }
        }
    }

    @Override
    public Number execPart1() {
        List<Long> locations = new ArrayList<>();
        for(Long l: seeds) {
            Long translation = l;
            for(String mapName: getPath()) {
                List<Conversion> translationList = conversionLists.get(mapName);
                for(Conversion c: translationList) {
                    if(translation >= c.source && translation < (c.source + c.range)) {
                        translation = c.destination + (translation - c.source);
                        break;
                    }
                }
            }
            locations.add(translation);
        }

        return locations.stream().mapToLong(v -> v).min().getAsLong();
    }

    @Override
    public Number execPart2() {
        return null;
    }

    private record Conversion(long source, long destination, long range) {}
}
