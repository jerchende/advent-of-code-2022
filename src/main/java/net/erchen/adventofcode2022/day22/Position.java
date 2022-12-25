package net.erchen.adventofcode2022.day22;

import lombok.With;

@With
public record Position(int x, int y) {
    public Position up(int height) {
        return this.withY(y == 0 ? height - 1 : y - 1);
    }

    public Position down(int height) {
        return this.withY(y + 1 == height ? 0 : y + 1);
    }

    public Position left(int width) {
        return this.withX(x == 0 ? width - 1 : x - 1);
    }

    public Position right(int width) {
        return this.withX(x + 1 == width ? 0 : x + 1);
    }

    public Position move(Direction currentDirection, int width, int height) {
        return switch (currentDirection) {
            case Up -> up(height);
            case Down -> down(height);
            case Left -> left(width);
            case Right -> right(width);
        };
    }
}
