package net.erchen.adventofcode2022.day05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @Test
    void shouldInitFromInputOneDigit() {
        var command = Command.fromInput("move 2 from 1 to 4");

        assertThat(command.quality()).isEqualTo(2);
        assertThat(command.from()).isEqualTo(1);
        assertThat(command.to()).isEqualTo(4);
    }

    @Test
    void shouldInitFromInputMultipleDigits() {
        var command = Command.fromInput("move 20 from 11 to 45");

        assertThat(command.quality()).isEqualTo(20);
        assertThat(command.from()).isEqualTo(11);
        assertThat(command.to()).isEqualTo(45);
    }

    @Test
    void shouldSplitIntoSimpleCommands() {
        var command = Command.fromInput("move 2 from 1 to 4");

        assertThat(command.simpleCommands()).containsExactly(new Command(1, 1, 4), new Command(1, 1, 4));
    }
}