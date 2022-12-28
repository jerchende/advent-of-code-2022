package net.erchen.adventofcode2022.day22;

import lombok.With;

@With
public record Position(int x, int y) {
    public Position up() {
        return this.withY(y - 1);
    }

    public Position down() {
        return this.withY(y + 1);
    }

    public Position left() {
        return this.withX(x - 1);
    }

    public Position right() {
        return this.withX(x + 1);
    }

    public Position move(Direction currentDirection) {
        return switch (currentDirection) {
            case Up -> up();
            case Down -> down();
            case Left -> left();
            case Right -> right();
        };
    }
}
