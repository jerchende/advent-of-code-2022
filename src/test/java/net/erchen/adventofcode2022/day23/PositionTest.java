package net.erchen.adventofcode2022.day23;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void lookupPositions() {

        var position = new Position(0, 0);

        assertThat(position.lookAtPosition(Direction.North)).containsExactlyInAnyOrder(new Position(0, -1), new Position(-1, -1), new Position(1, -1));
        assertThat(position.lookAtPosition(Direction.South)).containsExactlyInAnyOrder(new Position(0, 1), new Position(-1, 1), new Position(1, 1));
        assertThat(position.lookAtPosition(Direction.West)).containsExactlyInAnyOrder(new Position(-1, 0), new Position(-1, -1), new Position(-1, 1));
        assertThat(position.lookAtPosition(Direction.East)).containsExactlyInAnyOrder(new Position(1, -1), new Position(1, 0), new Position(1, 1));
    }

    @Test
    void adjacents() {
        var position = new Position(0, 0);

        assertThat(position.adjacent()).containsExactlyInAnyOrder(
                new Position(-1, -1),
                new Position(-1, 0),
                new Position(-1, 1),
                new Position(0, -1),
                new Position(0, 1),
                new Position(1, -1),
                new Position(1, 0),
                new Position(1, 1)
        );
    }
}
