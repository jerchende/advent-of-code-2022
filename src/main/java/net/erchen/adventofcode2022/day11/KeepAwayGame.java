package net.erchen.adventofcode2022.day11;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Collections.reverseOrder;

public record KeepAwayGame(List<Monkey> monkeys, List<AtomicLong> stats) {

    public static KeepAwayGame fromInput(String input, boolean withCooldown) {
        var keepAwayGame = new KeepAwayGame(new LinkedList<>(), IntStream.range(0, 10).mapToObj(i -> new AtomicLong(0)).toList());
        keepAwayGame.monkeys().addAll(SeparatorParser.parseInput(input, "\n\n", m -> Monkey.fromInput(m, i -> keepAwayGame.monkeys().get(i), withCooldown ? cooldownOperation() : overflowOptimizer(input))));
        return keepAwayGame;
    }

    private static Function<Long, Long> overflowOptimizer(String input) {
        var optimizer = Arrays.stream(input.split("\n")).filter(line -> line.startsWith("  Test: divisible by ")).map(line -> line.substring("  Test: divisible by ".length())).mapToLong(Long::parseLong).reduce((a, b) -> a * b).orElseThrow();
        return i -> Math.floorMod(i, optimizer); // CLM of all dividers
    }

    private static Function<Long, Long> cooldownOperation() {
        return i -> Math.floorDiv(i, 3);
    }

    public void playRounds(int rounds) {
        IntStream.range(0, rounds).forEach(i -> playRound());
    }

    public void playRound() {
        for (int i = 0; i < monkeys.size(); i++) {
            stats.get(i).addAndGet(monkeys().get(i).items().size());
            monkeys().get(i).playRound();
        }
    }

    public long levelOfMonkeyBusiness() {
        return stats().stream().map(AtomicLong::get).sorted(reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b);
    }
}
