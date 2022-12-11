package net.erchen.adventofcode2022.day11;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.Collections.reverseOrder;

public record KeepAwayGame(List<Monkey> monkeys, List<AtomicInteger> stats) {

    public static KeepAwayGame fromInput(String input) {
        var keepAwayGame = new KeepAwayGame(new LinkedList<>(), IntStream.range(0, 10).mapToObj(i -> new AtomicInteger(0)).toList());
        keepAwayGame.monkeys().addAll(SeparatorParser.parseInput(input, "\n\n", m -> Monkey.fromInput(m, i -> keepAwayGame.monkeys().get(i))));
        return keepAwayGame;
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

    public int levelOfMonkeyBusiness() {
        return stats().stream().map(AtomicInteger::get).sorted(reverseOrder()).limit(2).reduce(1, (a, b) -> a * b);
    }
}
