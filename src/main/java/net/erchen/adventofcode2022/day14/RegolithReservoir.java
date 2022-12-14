package net.erchen.adventofcode2022.day14;

import lombok.With;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;


@With
public record RegolithReservoir(Set<Point> blockedFields, int maxY, boolean hasFloor) {

    public static RegolithReservoir fromInput(String input) {
        var rocks = SeparatorParser.parseInput(input, "\n", Rock::fromInput);

        var maxY = rocks.stream().flatMap(rock -> rock.points().stream()).mapToInt(Point::y).max().orElse(0);

        return new RegolithReservoir(rocks.stream().flatMap(rock -> rock.points().stream()).collect(toSet()), maxY, false);
    }

    public record Rock(Set<Point> points) {
        public static Rock fromInput(String input) {
            var defPoints = SeparatorParser.parseInput(input, " -> ", Point::fromInput);

            return new Rock(IntStream.range(0, defPoints.size() - 1).boxed().flatMap(i -> defPoints.get(i).drawLineTo(defPoints.get(i + 1))).collect(toSet()));
        }
    }

    public RegolithReservoir addFloor() {
        return this.withHasFloor(true).withMaxY(maxY() + 2);
    }

    public Set<Point> simulateSand() {
        Set<Point> sandFields = new HashSet<>(blockedFields);

        sand:
        while (true) {
            var sand = new Point(500, 0);
            if (sandFields.contains(sand)) {
                break;
            }

            while (true) {
                var nextPosition = sand.nextPosition(sandFields, hasFloor ? maxY : maxY + 2);
                if (nextPosition.isEmpty()) {
                    sandFields.add(sand);
                    continue sand;
                }
                if (nextPosition.get().y() > maxY) {
                    break sand;
                }
                sand = nextPosition.get();
            }
        }
        sandFields.removeAll(blockedFields);

        return sandFields;
    }

    @With
    public record Point(int x, int y) {

        public static Point fromInput(String input) {
            var inputs = input.split(",");
            return new Point(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }

        public Stream<Point> drawLineTo(Point to) {
            if (this.y == to.y) {
                return drawHorizontalLineTo(to);
            }
            if (this.x == to.x) {
                return drawVerticalLineTo(to);
            }
            throw new IllegalArgumentException("Diagonal Lines are currently not supported");
        }

        private Stream<Point> drawHorizontalLineTo(Point to) {
            return IntStream.range(Math.min(this.x, to.x), Math.max(this.x, to.x) + 1).mapToObj(this::withX);
        }

        private Stream<Point> drawVerticalLineTo(Point to) {
            return IntStream.range(Math.min(this.y, to.y), Math.max(this.y, to.y) + 1).mapToObj(this::withY);
        }

        private Point bottom() {
            return this.withY(y + 1);
        }

        private Point bottomLeft() {
            return bottom().withX(x - 1);
        }

        private Point bottomRight() {
            return bottom().withX(x + 1);
        }

        private Stream<Point> fallingDirections() {
            return Stream.<Supplier<Point>>of(this::bottom, this::bottomLeft, this::bottomRight).map(Supplier::get);
        }

        Optional<Point> nextPosition(Set<Point> blockedFields, int floorY) {
            return fallingDirections().filter(point -> !blockedFields.contains(point) && point.y < floorY).findFirst();
        }
    }

}
