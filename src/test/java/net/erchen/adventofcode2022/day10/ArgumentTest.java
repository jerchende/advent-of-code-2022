package net.erchen.adventofcode2022.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArgumentTest {

    @Test
    void shouldInitByInput_Noop() {
        var noop = Argument.fromInput("noop");

        assertThat(noop.command()).isEqualTo(Command.Noop);
        assertThat(noop.parameter()).isNull();
        ;
    }

    @Test
    void shouldInitByInput_addXPositive() {
        var noop = Argument.fromInput("addx 3");

        assertThat(noop.command()).isEqualTo(Command.AddX);
        assertThat(noop.parameter()).isEqualTo(3);
    }

    @Test
    void shouldInitByInput_addXNegative() {
        var noop = Argument.fromInput("addx -5");

        assertThat(noop.command()).isEqualTo(Command.AddX);
        assertThat(noop.parameter()).isEqualTo(-5);
    }
}