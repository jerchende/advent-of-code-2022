package net.erchen.adventofcode2022.day02;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RockPaperScissorsTournament {

    private final List<Round> rounds;

    public static RockPaperScissorsTournament fromInputPart1(String input) {
        return new RockPaperScissorsTournament(SeparatorParser.parseInput(input, "\n", Round::fromInputPart1));
    }

    public static RockPaperScissorsTournament fromInputPart2(String input) {
        return new RockPaperScissorsTournament(SeparatorParser.parseInput(input, "\n", Round::fromInputPart2));
    }

    public int yourScore() {
        return rounds.stream().mapToInt(Round::yourScore).sum();
    }
}
