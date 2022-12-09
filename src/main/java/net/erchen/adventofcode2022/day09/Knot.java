package net.erchen.adventofcode2022.day09;


import lombok.With;

@With
public record Knot(int x, int y) {

    public Knot up() {
        return new Knot(x, y - 1);
    }

    public Knot down() {
        return new Knot(x, y + 1);
    }

    public Knot left() {
        return new Knot(x - 1, y);
    }

    public Knot right() {
        return new Knot(x + 1, y);
    }

    public boolean isNextTo(Knot other) {
        return Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1;
    }

    public Knot moveTo(Knot other) {
        var diffX = this.x - other.x;
        var diffY = this.y - other.y;

        return new Knot(this.x - Integer.signum(diffX), this.y - Integer.signum(diffY));

    }
}
