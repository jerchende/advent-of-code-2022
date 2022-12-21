package net.erchen.adventofcode2022.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathOperationYellTest {

    @Test
    void shouldInit() {

        var yell = MathOperationYell.fromInput("pppw + sjmn", monkey -> new NumberYell(monkey.hashCode()));

        assertThat(yell.left()).isEqualTo(new NumberYell("pppw".hashCode()));
        assertThat(yell.right()).isEqualTo(new NumberYell("sjmn".hashCode()));
        assertThat(yell.operation()).isEqualTo(Operation.Plus);
    }

}