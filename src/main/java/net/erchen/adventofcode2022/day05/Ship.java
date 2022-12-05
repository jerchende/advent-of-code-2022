package net.erchen.adventofcode2022.day05;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Getter

@RequiredArgsConstructor
public class Ship {

    private final List<LinkedList<Character>> crates;
    private final List<Command> commands;

    public static Ship fromInput(String input) {
        var inputs = input.split("\n\n");

        var crates = parseCrates(inputs[0]);
        var commands = SeparatorParser.parseInput(inputs[1], "\n", Command::fromInput);

        return new Ship(crates, commands);
    }

    private static List<LinkedList<Character>> parseCrates(String input) {
        var lines = input.split("\n");
        var stackCount = (lines[lines.length - 1].length() + 2) / 4;

        return IntStream.range(0, stackCount)
                .mapToObj(row -> new LinkedList<>(
                        Arrays.stream(lines)
                                .limit(lines.length - 1)
                                .map(line -> {
                                    var endIndex = ((row + 1) * 4) - 1;
                                    if (line.length() < endIndex) return " ";
                                    return line.substring(row * 4, endIndex);
                                })
                                .filter(s -> !s.isBlank())
                                .map(s -> s.charAt(1))
                                .toList())
                ).toList();
    }

    public void applyCommandsCrateMover9000() {
        commands.stream().flatMap(Command::simpleCommands).forEach(command -> crates.get(command.to() - 1).push(crates.get(command.from() - 1).pop()));
    }

    public void applyCommandsCrateMover9001() {
        commands.forEach(command -> {
            for (int i = 0; i < command.quality(); i++) {
                crates.get(command.to() - 1).add(i, (crates.get(command.from() - 1).pop()));
            }
        });
    }


    public String message() {
        return crates.stream().map(LinkedList::getFirst).map(Object::toString).collect(Collectors.joining());
    }
}
