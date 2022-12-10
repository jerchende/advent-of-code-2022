package net.erchen.adventofcode2022.day10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @Test
    void shouldInitByInput() {
        assertThat(Command.fromInput("noop")).isEqualTo(Command.Noop);
        assertThat(Command.fromInput("addx")).isEqualTo(Command.AddX);
    }
}