package net.erchen.adventofcode2022.day22;

import lombok.Builder;
import lombok.Getter;

import java.util.*;


@Getter
@Builder
public class MonkeyMap {

    private final List<Command> commands;
    private final Map<Position, TileType> tiles;

    private final int width;
    private final int height;
    private final int tileSize;

    public static MonkeyMap fromInput(String input) {
        var inputs = input.split("\n\n");

        var mapLines = inputs[0].split("\n");
        var width = Arrays.stream(mapLines).mapToInt(String::length).max().orElseThrow();
        var height = mapLines.length;

        Map<Position, TileType> tiles = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position position = new Position(x, y);
                if (x < mapLines[y].length()) {
                    tiles.put(position, TileType.fromInput(mapLines[y].charAt(x)));
                }
            }
        }
        return MonkeyMap.builder()
                .commands(Command.fromInput(inputs[1]))
                .tiles(tiles)
                .width(width)
                .height(height)
                .tileSize(height / 3)
                .build();

    }

    public int calculatePassword(boolean isCube) {
        Position currentPosition = startingPosition();
        Direction currentDirection = Direction.Right;

        for (var command : commands) {
            if (command instanceof TurnCommand turnDirection) {
                currentDirection = currentDirection.turn(turnDirection);
            }

            if (command instanceof MoveCommand moveCommand) {
                for (int i = 0; i < moveCommand.numberOfTiles(); i++) {
                    Position nextPosition = currentPosition.move(currentDirection);
                    TileType nextTile;
                    Direction nextDirection = currentDirection;
                    while ((nextTile = tiles.getOrDefault(nextPosition, TileType.Nothing)) == TileType.Nothing) {
                        if (isCube) {
                            if (currentDirection == Direction.Up && nextPosition.y() < 0) { // Face 1, up
                                nextDirection = Direction.Down;
                                nextPosition = nextPosition.withX(tileSize - (nextPosition.x() + 1 - 2 * tileSize)).withY(tileSize);
                            } else if (currentDirection == Direction.Left && nextPosition.y() < tileSize && nextPosition.x() == 2 * tileSize - 1) { // face 1, left
                                nextDirection = Direction.Down;
                                nextPosition = nextPosition.withX(tileSize + currentPosition.y()).withY(tileSize);
                            } else if (currentDirection == Direction.Right && nextPosition.y() < tileSize && nextPosition.x() == 3 * tileSize) { // face 1, right
                                nextDirection = Direction.Left;
                                nextPosition = nextPosition.withX(tileSize * 4 - 1).withY(2 * tileSize + (tileSize - 1 - currentPosition.y()));
                            } else if (currentDirection == Direction.Up && nextPosition.x() < tileSize && nextPosition.y() == tileSize - 1) { // face 2, up
                                nextDirection = Direction.Down;
                                nextPosition = nextPosition.withX(3 * tileSize - 1 - currentPosition.x()).withY(0);
                            } else if (currentDirection == Direction.Left && nextPosition.x() < 0) { // face 2, left
                                nextDirection = Direction.Up;
                                nextPosition = nextPosition.withX(4 * tileSize - 1 - (currentPosition.y() - tileSize)).withY(3 * tileSize - 1);
                            } else if (currentDirection == Direction.Down && nextPosition.x() < tileSize && nextPosition.y() == 2 * tileSize) { // face 2, down
                                nextDirection = Direction.Up;
                                nextPosition = nextPosition.withX(3 * tileSize - 1 - (currentPosition.x())).withY(3 * tileSize - 1);
                            } else if (currentDirection == Direction.Up && nextPosition.y() == tileSize - 1) { //face 3 up
                                nextDirection = Direction.Right;
                                nextPosition = nextPosition.withX(2 * tileSize).withY(currentPosition.x() - tileSize);
                            } else if (currentDirection == Direction.Down && nextPosition.y() == 2 * tileSize) { //face 3 down
                                nextDirection = Direction.Right;
                                nextPosition = nextPosition.withX(2 * tileSize).withY(2 * tileSize + (2 * tileSize - 1) - currentPosition.x());
                            } else if (currentDirection == Direction.Right && nextPosition.x() == 3 * tileSize) { //face 4 right
                                nextDirection = Direction.Down;
                                nextPosition = nextPosition.withX(4 * tileSize - 1 - (currentPosition.y() - tileSize)).withY(2 * tileSize);
                            } else if (currentDirection == Direction.Down && nextPosition.y() == 3 * tileSize && nextPosition.x() < tileSize * 3) { //face 5 down
                                nextDirection = Direction.Up;
                                nextPosition = nextPosition.withX(tileSize * 3 - 1 - currentPosition.x()).withY(2 * tileSize - 1);
                            } else if (currentDirection == Direction.Left && nextPosition.x() == 2 * tileSize - 1) { //face 5 left
                                nextDirection = Direction.Up;
                                nextPosition = nextPosition.withX(tileSize + (3 * tileSize - 1 - currentPosition.y())).withY(2 * tileSize - 1);
                            } else if (currentDirection == Direction.Up && nextPosition.y() == 2 * tileSize - 1) { //face 6 up
                                nextDirection = Direction.Left;
                                nextPosition = nextPosition.withX(tileSize * 3 - 1).withY(tileSize + (4 * tileSize - 1 - currentPosition.x()));
                            } else if (currentDirection == Direction.Down && nextPosition.y() == 3 * tileSize) { //face 6 down
                                nextDirection = Direction.Right;
                                nextPosition = nextPosition.withX(0).withY(tileSize + (4 * tileSize - 1 - currentPosition.x()));
                            } else if (currentDirection == Direction.Right && nextPosition.x() == 4 * tileSize) { //face 6 right
                                nextDirection = Direction.Left;
                                nextPosition = nextPosition.withX(tileSize * 3 - 1).withY(tileSize * 3 - 1 - currentPosition.y());
                            } else {
                                throw new IllegalStateException("Unknown Position");
                            }
                            if (nextPosition.x() == 66 && nextPosition.y() == 0) {
                                throw new IllegalStateException("Can not happen!");
                            }

                        } else {
                            if (nextPosition.x() < 0) {
                                nextPosition = nextPosition.withX(width - 1);
                            } else if (nextPosition.x() >= width) {
                                nextPosition = nextPosition.withX(0);
                            } else if (nextPosition.y() < 0) {
                                nextPosition = nextPosition.withY(height - 1);
                            } else if (nextPosition.y() >= height) {
                                nextPosition = nextPosition.withY(0);
                            } else {
                                nextPosition = nextPosition.move(currentDirection);
                            }
                        }
                    }
                    if (nextTile == TileType.Wall) {
                        break;
                    }
                    currentPosition = nextPosition;
                    currentDirection = nextDirection;

                }
            }
        }
        return (currentPosition.y() + 1) * 1000 + (currentPosition.x() + 1) * 4 + currentDirection.getScore();
    }

    Position startingPosition() {
        return tiles.keySet().stream().filter(position -> position.y() == 0).filter(position -> tiles.get(position) == TileType.Open).min(Comparator.comparing(Position::x)).orElseThrow();
    }
}
