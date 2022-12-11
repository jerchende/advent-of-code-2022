package net.erchen.adventofcode2022.day11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

public record Monkey(LinkedList<Integer> items, Function<Integer, Integer> operation, Function<Integer, Monkey> nextMonkey) {

    public static Monkey fromInput(String input, Function<Integer, Monkey> monkeySupplier) {
        var inputs = input.split("\n");


        return new Monkey(parseStartingItems(inputs[1]), parseOperation(inputs[2]), parseTest(Arrays.copyOfRange(inputs, 3, 6), monkeySupplier));
    }

    static LinkedList<Integer> parseStartingItems(String input) {
        return new LinkedList<>(Arrays.stream(input.trim().substring("Starting items: ".length()).split(",")).map(String::trim).map(Integer::parseInt).toList());
    }

    static Function<Integer, Integer> parseOperation(String input) {
        var calcString = input.trim().substring("Operation: new = ".length());
        if (calcString.equals("old * old")) {
            return i -> i * i;
        }
        if (calcString.startsWith("old * ")) {
            return i -> i * Integer.parseInt(calcString.substring("old * ".length()));
        }
        if (calcString.startsWith("old + ")) {
            return i -> i + Integer.parseInt(calcString.substring("old + ".length()));
        }

        throw new IllegalArgumentException();
    }

    static Function<Integer, Monkey> parseTest(String[] inputs, Function<Integer, Monkey> monkeySupplier) {
        var dividableBy = Integer.parseInt(inputs[0].trim().substring("Test: divisible by ".length()));
        var ifTrue = Integer.parseInt(inputs[1].trim().substring("If true: throw to monkey ".length()));
        var ifFalse = Integer.parseInt(inputs[2].trim().substring("If false: throw to monkey ".length()));


        return i -> i % dividableBy == 0 ? monkeySupplier.apply(ifTrue) : monkeySupplier.apply(ifFalse);
    }

    public void catchItem(Integer item) {
        items.add(item);
    }

    public void playRound() {
        items.forEach(item -> {
            var currentItem = Math.floorDiv(operation.apply(item), 3);
            nextMonkey().apply(currentItem).catchItem(currentItem);
        });
        items.clear();
    }
}
