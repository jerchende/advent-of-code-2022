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

    public static MonkeyMap fromInput(String input) {
        var inputs = input.split("\n\n");

        var mapLines = inputs[0].split("\n");
        var width = Arrays.stream(mapLines).mapToInt(String::length).max().orElseThrow();
        var height = mapLines.length;

        Map<Position, TileType> tiles = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position position = new Position(x, y);
                if (x >= mapLines[y].length()) {
                    tiles.put(position, TileType.Nothing);
                } else {
                    tiles.put(position, TileType.fromInput(mapLines[y].charAt(x)));
                }
            }
        }
        return MonkeyMap.builder()
                .commands(Command.fromInput(inputs[1]))
                .tiles(tiles)
                .width(width)
                .height(height)
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
                    Position nextPosition = currentPosition.move(currentDirection, width, height);
                    var nextTile = tiles.get(nextPosition);
                    while (nextTile == TileType.Nothing) {
                        if (isCube) {
                            nextPosition = null; // do the magic for part2;
                        } else {
                            nextPosition = nextPosition.move(currentDirection, width, height);
                        }
                        nextTile = tiles.get(nextPosition);
                    }
                    if (nextTile == TileType.Wall) {
                        break;
                    }
                    currentPosition = nextPosition;

                }
            }
        }
        return (currentPosition.y() + 1) * 1000 + (currentPosition.x() + 1) * 4 + currentDirection.getScore();
    }

    Position startingPosition() {
        return tiles.keySet().stream().filter(position -> position.y() == 0).filter(position -> tiles.get(position) == TileType.Open).min(Comparator.comparing(Position::x)).orElseThrow();
    }
}
