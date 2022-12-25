package net.erchen.adventofcode2022.day23;

import lombok.Data;

import java.util.Set;

@Data
public class Elf {

    private Position currentPosition;

    private Position moveCandidate = null;


    public Elf(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void determinateNextMoveCandidate(Set<Position> currentPositions, Direction firstMoveDirection) {
        moveCandidate = firstMoveDirection.nextLookups()
                .filter(moveDirection -> canMove(currentPositions, moveDirection))
                .findFirst()
                .map(moveDirection -> currentPosition.move(moveDirection))
                .orElse(null);
    }

    public boolean shouldMove(Set<Position> currentPositions) {
        return currentPosition.adjacent().anyMatch(currentPositions::contains);
    }

    private boolean canMove(Set<Position> currentPositions, Direction moveDirection) {
        return currentPosition.lookAtPosition(moveDirection).noneMatch(currentPositions::contains);
    }

    public void move() {
        currentPosition = moveCandidate;
        moveCandidate = null;
    }
}
