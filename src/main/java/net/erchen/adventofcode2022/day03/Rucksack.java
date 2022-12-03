package net.erchen.adventofcode2022.day03;


import java.util.List;
import java.util.stream.Stream;


public record Rucksack(List<Item> left, List<Item> right) {

    public static Rucksack fromInput(String input) {
        return new Rucksack(toItemList(input.substring(0, input.length() / 2)), toItemList(input.substring(input.length() / 2)));
    }

    private static List<Item> toItemList(String input) {
        return input.chars().mapToObj(c -> new Item((char) c)).toList();
    }

    public Stream<Item> distinctItems() {
        return Stream.concat(left.stream(), right.stream()).distinct();
    }

    public Stream<Item> commonItems() {
        return left.stream().filter(right::contains).distinct();
    }

    public boolean contains(Item item) {
        return left.contains(item) || right.contains(item);
    }

    public static Item commonItem(Rucksack rucksack1, Rucksack rucksack2, Rucksack rucksack3) {
        return Stream.of(rucksack1, rucksack2, rucksack3).flatMap(Rucksack::distinctItems).filter(item -> rucksack1.contains(item) && rucksack2.contains(item) && rucksack3.contains(item)).findFirst().orElseThrow();
    }
}
