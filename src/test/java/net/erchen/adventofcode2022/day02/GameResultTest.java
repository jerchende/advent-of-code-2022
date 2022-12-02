package net.erchen.adventofcode2022.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.erchen.adventofcode2022.day02.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private static Stream<Arguments> shouldInitByMappingCharArguments() {
        return Stream.of(
                Arguments.of('X', Defeat),
                Arguments.of('Y', Draw),
                Arguments.of('Z', Win)
        );
    }

    @MethodSource("shouldInitByMappingCharArguments")
    @ParameterizedTest
    void shouldInitByMappingChar(char input, GameResult expected) {
        assertThat(GameResult.byInput(input)).isEqualTo(expected);
    }
}