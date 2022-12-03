package net.erchen.adventofcode2022.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RucksackTest {

    @Test
    void fromInput() {
        var rucksack = Rucksack.fromInput("vJrwpWtwJgWrhcsFMMfFFhFp");

        assertThat(rucksack.left()).containsExactly(new Item('v'), new Item('J'), new Item('r'), new Item('w'), new Item('p'), new Item('W'), new Item('t'), new Item('w'), new Item('J'), new Item('g'), new Item('W'), new Item('r'));
        assertThat(rucksack.right()).containsExactly(new Item('h'), new Item('c'), new Item('s'), new Item('F'), new Item('M'), new Item('M'), new Item('f'), new Item('F'), new Item('F'), new Item('h'), new Item('F'), new Item('p'));
    }

    @Test
    void shouldFindCommonItem() {
        var rucksack = Rucksack.fromInput("vJrwpWtwJgWrhcsFMMfFFhFp");

        assertThat(rucksack.commonItems()).containsExactly(new Item('p'));
    }

    @Test
    void shouldFindDistinctCommonItem() {
        var rucksack = Rucksack.fromInput("PmmdzqPrVvPwwTWBwg");

        assertThat(rucksack.commonItems()).containsExactly(new Item('P'));
    }

    @Test
    void shouldReturnDestinctItems() {
        var rucksack = Rucksack.fromInput("AAaabc");

        assertThat(rucksack.distinctItems()).containsExactly(new Item('A'), new Item('a'), new Item('b'), new Item('c'));
    }

    @Test
    void findCommonItem() {
        var rucksack1 = Rucksack.fromInput("vJrwpWtwJgWrhcsFMMfFFhFp");
        var rucksack2 = Rucksack.fromInput("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        var rucksack3 = Rucksack.fromInput("PmmdzqPrVvPwwTWBwg");

        var commonItem = Rucksack.commonItem(rucksack1, rucksack2, rucksack3);

        assertThat(commonItem).isEqualTo(new Item('r'));
    }
}