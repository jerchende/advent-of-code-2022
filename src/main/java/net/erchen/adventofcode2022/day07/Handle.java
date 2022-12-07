package net.erchen.adventofcode2022.day07;

public interface Handle {

    static Handle fromInput(String input, Directory parent) {
        if (input.startsWith("dir")) {
            return Directory.fromInput(input, parent);
        }

        return File.fromInput(input);
    }

    String name();

    Long size();

    boolean isDirectory();

    boolean isFile();
}
