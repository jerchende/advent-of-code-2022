package net.erchen.adventofcode2022.day02;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Round {

    private final Move yourMove;
    private final Move opponentsMove;

    public static Round fromInputPart1(String input) {
        return new Round(Move.byInput(input.charAt(2)), Move.byInput(input.charAt(0)));
    }

    public static Round fromInputPart2(String input) {
        var opponentsMove = Move.byInput(input.charAt(0));
        var gameResult = GameResult.byInput(input.charAt(2));
        return new Round(opponentsMove.yourMove(gameResult), opponentsMove);
    }

    public int yourScore() {
        var gameResult = yourMove.playAgainst(opponentsMove);
        return yourMove.getScore() + gameResult.getScore();
    }
}
