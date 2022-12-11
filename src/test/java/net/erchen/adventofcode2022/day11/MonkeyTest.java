package net.erchen.adventofcode2022.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MonkeyTest {

    @Test
    void shouldParseStatingItems() {
        var startingItems = Monkey.parseStartingItems("  Starting items: 79, 98");

        assertThat(startingItems).containsExactly(79, 98);
    }

    @CsvSource({
            "Operation: new = old * 19,38",
            "Operation: new = old + 6,8",
            "Operation: new = old * old,4"
    })
    @ParameterizedTest
    void shouldParseOperation(String operationStr, int expectedResult) {
        var operation = Monkey.parseOperation("  " + operationStr);

        assertThat(operation.apply(2)).isEqualTo(expectedResult);
    }

    @Test
    void shouldParseTest() {
        var monkey2 = new Monkey(new LinkedList<>(List.of(2, 3, 4)), null, null);
        var monkey3 = new Monkey(new LinkedList<>(List.of(4, 5, 6)), null, null);

        var test = Monkey.parseTest("""
                Test: divisible by 23
                  If true: throw to monkey 2
                  If false: throw to monkey 3""".split("\n"), i -> i == 2 ? monkey2 : (i == 3 ? monkey3 : null));

        assertThat(test.apply(23)).isEqualTo(monkey2);
        assertThat(test.apply(46)).isEqualTo(monkey2);
        assertThat(test.apply(22)).isEqualTo(monkey3);
        assertThat(test.apply(24)).isEqualTo(monkey3);
        assertThat(test.apply(47)).isEqualTo(monkey3);
    }

    @Test
    void shouldParseMonkey() {
        var monkey = Monkey.fromInput("""
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """, i -> null);

        assertThat(monkey.items()).containsExactly(79, 98);
        assertThat(monkey.operation().apply(1)).isEqualTo(19);
        assertThat(monkey.nextMonkey()).isNotNull();
    }

    @Test
    void shouldCatchItem() {
        var monkey = new Monkey(new LinkedList<>(List.of(2, 3, 4)), null, null);

        monkey.catchItem(5);

        assertThat(monkey.items()).containsExactly(2, 3, 4, 5);
    }

    @Test
    void shouldPlayRound() {
        var monkey2 = new Monkey(new LinkedList<>(), null, null);
        var monkey3 = new Monkey(new LinkedList<>(), null, null);
        var monkey = Monkey.fromInput("""
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """, i -> i == 2 ? monkey2 : (i == 3 ? monkey3 : null));

        monkey.playRound();

        assertThat(monkey.items()).isEmpty();
        assertThat(monkey2.items()).isEmpty();
        assertThat(monkey3.items()).contains(500, 620);

    }
}