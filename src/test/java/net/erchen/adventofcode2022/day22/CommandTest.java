package net.erchen.adventofcode2022.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @Test
    void shouldInit() {

        var commands = Command.fromInput("10R5");

        assertThat(commands).containsExactly(new MoveCommand(10), TurnCommand.Right, new MoveCommand(5));
    }
}