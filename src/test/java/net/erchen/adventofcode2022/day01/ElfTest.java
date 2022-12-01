package net.erchen.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElfTest {

    @Test
    void shouldParseInput() {
        var elf = Elf.parseInput("""
                100
                200
                300
                """);

        assertThat(elf.carriedCalories()).containsExactly(100, 200, 300);
    }

    @Test
    void shouldSumAllCalories() {
        var elf = new Elf(List.of(10, 20, 30));

        assertThat(elf.totalCalories()).isEqualTo(60);
    }
}