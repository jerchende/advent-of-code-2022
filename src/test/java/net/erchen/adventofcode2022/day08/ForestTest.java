package net.erchen.adventofcode2022.day08;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ForestTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day08/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day08/input.txt"));
    }


    @Test
    void shouldInitFromInput() {

        var forest = Forest.fromInput(sampleInput());

        assertThat(forest.trees().toString()).isEqualTo("""
                3 0 3 7 3
                2 5 5 1 2
                6 5 3 3 2
                3 3 5 4 9
                3 5 3 9 0""");

    }

    @Test
    void shouldCountVisibleTrees_Sample() {
        var forest = Forest.fromInput(sampleInput());

        assertThat(forest.countVisibleTrees()).isEqualTo(21);
    }

    @Test
    void shouldCountVisibleTrees_Solution() {
        var forest = Forest.fromInput(solutionInput());

        assertThat(forest.countVisibleTrees()).isEqualTo(1779);
    }

    @Test
    void shouldGetScenicScore_Sample() {
        var forest = Forest.fromInput(sampleInput());

        assertThat(forest.scenicScore(forest.trees().field(2, 1))).isEqualTo(4);
        assertThat(forest.scenicScore(forest.trees().field(2, 3))).isEqualTo(8);
    }

    @Test
    void shouldGetHighestScenicScore_Sample() {
        var forest = Forest.fromInput(sampleInput());

        assertThat(forest.highestScenicScore()).isEqualTo(8);
    }

    @Test
    void shouldGetHighestScenicScore_Solution() {
        var forest = Forest.fromInput(solutionInput());

        assertThat(forest.highestScenicScore()).isEqualTo(172224);
    }

}