package net.erchen.adventofcode2022.day22;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMapTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day22/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day22/input.txt"));
    }

    @Test
    void shouldInit() {
        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.getCommands()).hasSize(13);
        assertThat(monkeyMap.getTiles()).hasSize(160);
    }

    @Test
    void startPosition() {
        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.startingPosition()).isEqualTo(new Position(8, 0));
    }

    @Test
    void part1_Sample() {
        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.calculatePassword(false)).isEqualTo(6032);
    }

    @Test
    void part1_Solution() {
        var monkeyMap = MonkeyMap.fromInput(solutionInput());

        assertThat(monkeyMap.calculatePassword(false)).isEqualTo(20494);
    }

    static String empty3x3CubeWithCommands(String commands) {
        return """
                      ...
                      ...
                      ...
                .........
                .........
                .........
                      ......
                      ......
                      ......
                                       
                """ + commands;
    }

    static Stream<Arguments> moveCommands() {
        return Stream.of(
                Arguments.of("Face 1 Top", empty3x3CubeWithCommands("2L1"), 3, 0, Direction.Down),
                Arguments.of("Face 1 Top", empty3x3CubeWithCommands("1L1"), 3, 1, Direction.Down),
                Arguments.of("Face 1 Top", empty3x3CubeWithCommands("0L1"), 3, 2, Direction.Down),
                Arguments.of("Face 1 Left", empty3x3CubeWithCommands("0R2R1"), 3, 5, Direction.Down),
                Arguments.of("Face 1 Left", empty3x3CubeWithCommands("0R1R1"), 3, 4, Direction.Down),
                Arguments.of("Face 1 Left", empty3x3CubeWithCommands("0R0R1"), 3, 3, Direction.Down),
                Arguments.of("Face 1 Right", empty3x3CubeWithCommands("3"), 8, 11, Direction.Left),
                Arguments.of("Face 1 Right", empty3x3CubeWithCommands("0R1L3"), 7, 11, Direction.Left),
                Arguments.of("Face 1 Right", empty3x3CubeWithCommands("0R2L3"), 6, 11, Direction.Left),


                Arguments.of("Face 2 Top", empty3x3CubeWithCommands("0R3R6R1"), 0, 8, Direction.Down),
                Arguments.of("Face 2 Top", empty3x3CubeWithCommands("0R3R5R1"), 0, 7, Direction.Down),
                Arguments.of("Face 2 Top", empty3x3CubeWithCommands("0R3R4R1"), 0, 6, Direction.Down),
                Arguments.of("Face 2 Left", empty3x3CubeWithCommands("0R3R7"), 8, 11, Direction.Up),
                Arguments.of("Face 2 Left", empty3x3CubeWithCommands("0R4R7"), 8, 10, Direction.Up),
                Arguments.of("Face 2 Left", empty3x3CubeWithCommands("0R5R7"), 8, 9, Direction.Up),
                Arguments.of("Face 2 Down", empty3x3CubeWithCommands("0R5R6L1"), 8, 8, Direction.Up),
                Arguments.of("Face 2 Down", empty3x3CubeWithCommands("0R5R5L1"), 8, 7, Direction.Up),
                Arguments.of("Face 2 Down", empty3x3CubeWithCommands("0R5R4L1"), 8, 6, Direction.Up),

                Arguments.of("Face 3 Top", empty3x3CubeWithCommands("0R3R3R1"), 0, 6, Direction.Right),
                Arguments.of("Face 3 Top", empty3x3CubeWithCommands("0R3R2R1"), 1, 6, Direction.Right),
                Arguments.of("Face 3 Top", empty3x3CubeWithCommands("0R3R1R1"), 2, 6, Direction.Right),
                Arguments.of("Face 3 Down", empty3x3CubeWithCommands("0R5R3L1"), 8, 6, Direction.Right),
                Arguments.of("Face 3 Down", empty3x3CubeWithCommands("0R5R2L1"), 7, 6, Direction.Right),
                Arguments.of("Face 3 Down", empty3x3CubeWithCommands("0R5R1L1"), 6, 6, Direction.Right),

                Arguments.of("Face 4 Right", empty3x3CubeWithCommands("2R3L1"), 6, 11, Direction.Down),
                Arguments.of("Face 4 Right", empty3x3CubeWithCommands("2R4L1"), 6, 10, Direction.Down),
                Arguments.of("Face 4 Right", empty3x3CubeWithCommands("2R5L1"), 6, 9, Direction.Down),

                Arguments.of("Face 5 Down", empty3x3CubeWithCommands("0R9"), 5, 2, Direction.Up),
                Arguments.of("Face 5 Down", empty3x3CubeWithCommands("1R9"), 5, 1, Direction.Up),
                Arguments.of("Face 5 Down", empty3x3CubeWithCommands("2R9"), 5, 0, Direction.Up),
                Arguments.of("Face 5 Left", empty3x3CubeWithCommands("0R6R1"), 5, 5, Direction.Up),
                Arguments.of("Face 5 Left", empty3x3CubeWithCommands("0R7R1"), 5, 4, Direction.Up),
                Arguments.of("Face 5 Left", empty3x3CubeWithCommands("0R8R1"), 5, 3, Direction.Up),

                Arguments.of("Face 6 Up", empty3x3CubeWithCommands("0R6L3L1"), 5, 8, Direction.Left),
                Arguments.of("Face 6 Up", empty3x3CubeWithCommands("0R6L4L1"), 4, 8, Direction.Left),
                Arguments.of("Face 6 Up", empty3x3CubeWithCommands("0R6L5L1"), 3, 8, Direction.Left),

                Arguments.of("Face 6 Down", empty3x3CubeWithCommands("0R8L3R1"), 5, 0, Direction.Right),
                Arguments.of("Face 6 Down", empty3x3CubeWithCommands("0R8L4R1"), 4, 0, Direction.Right),
                Arguments.of("Face 6 Down", empty3x3CubeWithCommands("0R8L5R1"), 3, 0, Direction.Right),

                Arguments.of("Face 6 Right", empty3x3CubeWithCommands("0R6L6"), 2, 8, Direction.Left),
                Arguments.of("Face 6 Right", empty3x3CubeWithCommands("0R7L6"), 1, 8, Direction.Left),
                Arguments.of("Face 6 Right", empty3x3CubeWithCommands("0R8L6"), 0, 8, Direction.Left)
        );
    }


    @MethodSource("moveCommands")
    @ParameterizedTest(name = "{index} -> {0}")
    void shouldMoveOnCube(String name, String input, int row, int col, Direction direction) {
        var monkeyMap = MonkeyMap.fromInput(input);

        assertThat(monkeyMap.calculatePassword(true)).isEqualTo((row + 1) * 1000 + (col + 1) * 4 + direction.getScore());

    }

    @Test
    void part2_Sample() {

        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.calculatePassword(true)).isEqualTo(5031);
    }

    @Test
    @Disabled
        // FUCK my input is not formed like the example -.- need a new solution
    void part2_Solution() {

        var monkeyMap = MonkeyMap.fromInput(solutionInput());

        assertThat(monkeyMap.calculatePassword(true)).isEqualTo(20494);
    }
}