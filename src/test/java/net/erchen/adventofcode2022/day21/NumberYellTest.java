package net.erchen.adventofcode2022.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberYellTest {

    @Test
    void shouldInit() {
        var yell = NumberYell.fromInput("42");

        assertThat(yell).contains(new NumberYell(42));
    }

    @Test
    void shouldNotInit() {
        var yell = NumberYell.fromInput("pppw + sjmn");

        assertThat(yell).isEmpty();
    }
}