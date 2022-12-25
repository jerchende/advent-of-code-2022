package net.erchen.adventofcode2022.day24;


import lombok.With;

@With
public record Blizzard(Position position, Direction direction) {

    public Blizzard move(int maxX, int maxY) {
        var newPosition = position.move(direction);
        if (newPosition.x() < 0) newPosition = newPosition.withX(maxX - 1);
        if (newPosition.y() < 0) newPosition = newPosition.withY(maxY - 1);
        if (newPosition.x() >= maxX) newPosition = newPosition.withX(0);
        if (newPosition.y() >= maxY) newPosition = newPosition.withY(0);

        return this.withPosition(newPosition);
    }

}
