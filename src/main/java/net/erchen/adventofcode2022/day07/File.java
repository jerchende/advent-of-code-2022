package net.erchen.adventofcode2022.day07;

public record File(String name, Long size) implements Handle {
    public static File fromInput(String input) {
        var inputs = input.split(" ");
        return new File(inputs[1], Long.parseLong(inputs[0]));
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return true;
    }
}
