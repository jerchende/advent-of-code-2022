package net.erchen.adventofcode2022.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnCommandTest {

    @Test
    void fromInput() {
        assertThat(TurnCommand.fromInput("L")).isEqualTo(TurnCommand.Left);
        assertThat(TurnCommand.fromInput("R")).isEqualTo(TurnCommand.Right);
    }
}