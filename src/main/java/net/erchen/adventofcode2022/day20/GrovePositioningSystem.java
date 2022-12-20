package net.erchen.adventofcode2022.day20;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


@Slf4j
@Getter
@RequiredArgsConstructor
public class GrovePositioningSystem {

    private final LinkedList<Number> numbers;
    private final List<Number> originalOrder;
    private final Number zero;

    public static GrovePositioningSystem fromInput(String input, boolean applyMultiplier) {
        var numbers = SeparatorParser.parseInput(input, "\n", i -> new Number(Long.parseLong(i) * (applyMultiplier ? 811589153 : 1)));
        var zero = numbers.stream().filter(number -> number.getValue() == 0L).findFirst().orElseThrow();
        return new GrovePositioningSystem(new LinkedList<>(numbers), numbers, zero);
    }

    public void mix() {
        for (var i : originalOrder) {
            if (i.getValue() == 0) {
                continue;
            }
            var currentIndex = numbers.indexOf(i);
            int newIndex = (int) (currentIndex + (i.getValue() % (numbers.size() - 1))) % (numbers.size() - 1);
            while (newIndex <= 0) {
                newIndex += numbers.size() - 1;
            }
            numbers.add(newIndex > currentIndex ? newIndex + 1 : newIndex, i);
            numbers.remove(newIndex < currentIndex ? currentIndex + 1 : currentIndex);
            //log.debug("Moving {}, result: {}", i, numbers);
        }
    }

    public LongStream coordinates(int mixCount) {
        IntStream.range(0, mixCount).forEach(i -> mix());
        var zeroIndex = numbers.indexOf(zero);
        return Stream.of(1000, 2000, 3000).mapToLong(i -> numbers.get((zeroIndex + i) % (numbers.size())).getValue());
    }

    @Getter
    @RequiredArgsConstructor
    public static class Number {
        private final long value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
