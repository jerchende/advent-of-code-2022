package net.erchen.adventofcode2022.day15;

public record Point(int x, int y) {

    public int distanceTo(Point b) {
        return Math.abs(this.x() - b.x()) + Math.abs(this.y() - b.y());
    }
}
