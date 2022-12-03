package net.erchen.adventofcode2022.day03;

public record Item(char character) {

    public int priority() {
        return this.character <= 'Z' ? this.character - 'A' + 27 : this.character - 'a' + 1;
    }

}
