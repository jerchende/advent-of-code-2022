package net.erchen.adventofcode2022.day15;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class BeaconExclusionZoneTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day15/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day15/input.txt"));
    }

    @Test
    void shouldParseFromInput() {
        var beaconExclusionZone = BeaconExclusionZone.fromInput(sampleInput());

        assertThat(beaconExclusionZone.sensors()).hasSize(14);
    }

    @Test
    void shouldCountPositionWithoutBeacons_Sample() {
        var beaconExclusionZone = BeaconExclusionZone.fromInput(sampleInput());

        assertThat(beaconExclusionZone.countPositionWithoutBeacons(10)).isEqualTo(26);
    }

    @Test
    void shouldCountPositionWithoutBeacons_Solution() {
        var beaconExclusionZone = BeaconExclusionZone.fromInput(solutionInput());

        assertThat(beaconExclusionZone.countPositionWithoutBeacons(2000000)).isEqualTo(5299855);
    }

    @Test
    void shouldFindDistressBeacon_Sample() {
        var beaconExclusionZone = BeaconExclusionZone.fromInput(sampleInput());

        assertThat(beaconExclusionZone.findDistressBeacon(20)).isEqualTo(56000011);
    }

    @Test
    void shouldFindDistressBeacon_Solution() {
        var beaconExclusionZone = BeaconExclusionZone.fromInput(solutionInput());

        assertThat(beaconExclusionZone.findDistressBeacon(4000000)).isEqualTo(13615843289729L);
    }
}