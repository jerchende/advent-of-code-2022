package net.erchen.adventofcode2022.day22;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {
    Up(3), Down(1), Left(2), Right(0);


    private final int score;

    public Direction turn(TurnCommand turnCommand) {
        return switch (this) {
            case Up -> turnCommand == TurnCommand.Left ? Left : Right;
            case Down -> turnCommand == TurnCommand.Left ? Right : Left;
            case Left -> turnCommand == TurnCommand.Left ? Down : Up;
            case Right -> turnCommand == TurnCommand.Left ? Up : Down;
        };
    }
}
