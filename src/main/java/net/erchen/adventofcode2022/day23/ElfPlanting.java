package net.erchen.adventofcode2022.day23;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.erchen.codingpuzzlescommon.matrix.Matrix;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


@Getter
@RequiredArgsConstructor
public class ElfPlanting {

    private final List<Elf> elves;

    private Direction moveDirection = Direction.North;

    public static ElfPlanting fromInput(String input) {
        var booleanMatrix = Matrix.fromInput(input, "\n", "", s -> s.equals("#"));

        return new ElfPlanting(booleanMatrix.allFields().filter(Matrix.Field::getValue).map(field -> new Elf(new Position(field.getX(), field.getY()))).toList());
    }

    public void playRounds(int rounds) {
        IntStream.range(0, rounds).forEach(i -> playRound());
    }

    public int countRoundsUntilNoOneMoves() {
        for (int i = 1; ; i++) {
            if (!playRound()) {
                return i;
            }
        }
    }

    public boolean playRound() {
        var positions = currentPositions().collect(toSet());

        var elvesToMove = elves.stream().filter(elf -> elf.shouldMove(positions)).toList();
        if (elvesToMove.size() == 0) {
            return false;
        }
        elvesToMove.parallelStream().forEach(elf -> elf.determinateNextMoveCandidate(positions, moveDirection));

        var allowedMoveCandidates = elvesToMove.stream()
                .map(Elf::getMoveCandidate)
                .filter(Objects::nonNull)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .map(Map.Entry::getKey)
                .collect(toSet());

        elvesToMove.stream().filter(elf -> allowedMoveCandidates.contains(elf.getMoveCandidate())).forEach(Elf::move);

        moveDirection = moveDirection.next();
        return true;
    }

    private Stream<Position> currentPositions() {
        return elves.stream().map(Elf::getCurrentPosition);
    }

    private int currentMinX() {
        return currentPositions().mapToInt(Position::x).min().orElse(0);
    }

    private int currentMaxX() {
        return currentPositions().mapToInt(Position::x).max().orElse(0);
    }

    public int currentWidth() {
        return currentMaxX() - currentMinX() + 1;
    }

    private int currentMinY() {
        return currentPositions().mapToInt(Position::y).min().orElse(0);
    }

    private int currentMaxY() {
        return currentPositions().mapToInt(Position::y).max().orElse(0);
    }

    public int currentHeight() {
        return currentMaxY() - currentMinY() + 1;
    }

    public int countEmptyFields() {
        return currentWidth() * currentHeight() - elves.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        var positions = currentPositions().collect(toSet());

        for (int y = currentMinY(); y <= currentMaxY(); y++) {
            for (int x = currentMinX(); x <= currentMaxX(); x++) {
                sb.append(positions.contains(new Position(x, y)) ? '#' : '.');
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
