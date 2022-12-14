package net.erchen.adventofcode2022.day13;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class DistressSignalMessageTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day13/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day13/input.txt"));
    }


    @Test
    void shouldParseFromInput_Sample() {
        var pairs = DistressSignalMessage.fromInput(sampleInput());

        assertThat(pairs).hasSize(8);
        assertThat(pairs.stream().map(DistressSignalMessage.Pair::toString).collect(Collectors.joining("\n\n"))).isEqualToIgnoringNewLines(sampleInput());
    }

    @Test
    void shouldParseFromInput_Solution() {
        var pairs = DistressSignalMessage.fromInput(solutionInput());

        assertThat(pairs).hasSize(150);
        assertThat(pairs.stream().map(DistressSignalMessage.Pair::toString).collect(Collectors.joining("\n\n"))).isEqualToIgnoringNewLines(solutionInput());
    }

    @Test
    void numberIsLeftOfOtherNumber() {
        assertThat(number(1).compareTo(number(1))).isEqualTo(0);
        assertThat(number(1).compareTo(number(3))).isEqualTo(-1);
        assertThat(number(4).compareTo(number(1))).isEqualTo(1);
    }

    @Test
    void listIsLeftOfOtherNumber() {
        assertThat(list(number(1), number(1), number(3), number(1), number(1)).compareTo(list(number(1), number(1), number(5), number(1), number(1)))).isEqualTo(-1);
        assertThat(list(number(1), number(1), number(5), number(1), number(1)).compareTo(list(number(1), number(1), number(5), number(1), number(1)))).isEqualTo(0);
        assertThat(list(number(1), number(1), number(5), number(1), number(1)).compareTo(list(number(1), number(1), number(3), number(1), number(1)))).isEqualTo(1);


        assertThat(list(number(7), number(7), number(7), number(7)).compareTo(list(number(7), number(7), number(7)))).isEqualTo(1);
        assertThat(list(number(7), number(7), number(7)).compareTo(list(number(7), number(7), number(7), number(7)))).isEqualTo(-1);

        assertThat(list().compareTo(list(number(3)))).isEqualTo(-1);
        assertThat(list(number(3)).compareTo(list())).isEqualTo(1);
    }

    @Test
    void part1_Sample() {
        var magicPart1Sum = DistressSignalMessage.magicPart1Sum(DistressSignalMessage.fromInput(sampleInput()));

        assertThat(magicPart1Sum).isEqualTo(13);
    }


    @Test
    void part1_Solution() {
        var magicPart1Sum = DistressSignalMessage.magicPart1Sum(DistressSignalMessage.fromInput(solutionInput()));

        assertThat(magicPart1Sum).isEqualTo(5350);
    }

    @Test
    void part2_Sample() {
        var magicPart2 = DistressSignalMessage.magicPart2(DistressSignalMessage.fromInput(sampleInput()));

        assertThat(magicPart2).isEqualTo(140);
    }


    @Test
    void part2_Solution() {
        var magicPart2 = DistressSignalMessage.magicPart2(DistressSignalMessage.fromInput(solutionInput()));

        assertThat(magicPart2).isEqualTo(19570);
    }


    private static DistressSignalMessage.List list(DistressSignalMessage.PacketData... values) {
        return new DistressSignalMessage.List(Arrays.asList(values), null);
    }

    private static DistressSignalMessage.Number number(int value) {
        return new DistressSignalMessage.Number(value);
    }

}