package net.erchen.adventofcode2022.day09;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.IntStream;


@NoArgsConstructor(access = AccessLevel.NONE)
public class RopeMotionSimulation {

    public static int countVisitedTailFields(String input, int knotCount) {
        LinkedList<Knot> knots = new LinkedList<>(IntStream.range(0, knotCount).mapToObj(i -> new Knot(0, 0)).toList());
        Set<Knot> visited = new HashSet<>();

        for (var direction : simpleCommands(input)) {
            var head = knots.get(0);
            knots.set(0, switch (direction) {
                case Up -> head.up();
                case Left -> head.left();
                case Right -> head.right();
                case Down -> head.down();
            });

            for (int i = 1; i < knots.size(); i++) {
                var last = knots.get(i - 1);
                var current = knots.get(i);

                if (!current.isNextTo(last)) {
                    knots.set(i, current.moveTo(last));
                }
            }
            visited.add(knots.getLast());
        }
        return visited.size();
    }

    static List<Direction> simpleCommands(String input) {
        return Arrays.stream(input.split("\n")).flatMap(command -> Collections.nCopies(Integer.parseInt(command.substring(2)), Direction.fromChar(command.charAt(0))).stream()).toList();
    }
}
