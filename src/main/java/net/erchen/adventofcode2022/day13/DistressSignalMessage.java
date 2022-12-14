package net.erchen.adventofcode2022.day13;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class DistressSignalMessage {

    public static java.util.List<Pair> fromInput(String input) {
        return SeparatorParser.parseInput(input, "\n\n", pairInput -> {
            var packageInput = pairInput.split("\n");
            return new Pair(parsePackage(packageInput[0]), parsePackage(packageInput[1]));
        });
    }

    private static PacketData parsePackage(String input) {
        List current = new List(new LinkedList<>(), null);

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '[' -> {
                    var sub = new List(new LinkedList<>(), current);
                    current.value().add(sub);
                    current = sub;
                }
                case ']' -> current = current.parent();
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    var value = input.substring(i, IntStream.of(input.indexOf(',', i), input.indexOf(']', i)).filter(j -> j != -1).min().orElseThrow());
                    i += value.length() - 1;
                    current.value().add(new Number(Integer.parseInt(value)));
                }
            }
        }

        return current.root().value().get(0);
    }

    public static int magicPart1Sum(java.util.List<Pair> pairs) {
        int sum = 0;
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).isOrderedValid()) {
                sum += i + 1;
            }
        }
        return sum;
    }

    public static int magicPart2(java.util.List<Pair> pairs) {
        var dividers = Stream.of("[[2]]", "[[6]]").toList();
        var ordered = Stream.concat(
                        dividers.stream().map(DistressSignalMessage::parsePackage),
                        pairs.stream().flatMap(pair -> Stream.of(pair.left, pair.right)))
                .sorted()
                .toList();

        return IntStream.range(0, ordered.size()).filter(i -> dividers.contains(ordered.get(i).toString())).map(i -> i + 1).reduce((a, b) -> a * b).orElseThrow();
    }


    record Pair(PacketData left, PacketData right) {
        public String toString() {
            return left.toString() + "\n" + right.toString();
        }

        boolean isOrderedValid() {
            return left.compareTo(right) < 0;
        }
    }

    interface PacketData extends Comparable<PacketData> {

    }

    public record List(java.util.List<PacketData> value, List parent) implements PacketData {

        @Override
        public String toString() {

            return "[" + value.stream().map(Objects::toString).collect(joining(",")) + "]";
        }

        public List root() {
            return parent == null ? this : parent.root();
        }

        @Override
        public int compareTo(PacketData other) {
            if (other instanceof Number otherNumber) {
                return -otherNumber.compareTo(this);
            }
            if (other instanceof List otherList) {
                for (int i = 0; i < Math.min(value().size(), otherList.value().size()); i++) {
                    var compared = this.value().get(i).compareTo(otherList.value().get(i));
                    if (compared != 0) {
                        return compared;
                    }
                }
                return Integer.compare(this.value().size(), otherList.value().size());
            }
            throw new IllegalArgumentException();
        }
    }

    public record Number(int value) implements PacketData {

        public String toString() {
            return String.valueOf(value);
        }

        @Override
        public int compareTo(PacketData other) {
            if (other instanceof Number otherNumber) {
                return Integer.compare(this.value(), otherNumber.value());
            }
            if (other instanceof List) {
                return new List(java.util.List.of(this), null).compareTo(other);
            }
            throw new IllegalArgumentException();
        }
    }
}
