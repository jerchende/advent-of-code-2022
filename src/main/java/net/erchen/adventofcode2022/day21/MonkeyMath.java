package net.erchen.adventofcode2022.day21;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public record MonkeyMath(Map<String, Yell> monkeys) {

    public static MonkeyMath fromInput(String input, boolean withHuman) {
        Map<String, Yell> monkeys = new ConcurrentSkipListMap<>();

        var inputs = input.split("\n");

        Arrays.stream(inputs).forEach(monkeyInput -> lookupOrCreateMonkey(monkeyInput.substring(0, 4), inputs, monkeys, withHuman));

        return new MonkeyMath(monkeys);
    }

    public static Yell lookupOrCreateMonkey(String name, String[] inputs, Map<String, Yell> monkeys, boolean withHuman) {
        return monkeys.computeIfAbsent(name,
                monkeyName -> Arrays.stream(inputs)
                        .filter(monkeyInput -> monkeyInput.startsWith(monkeyName))
                        .findFirst()
                        .map(monkeyInput -> monkeyInput.startsWith("humn") && withHuman ? new Human() : Yell.fromInput(monkeyInput.substring(6), newMonkeyName -> lookupOrCreateMonkey(newMonkeyName, inputs, monkeys, withHuman)))
                        .orElseThrow());
    }

    public long humanValue() {
        var root = (MathOperationYell) monkeys.get("root");

        var fixedValue = (root.left().containsHuman() ? root.right() : root.left()).value();
        var toResolve = (MathOperationYell) (root.left().containsHuman() ? root.left() : root.right());

        return toResolve.resolveHuman(fixedValue);
    }

}