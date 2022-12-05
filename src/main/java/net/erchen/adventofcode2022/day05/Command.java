package net.erchen.adventofcode2022.day05;

import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Command(int quality, int from, int to) {

    private final static Pattern PARSE_PATTERN = Pattern.compile("move ([0-9]+) from ([0-9]+) to ([0-9]+)");

    public static Command fromInput(String input) {
        var matcher = PARSE_PATTERN.matcher(input);
        matcher.find();
        return new Command(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
    }

    public Stream<Command> simpleCommands() {
        return IntStream.range(0, quality).mapToObj(i -> new Command(1, from, to));
    }
}
