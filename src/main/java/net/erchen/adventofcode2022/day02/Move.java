package net.erchen.adventofcode2022.day02;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static net.erchen.adventofcode2022.day02.GameResult.*;


@Getter

@RequiredArgsConstructor
public enum Move {
    Rock(1, new char[]{'A', 'X'}), Paper(2, new char[]{'B', 'Y'}), Scissors(3, new char[]{'C', 'Z'});

    private final int score;

    private final char[] mappedBy;

    public static Move byInput(char input) {
        return Arrays.stream(Move.values()).filter(move -> move.mappedBy[0] == input || move.mappedBy[1] == input).findFirst().orElseThrow();
    }

    public GameResult playAgainst(Move opponent) {
        return switch (this) {
            case Rock -> switch (opponent) {
                case Rock -> Draw;
                case Paper -> Defeat;
                case Scissors -> Win;
            };
            case Paper -> switch (opponent) {
                case Rock -> Win;
                case Paper -> Draw;
                case Scissors -> Defeat;
            };
            case Scissors -> switch (opponent) {
                case Rock -> Defeat;
                case Paper -> Win;
                case Scissors -> Draw;
            };
        };
    }

    public Move yourMove(GameResult gameResult) {
        return Arrays.stream(Move.values()).filter(move -> move.playAgainst(this) == gameResult).findFirst().orElseThrow();
    }
}
