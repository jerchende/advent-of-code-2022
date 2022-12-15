package net.erchen.adventofcode2022.day15;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SensorTest {

    @Test
    void shouldParseFromInput() {
        var sensor = Sensor.fromInput("Sensor at x=-2, y=18: closest beacon is at x=-2, y=15");

        assertThat(sensor.position()).isEqualTo(new Point(-2, 18));
        assertThat(sensor.beacon()).isEqualTo(new Point(-2, 15));
        assertThat(sensor.closestBeaconDistance()).isEqualTo(3);
    }

    @Test
    void shouldGetSurroundingPoints() {
        var sensor = Sensor.fromInput("Sensor at x=4, y=4: closest beacon is at x=2, y=4");

        assertThat(sensor.closestBeaconDistance()).isEqualTo(2);
        assertThat(sensor.surroundingPoints()).contains(new Point(1, 4), new Point(7, 4), new Point(4, 1), new Point(4, 7), new Point(2, 3), new Point(5, 6)); // not complete
    }
}