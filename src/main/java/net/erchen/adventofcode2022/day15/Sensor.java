package net.erchen.adventofcode2022.day15;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public record Sensor(Point position, Point beacon, int closestBeaconDistance) {

    final static Pattern parsePattern = Pattern.compile("Sensor at x=(-?[0-9]+), y=(-?[0-9-]+): closest beacon is at x=(-?[0-9]+), y=(-?[0-9]+)");

    public static Sensor fromInput(String input) {
        var matcher = parsePattern.matcher(input);
        if (!matcher.find()) throw new IllegalArgumentException();
        var position = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        var beacon = new Point(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

        return new Sensor(position, beacon, position.distanceTo(beacon));
    }

    public Set<Point> surroundingPoints() {
        var distance = closestBeaconDistance + 1;
        return IntStream.range(position().y() - distance, position.y() + distance + 1)
                .boxed()
                .flatMap(y -> {
                    int xDistance = distance - Math.abs(position().y() - y);
                    return Stream.of(new Point(position().x() - xDistance, y), new Point(position().x() + xDistance, y));
                })
                .collect(toSet());
    }


    public boolean ruleOutBeacon(Point point) {
        return this.position.distanceTo(point) <= closestBeaconDistance;
    }
}


