package net.erchen.adventofcode2022.day24;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class BlizzardBasinTest {

    @SneakyThrows
    static String simpleSampleInput() {
        return Files.readString(Path.of("src/test/resources/day24/simpleSample.txt"));
    }

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day24/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day24/input.txt"));
    }

    @Test
    void shouldInitFromInput() {
        var blizzardBasin = BlizzardBasin.fromInput(simpleSampleInput());
        assertThat(blizzardBasin.getInitialBlizzards()).containsExactlyInAnyOrder(new Blizzard(new Position(0, 1), Direction.Right), new Blizzard(new Position(3, 3), Direction.Down));
        assertThat(blizzardBasin.getWidth()).isEqualTo(5);
        assertThat(blizzardBasin.getHeight()).isEqualTo(5);
    }

    @Test
    void shouldInitFromInput2() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());
        assertThat(blizzardBasin.getWidth()).isEqualTo(6);
        assertThat(blizzardBasin.getHeight()).isEqualTo(4);
    }

    @Test
    void shouldMoveBlizzards() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(0).toList(), null)).isEqualTo("""
                #.######
                #>>.<^<#
                #.<..<<#
                #>v.><>#
                #<^v^^>#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(1).toList(), null)).isEqualTo("""
                #.######
                #.>3.<.#
                #<..<<.#
                #>2.22.#
                #>v..^<#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(2).toList(), null)).isEqualTo("""
                #.######
                #.2>2..#
                #.^22^<#
                #.>2.^>#
                #.>..<.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(3).toList(), null)).isEqualTo("""
                #.######
                #<^<22.#
                #.2<.2.#
                #><2>..#
                #..><..#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(4).toList(), null)).isEqualTo("""
                #.######
                #.<..22#
                #<<.<..#
                #<2.>>.#
                #.^22^.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(5).toList(), null)).isEqualTo("""
                #.######
                #2.v.<>#
                #<.<..<#
                #.^>^22#
                #.2..2.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(6).toList(), null)).isEqualTo("""
                #.######
                #>2.<.<#
                #.2v^2<#
                #>..>2>#
                #<....>#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(7).toList(), null)).isEqualTo("""
                #.######
                #.22^2.#
                #<v.<2.#
                #>>v<>.#
                #>....<#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(8).toList(), null)).isEqualTo("""
                #.######
                #.<>2^.#
                #..<<.<#
                #.22..>#
                #.2v^2.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(9).toList(), null)).isEqualTo("""
                #.######
                #<.2>>.#
                #.<<.<.#
                #>2>2^.#
                #.v><^.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(10).toList(), null)).isEqualTo("""
                #.######
                #.2..>2#
                #<2v2^.#
                #<>.>2.#
                #..<>..#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(11).toList(), null)).isEqualTo("""
                #.######
                #2^.^2>#
                #<v<.^<#
                #..2.>2#
                #.<..>.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(12).toList(), null)).isEqualTo("""
                #.######
                #>>.<^<#
                #.<..<<#
                #>v.><>#
                #<^v^^>#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(13).toList(), null)).isEqualTo("""
                #.######
                #.>3.<.#
                #<..<<.#
                #>2.22.#
                #>v..^<#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(14).toList(), null)).isEqualTo("""
                #.######
                #.2>2..#
                #.^22^<#
                #.>2.^>#
                #.>..<.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(15).toList(), null)).isEqualTo("""
                #.######
                #<^<22.#
                #.2<.2.#
                #><2>..#
                #..><..#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(16).toList(), null)).isEqualTo("""
                #.######
                #.<..22#
                #<<.<..#
                #<2.>>.#
                #.^22^.#
                ######.#
                """);

        assertThat(blizzardBasin.toString(blizzardBasin.blizzardsAt(17).toList(), null)).isEqualTo("""
                #.######
                #2.v.<>#
                #<.<..<#
                #.^>^22#
                #.2..2.#
                ######.#
                """);
    }

    @Test
    void shouldGetNextState1() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        var nextStates = blizzardBasin.nextStates(blizzardBasin.initialState());

        assertThat(nextStates).containsExactlyInAnyOrder(
                new BlizzardBasin.State(new Position(0, -1), 1),
                new BlizzardBasin.State(new Position(0, 0), 1)
        );
    }

    @Test
    void shouldGetNextState2() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        var nextStates = blizzardBasin.nextStates(new BlizzardBasin.State(new Position(1, 1), 0));

        assertThat(nextStates).containsExactlyInAnyOrder(
                new BlizzardBasin.State(new Position(1, 1), 1),
                new BlizzardBasin.State(new Position(1, 2), 1),
                new BlizzardBasin.State(new Position(1, 0), 1),
                new BlizzardBasin.State(new Position(2, 1), 1),
                new BlizzardBasin.State(new Position(0, 1), 1)

        );
    }

    @Test
    void shouldGetNextState3() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        var nextStates = blizzardBasin.nextStates(new BlizzardBasin.State(new Position(0, 0), 0));

        assertThat(nextStates).containsExactlyInAnyOrder(
                new BlizzardBasin.State(new Position(0, 0), 1),
                new BlizzardBasin.State(new Position(0, 1), 1),
                new BlizzardBasin.State(new Position(1, 0), 1)
        );
    }

    @Test
    void shouldGetRoute_SimpleSample() {
        var blizzardBasin = BlizzardBasin.fromInput(simpleSampleInput());

        var route = blizzardBasin.getRoute();

        assertThat(route.minute()).isEqualTo(10);
    }

    @Test
    void shouldGetRoute_Sample() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        var route = blizzardBasin.getRoute();

        assertThat(route.minute()).isEqualTo(18);
    }

    @Test
    void shouldGetRoute_Solution() {
        var blizzardBasin = BlizzardBasin.fromInput(solutionInput());

        var route = blizzardBasin.getRoute();

        assertThat(route.minute()).isEqualTo(228);
    }

    @Test
    void shouldGetRouteWithGettingBack_Sample() {
        var blizzardBasin = BlizzardBasin.fromInput(sampleInput());

        var route = blizzardBasin.getRouteWithGettingBack();

        assertThat(route.minute()).isEqualTo(54);
    }

    @Test
    void shouldGetRouteWithGettingBack_Solution() {
        var blizzardBasin = BlizzardBasin.fromInput(solutionInput());

        var route = blizzardBasin.getRouteWithGettingBack();

        assertThat(route.minute()).isEqualTo(723);
    }

}