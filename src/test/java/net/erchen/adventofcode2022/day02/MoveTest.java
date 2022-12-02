package net.erchen.adventofcode2022.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.erchen.adventofcode2022.day02.GameResult.*;
import static net.erchen.adventofcode2022.day02.Move.*;
import static org.assertj.core.api.Assertions.assertThat;

class MoveTest {

    private static Stream<Arguments> shouldCalculateWinnerArguments() {
        return Stream.of(
                Arguments.of(Rock, Rock, Draw),
                Arguments.of(Rock, Paper, Defeat),
                Arguments.of(Rock, Scissors, Win),

                Arguments.of(Paper, Rock, Win),
                Arguments.of(Paper, Paper, Draw),
                Arguments.of(Paper, Scissors, Defeat),

                Arguments.of(Scissors, Rock, Defeat),
                Arguments.of(Scissors, Paper, Win),
                Arguments.of(Scissors, Scissors, Draw)
        );
    }

    @MethodSource("shouldCalculateWinnerArguments")
    @ParameterizedTest
    void shouldCalculateWinner(Move you, Move opponent, GameResult expected) {
        assertThat(you.playAgainst(opponent)).isEqualTo(expected);
    }


    private static Stream<Arguments> shouldInitByMappingCharArguments() {
        return Stream.of(
                Arguments.of('A', Rock),
                Arguments.of('X', Rock),
                Arguments.of('B', Paper),
                Arguments.of('Y', Paper),
                Arguments.of('C', Scissors),
                Arguments.of('Z', Scissors)
        );
    }

    @MethodSource("shouldInitByMappingCharArguments")
    @ParameterizedTest
    void shouldInitByMappingChar(char input, Move expected) {
        assertThat(Move.byInput(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> shouldGetYourMoveArguments() {
        return Stream.of(
                Arguments.of(Rock, Draw, Rock),
                Arguments.of(Rock, Defeat, Scissors),
                Arguments.of(Rock, Win, Paper),

                Arguments.of(Paper, Draw, Paper),
                Arguments.of(Paper, Defeat, Rock),
                Arguments.of(Paper, Win, Scissors),

                Arguments.of(Scissors, Draw, Scissors),
                Arguments.of(Scissors, Defeat, Paper),
                Arguments.of(Scissors, Win, Rock)
        );
    }

    @MethodSource("shouldGetYourMoveArguments")
    @ParameterizedTest
    void shouldGetYourMove(Move opponent, GameResult gameResult, Move expectedMove) {
        assertThat(opponent.yourMove(gameResult)).isEqualTo(expectedMove);
    }
}