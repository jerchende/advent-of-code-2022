package net.erchen.adventofcode2022.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

    @Test
    void shouldGetByIdentifier() {
        assertThat(Operation.fromIdentifier('+')).isEqualTo(Operation.Plus);
        assertThat(Operation.fromIdentifier('-')).isEqualTo(Operation.Minus);
        assertThat(Operation.fromIdentifier('*')).isEqualTo(Operation.Multiply);
        assertThat(Operation.fromIdentifier('/')).isEqualTo(Operation.Divide);
    }
}