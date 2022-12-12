package net.erchen.adventofcode2022.day12;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import net.erchen.codingpuzzlescommon.matrix.Matrix;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;

public class HillClimbingAlgorithm {
    private final Matrix<Position>.Field endPoint;

    public static HillClimbingAlgorithm fromInput(String input) {
        var area = Matrix.fromInput(input, "\n", "", field -> new Position(field.charAt(0)));
        var startingPoint = area.allFields().filter(field -> field.getValue().getLevel() == 'S').findFirst().orElseThrow();
        var endPoint = area.allFields().filter(field -> field.getValue().getLevel() == 'E').findFirst().orElseThrow();
        startingPoint.getValue().setLevel('a');
        endPoint.getValue().setLevel('z');

        return HillClimbingAlgorithm.builder()
                .startingPoint(startingPoint)
                .endPoint(endPoint)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public HillClimbingAlgorithm(Matrix<Position>.Field startingPoint, Matrix<Position>.Field endPoint) {
        this.endPoint = endPoint;

        startingPoint.getValue().setTotalCosts(0);

        var todo = new PriorityQueue<Matrix<Position>.Field>(1, comparingInt(x -> x.getValue().getTotalCosts()));
        var done = new HashSet<Matrix<Position>.Field>();
        todo.add(startingPoint);

        while (todo.size() > 0) {
            var current = todo.poll();
            done.add(current);
            current.getAdjacents()
                    .filter(adjacent -> adjacent.getValue().getLevel() - 1 <= current.getValue().getLevel())
                    .filter(adjacent -> !done.contains(adjacent))
                    .forEach(adjacent -> {
                        var totalCosts = 1 + current.getValue().getTotalCosts();
                        if (totalCosts < adjacent.getValue().getTotalCosts()) {
                            adjacent.getValue().setPredecessor(current);
                            adjacent.getValue().setTotalCosts(totalCosts);
                            todo.remove(adjacent);
                            todo.add(adjacent);
                        }
                    });
        }
    }

    public int calculateFewestSteps() {
        return endPoint.getValue().getTotalCosts();
    }

    public static int calculateFewestStepsFromAnyLowestPoint(String input) {
        var map = input.replace('S', 'a');
        return IntStream.range(0, map.length())
                .parallel()
                .filter(i -> map.charAt(i) == 'a')
                .mapToObj(i -> map.substring(0, i) + 'S' + map.substring(i + 1))
                .map(HillClimbingAlgorithm::fromInput)
                .mapToInt(HillClimbingAlgorithm::calculateFewestSteps)
                .min()
                .orElseThrow();
    }

    @Data
    public static class Position {
        private int level;
        private Matrix<Position>.Field predecessor = null;
        private int totalCosts = Integer.MAX_VALUE;

        public Position(int level) {
            this.level = level;
        }
    }

}
