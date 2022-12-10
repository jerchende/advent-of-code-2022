package net.erchen.adventofcode2022.day10;

public record Argument(Command command, Integer parameter) {
    public static Argument fromInput(String input) {
        var inputs = input.split(" ");
        return new Argument(Command.fromInput(inputs[0]), inputs.length == 2 ? Integer.parseInt(inputs[1]) : null);
    }
}
