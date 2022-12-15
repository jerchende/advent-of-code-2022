package net.erchen.adventofcode2022.day15;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;
import java.util.stream.IntStream;

public record BeaconExclusionZone(List<Sensor> sensors) {

    public static BeaconExclusionZone fromInput(String input) {
        return new BeaconExclusionZone(SeparatorParser.parseInput(input, "\n", Sensor::fromInput));
    }

    public int countPositionWithoutBeacons(int y) {
        int minX = sensors().stream().mapToInt(sensor -> sensor.position().x() - sensor.closestBeaconDistance()).min().orElse(0);
        int maxX = sensors().stream().mapToInt(sensor -> sensor.position().x() + sensor.closestBeaconDistance()).max().orElse(0);

        return (int) (IntStream.range(minX, maxX + 1).filter(x -> sensors.stream().anyMatch(sensor -> sensor.ruleOutBeacon(new Point(x, y)))).count() - sensors.stream().map(Sensor::beacon).filter(beacon -> beacon.y() == y).distinct().count());
    }

    public long findDistressBeacon(int max) {
        var distressBeacon = sensors.stream()
                .flatMap(sensor -> sensor.surroundingPoints().stream())
                .filter(point -> point.y() >= 0 && point.x() >= 0 && point.y() <= max && point.x() <= max)
                .filter(point -> sensors.stream().noneMatch(sensor -> sensor.ruleOutBeacon(point)))
                .findAny()
                .orElseThrow();

        return distressBeacon.x() * 4000000L + distressBeacon.y();
    }
}
