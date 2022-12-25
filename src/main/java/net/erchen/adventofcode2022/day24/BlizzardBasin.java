package net.erchen.adventofcode2022.day24;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import net.erchen.codingpuzzlescommon.matrix.Matrix;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;


@Slf4j
@Getter
@Builder
public class BlizzardBasin {

    private final Set<Blizzard> initialBlizzards;
    @Builder.Default
    private final Map<Integer, Set<Position>> blizzardsTransformationCache = new HashMap<>();
    private final int width;
    private final int height;


    public static BlizzardBasin fromInput(String input) {
        var blizzardCharacters = Arrays.stream(Direction.values()).map(Direction::getRepresentation).toList();
        var characterMatrix = Matrix.fromInput(input, "\n", "", s -> s.charAt(0));

        var blizzards = characterMatrix.allFields().filter(field -> blizzardCharacters.contains(field.getValue())).map(field -> new Blizzard(new Position(field.getX() - 1, field.getY() - 1), Direction.fromInput(field.getValue()))).collect(toSet());
        var split = input.split("\n");
        return BlizzardBasin.builder()
                .initialBlizzards(blizzards)
                .width(split[0].length() - 2)
                .height(split.length - 2)
                .build();
    }

    public State getRoute() {
        var nearGoal = getRoute(initialState(), new Position(width - 1, height - 1));
        return nearGoal.withMinute(nearGoal.minute() + 1).withCurrentPosition(nearGoal.currentPosition().down());
    }

    public State getRouteWithGettingBack() {
        var goal = getRoute();
        var nearlyStart = getRoute(goal, new Position(0, 0));
        var start = nearlyStart.withMinute(nearlyStart.minute() + 1).withCurrentPosition(nearlyStart.currentPosition().up());
        var nearGoal = getRoute(start, new Position(width - 1, height - 1));
        return nearGoal.withMinute(nearGoal.minute() + 1).withCurrentPosition(nearGoal.currentPosition().down());
    }


    public State getRoute(State from, Position to) {
        var todo = new LinkedList<State>();
        todo.add(from);
        Set<State> visited = new HashSet<>();

        while (todo.size() > 0) {
            var current = todo.poll();
            if (visited.contains(current)) {
                continue;
            }
            if (current.currentPosition().equals(to)) {
                return current;
            }

            visited.add(current);
            var blizzardPositions = blizzardsPositionsAt(current.minute() + 1);

            nextStates(current)
                    .filter(state -> !blizzardPositions.contains(state.currentPosition()))
                    .forEach(todo::add);
        }
        throw new IllegalStateException("No route found");
    }

    State initialState() {
        return new State(new Position(0, -1), 0);
    }

    Set<Position> blizzardsPositionsAt(int minute) {
        return blizzardsTransformationCache.computeIfAbsent(minute, key -> blizzardsAt(minute).map(Blizzard::position).collect(toSet()));
    }

    Stream<Blizzard> blizzardsAt(int minute) {
        if (minute == 0) {
            return initialBlizzards.stream();
        }
        return blizzardsAt(minute - 1).map(b -> b.move(width, height));
    }

    Stream<State> nextStates(State state) {
        var waitState = state.withMinute(state.minute + 1);
        return Stream.concat(
                Arrays.stream(Direction.values())
                        .map(direction -> waitState.withCurrentPosition(state.currentPosition().move(direction)))
                        .filter(newState -> newState.currentPosition().x() >= 0 && newState.currentPosition().x() < width && newState.currentPosition().y() >= 0 && newState.currentPosition().y() < height),
                Stream.of(waitState)
        );
    }


    @With
    record State(Position currentPosition, int minute) {

    }

    public String toString(Collection<Blizzard> blizzards, State state) {
        StringBuilder sb = new StringBuilder();
        sb.append("#.").append("#".repeat(width)).append("\n");

        for (int y = 0; y < height; y++) {
            sb.append("#");
            for (int x = 0; x < width; x++) {
                sb.append(blizzardSign(blizzards, new Position(x, y)));
            }
            sb.append("#\n");
        }
        sb.append("#".repeat(width)).append(".").append("#").append("\n");

        if (state != null) {
            var myPosition = (state.currentPosition().y() + 1) * (width + 3) + state.currentPosition().x() + 1;
            sb.replace(myPosition, myPosition + 1, "E");
        }

        return sb.toString();
    }

    private char blizzardSign(Collection<Blizzard> blizzards, Position position) {
        var blizzardPositions = blizzards.stream().filter(blizzard -> blizzard.position().equals(position)).toList();

        if (blizzardPositions.size() == 0) {
            return '.';
        }
        if (blizzardPositions.size() == 1) {
            return blizzardPositions.get(0).direction().getRepresentation();
        }
        return (char) (blizzardPositions.size() + '0');
    }

}
