package net.erchen.adventofcode2022.day21;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMathTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day21/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day21/input.txt"));
    }

    @Test
    void shouldInit() {
        var monkeyMath = MonkeyMath.fromInput(sampleInput(), false);

        assertThat(monkeyMath.monkeys()).hasSize(15);
    }

    @Test
    void shouldGetValueForRoot_Sample() {
        var monkeyMath = MonkeyMath.fromInput(sampleInput(), false);

        assertThat(monkeyMath.monkeys().get("root").value()).isEqualTo(152);
    }

    @Test
    void shouldGetValueForRoot_Solution() {
        var monkeyMath = MonkeyMath.fromInput(solutionInput(), false);

        assertThat(monkeyMath.monkeys().get("root").value()).isEqualTo(158731561459602L);
    }

    @Test
    void shouldGetValueForHuman_Sample() {
        var monkeyMath = MonkeyMath.fromInput(sampleInput(), true);

        assertThat(monkeyMath.humanValue()).isEqualTo(301);
    }

    @Test
    void shouldGetValueForHuman_Solution() {
        var monkeyMath = MonkeyMath.fromInput(solutionInput(), true);

        assertThat(monkeyMath.humanValue()).isEqualTo(3769668716709L);
    }

}