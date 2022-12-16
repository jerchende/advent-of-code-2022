package net.erchen.adventofcode2022.day16;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValveTest {

    @Test
    void shouldParseFromInput() {
        var valve = Valve.fromInput("Valve AA has flow rate=10; tunnels lead to valves DD, II, BB");

        assertThat(valve.id()).isEqualTo("AA");
        assertThat(valve.flowRate()).isEqualTo(10);
        assertThat(valve.routes()).containsExactly("DD", "II", "BB");
    }
}