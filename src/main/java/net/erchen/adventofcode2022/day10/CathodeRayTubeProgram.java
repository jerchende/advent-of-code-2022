package net.erchen.adventofcode2022.day10;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public record CathodeRayTubeProgram(List<Argument> arguments) {

    public static CathodeRayTubeProgram fromInput(String input) {
        return new CathodeRayTubeProgram(SeparatorParser.parseInput(input, "\n", Argument::fromInput));
    }

    public Stream<State> runProgramm() {
        AtomicInteger cycle = new AtomicInteger(0);
        AtomicInteger x = new AtomicInteger(1);
        return arguments.stream().flatMap(argument -> switch (argument.command()) {
                    case Noop -> Stream.of(new State(cycle.incrementAndGet(), x.get()));
                    case AddX -> Stream.of(new State(cycle.incrementAndGet(), x.get()), new State(cycle.incrementAndGet(), x.getAndAdd(argument.parameter())));
                }
        );
    }

    public int signalStrengths() {
        return runProgramm().filter(state -> (state.cycle() - 20) % 40 == 0).mapToInt(state -> state.cycle() * state.x()).sum();
    }

    public String renderOutput() {
        return runProgramm().map(state -> (isSpriteShown(state) ? "#" : ".") + (state.cycle() % 40 == 0 ? "\n" : "")).collect(joining());
    }

    static boolean isSpriteShown(State state) {
        return Math.abs(state.x() - state.cycle() % 40 + 1) <= 1;
    }

}
