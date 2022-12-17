package net.erchen.adventofcode2022.day17;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static net.erchen.adventofcode2022.day17.RockTetris.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class RockTetrisTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day17/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day17/input.txt"));
    }

    @Test
    void shouldPrintCave() {

        var output = RockTetris.printCave(new boolean[][]{{false, false, true, true, false, false, true}, {true, false, false, true, false, false, false}});

        assertThat(output).isEqualTo("""
                |#..#...|
                |..##..#|
                +-------+""");

    }

    @Test
    void shouldGetDirections() {
        var rockTetris = RockTetris.fromInput("<>>");

        assertThat(rockTetris.direction(0)).isEqualTo(LEFT);
        assertThat(rockTetris.direction(1)).isEqualTo(DOWN);
        assertThat(rockTetris.direction(2)).isEqualTo(RIGHT);
        assertThat(rockTetris.direction(3)).isEqualTo(DOWN);
        assertThat(rockTetris.direction(4)).isEqualTo(RIGHT);
        assertThat(rockTetris.direction(5)).isEqualTo(DOWN);
        assertThat(rockTetris.direction(6)).isEqualTo(LEFT);
        assertThat(rockTetris.direction(7)).isEqualTo(DOWN);
    }


    @Test
    void shouldPlayRight() {
        var rockTetris = RockTetris.fromInput(">");

        rockTetris.play(5);

        assertThat(rockTetris.debug(12)).isEqualTo("""
                |.....##|
                |......#|
                |......#|
                |......#|
                |......#|
                |......#|
                |......#|
                |....###|
                |.....#.|
                |....###|
                |.....#.|
                |...####|
                +-------+""");
    }

    @Test
    void shouldPlayLeft() {
        var rockTetris = RockTetris.fromInput("<");

        rockTetris.play(5);

        assertThat(rockTetris.debug(12)).isEqualTo("""
                |.......|
                |##.....|
                |##.....|
                |#......|
                |#......|
                |#.#....|
                |#.#....|
                |###....|
                |.#.....|
                |###....|
                |.#.....|
                |####...|
                +-------+""");
    }

    @Test
    void shouldFitAfter5RocksFromSample() {
        var rockTetris = RockTetris.fromInput(sampleInput());

        rockTetris.play(5);

        assertThat(rockTetris.debug(13)).isEqualTo("""
                |.......|
                |.......|
                |.......|
                |.......|
                |....##.|
                |....##.|
                |....#..|
                |..#.#..|
                |..#.#..|
                |#####..|
                |..###..|
                |...#...|
                |..####.|
                +-------+""");
    }

    @Test
    void shouldFitAfter10RocksFromSample() {
        var rockTetris = RockTetris.fromInput(sampleInput());

        rockTetris.play(10);

        assertThat(rockTetris.debug(21)).isEqualTo("""
                |.......|
                |.......|
                |.......|
                |.......|
                |....#..|
                |....#..|
                |....##.|
                |##..##.|
                |######.|
                |.###...|
                |..#....|
                |.####..|
                |....##.|
                |....##.|
                |....#..|
                |..#.#..|
                |..#.#..|
                |#####..|
                |..###..|
                |...#...|
                |..####.|
                +-------+""");
    }

    @Test
    void part1_Sample() {
        var rockTetris = RockTetris.fromInput(sampleInput());

        assertThat(rockTetris.play(2022)).isEqualTo(3068);
    }

    @Test
    void part1_Solution() {
        var rockTetris = RockTetris.fromInput(solutionInput());

        assertThat(rockTetris.play(2022)).isEqualTo(3215);
    }

    @Nested
    class MinusRockTest {

        @Test
        void shouldRest() {
            var rock = RockTetris.MinusRock.builder().line(2).build();
            boolean[][] cave = new boolean[3][7];

            rock.rest(cave);

            assertThat(RockTetris.printCave(cave)).isEqualTo("""
                    |..####.|
                    |.......|
                    |.......|
                    +-------+""");
        }

        static Stream<Arguments> collisionDetectionArguments() {
            return Stream.of(
                    Arguments.of("""
                            |.#@@@@.|
                            |.......|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, true, false, true),

                    Arguments.of("""
                            |..@@@@#|
                            |.......|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, true, true, false),

                    Arguments.of("""
                            |..@@@@.|
                            |..#....|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@@@@.|
                            |...#...|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@@@@.|
                            |....#..|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@@@@.|
                            |.....#.|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@@@@.|
                            |.#....#|
                            |.....#.|
                            |#....#.|
                            +-------+
                            """, true, true, true)
            );
        }

        @ParameterizedTest
        @MethodSource({"collisionDetectionArguments"})
        void shouldDetectCollision(String caveInput, boolean canMoveDown, boolean canMoveLeft, boolean canMoveRight) {
            var rock = RockTetris.MinusRock.builder().line(3).build();

            boolean[][] cave = fromInput(caveInput);

            assertThat(rock.canMoveDown(cave)).isEqualTo(canMoveDown);
            assertThat(rock.canMoveLeft(cave)).isEqualTo(canMoveLeft);
            assertThat(rock.canMoveRight(cave)).isEqualTo(canMoveRight);
        }

    }


    @Nested
    class PlusRockTest {


        @Test
        void shouldRest() {
            var rock = RockTetris.PlusRock.builder().line(2).build();
            boolean[][] cave = new boolean[3][7];

            rock.rest(cave);

            assertThat(RockTetris.printCave(cave)).isEqualTo("""
                    |...#...|
                    |..###..|
                    |...#...|
                    +-------+""");
        }

        static Stream<Arguments> collisionDetectionArguments() {
            return Stream.of(
                    Arguments.of("""
                            |...@...|
                            |..@@@..|
                            |...@...|
                            |.......|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |..#@#..|
                            |..@@@..|
                            |...@...|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |...@...|
                            |.#@@@#.|
                            |...@...|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |...@...|
                            |..@@@..|
                            |..#@#..|
                            |.......|
                            +-------+
                            """, false, false, false),
                    Arguments.of("""
                            |.#.@.#.|
                            |#.@@@.#|
                            |.#.@.##|
                            |..#.#..|
                            +-------+
                            """, true, true, true)
            );
        }

        @ParameterizedTest
        @MethodSource({"collisionDetectionArguments"})
        void shouldDetectCollision(String caveInput, boolean canMoveDown, boolean canMoveLeft, boolean canMoveRight) {
            var rock = RockTetris.PlusRock.builder().line(3).build();

            boolean[][] cave = fromInput(caveInput);

            assertThat(rock.canMoveDown(cave)).isEqualTo(canMoveDown);
            assertThat(rock.canMoveLeft(cave)).isEqualTo(canMoveLeft);
            assertThat(rock.canMoveRight(cave)).isEqualTo(canMoveRight);
        }
    }

    @Nested
    class CornerRockTest {

        @Test
        void shouldRest() {
            var rock = RockTetris.CornerRock.builder().line(2).build();
            boolean[][] cave = new boolean[3][7];

            rock.rest(cave);

            assertThat(RockTetris.printCave(cave)).isEqualTo("""
                    |....#..|
                    |....#..|
                    |..###..|
                    +-------+""");
        }

        static Stream<Arguments> collisionDetectionArguments() {
            return Stream.of(
                    Arguments.of("""
                            |....@..|
                            |....@..|
                            |..@@@..|
                            |.......|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |..#.@.#|
                            |..#.@.#|
                            |#.@@@.#|
                            |##...##|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |...#@#.|
                            |....@..|
                            |..@@@..|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |....@..|
                            |...#@#.|
                            |..@@@..|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |....@..|
                            |....@..|
                            |.#@@@#.|
                            |.#...#.|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |....@..|
                            |....@..|
                            |..@@@..|
                            |..#....|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |....@..|
                            |....@..|
                            |..@@@..|
                            |...#...|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |....@..|
                            |....@..|
                            |..@@@..|
                            |....#..|
                            +-------+
                            """, false, true, true)
            );
        }

        @ParameterizedTest
        @MethodSource({"collisionDetectionArguments"})
        void shouldDetectCollision(String caveInput, boolean canMoveDown, boolean canMoveLeft, boolean canMoveRight) {
            var rock = RockTetris.CornerRock.builder().line(3).build();

            boolean[][] cave = fromInput(caveInput);

            assertThat(rock.canMoveDown(cave)).isEqualTo(canMoveDown);
            assertThat(rock.canMoveLeft(cave)).isEqualTo(canMoveLeft);
            assertThat(rock.canMoveRight(cave)).isEqualTo(canMoveRight);
        }

    }

    @Nested
    class IRockTest {


        @Test
        void shouldRest() {
            var rock = RockTetris.IRock.builder().line(3).build();
            boolean[][] cave = new boolean[4][7];

            rock.rest(cave);

            assertThat(RockTetris.printCave(cave)).isEqualTo("""
                    |..#....|
                    |..#....|
                    |..#....|
                    |..#....|
                    +-------+""");
        }

        static Stream<Arguments> collisionDetectionArguments() {
            return Stream.of(
                    Arguments.of("""
                            |..@....|
                            |..@....|
                            |..@....|
                            |..@....|
                            |.......|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |..@....|
                            |..@....|
                            |..@....|
                            |..@....|
                            |..#....|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@....|
                            |..@....|
                            |..@....|
                            |.#@#...|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |..@....|
                            |..@....|
                            |.#@#...|
                            |..@....|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |..@....|
                            |.#@#...|
                            |..@....|
                            |..@....|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |.#@#...|
                            |..@....|
                            |..@....|
                            |..@....|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |..@....|
                            |..@....|
                            |..@....|
                            |..@....|
                            |.#.#...|
                            +-------+
                            """, true, true, true)
            );
        }

        @ParameterizedTest
        @MethodSource({"collisionDetectionArguments"})
        void shouldDetectCollision(String caveInput, boolean canMoveDown, boolean canMoveLeft, boolean canMoveRight) {
            var rock = RockTetris.IRock.builder().line(4).build();

            boolean[][] cave = fromInput(caveInput);

            assertThat(rock.canMoveDown(cave)).isEqualTo(canMoveDown);
            assertThat(rock.canMoveLeft(cave)).isEqualTo(canMoveLeft);
            assertThat(rock.canMoveRight(cave)).isEqualTo(canMoveRight);
        }

    }

    @Nested
    class SquareRockTest {

        @Test
        void shouldRest() {
            var rock = RockTetris.SquareRock.builder().line(2).build();
            boolean[][] cave = new boolean[3][7];

            rock.rest(cave);

            assertThat(RockTetris.printCave(cave)).isEqualTo("""
                    |..##...|
                    |..##...|
                    |.......|
                    +-------+""");
        }

        static Stream<Arguments> collisionDetectionArguments() {
            return Stream.of(
                    Arguments.of("""
                            |..@@...|
                            |..@@...|
                            |.......|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |#.@@.#.|
                            |#.@@.#.|
                            |.#..#..|
                            +-------+
                            """, true, true, true),
                    Arguments.of("""
                            |.#@@#..|
                            |..@@...|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |..@@...|
                            |.#@@#..|
                            |.......|
                            +-------+
                            """, true, false, false),
                    Arguments.of("""
                            |..@@...|
                            |..@@...|
                            |..#....|
                            +-------+
                            """, false, true, true),
                    Arguments.of("""
                            |..@@...|
                            |..@@...|
                            |...#...|
                            +-------+
                            """, false, true, true)
            );
        }

        @ParameterizedTest
        @MethodSource({"collisionDetectionArguments"})
        void shouldDetectCollision(String caveInput, boolean canMoveDown, boolean canMoveLeft, boolean canMoveRight) {
            var rock = RockTetris.SquareRock.builder().line(2).build();

            boolean[][] cave = fromInput(caveInput);

            assertThat(rock.canMoveDown(cave)).isEqualTo(canMoveDown);
            assertThat(rock.canMoveLeft(cave)).isEqualTo(canMoveLeft);
            assertThat(rock.canMoveRight(cave)).isEqualTo(canMoveRight);
        }

    }

    @Test
    void shouldParseFromInput() {
        boolean[][] cave = fromInput("""
                |.#@@@@.|
                |.......|
                |.....#.|
                |#....#.|
                +-------+
                """);

        assertThat(RockTetris.printCave(cave)).isEqualTo("""
                |.#.....|
                |.......|
                |.....#.|
                |#....#.|
                +-------+""");
    }

    private static boolean[][] fromInput(String string) {
        var inputLines = string.split("\n");
        boolean[][] result = new boolean[inputLines.length - 1][7];
        for (int y = 0; y < inputLines.length - 1; y++) {
            for (int x = 1; x < inputLines[y].length(); x++) {
                if (inputLines[y].charAt(x) == '#') {
                    result[inputLines.length - y - 2][x - 1] = true;
                }
            }
        }
        return result;
    }
}