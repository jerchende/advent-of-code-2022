package net.erchen.adventofcode2022.day02;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum GameResult {
    Defeat(0, 'X'), Draw(3, 'Y'), Win(6, 'Z');

    private final int score;

    private final char mappedBy;

    public static GameResult byInput(char input) {
        return Arrays.stream(GameResult.values()).filter(gameResult -> gameResult.mappedBy == input).findFirst().orElseThrow();
    }
}
