package net.erchen.adventofcode2022.day17;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;


@SuppressWarnings("PointlessArithmeticExpression") // used "- 0" for better formatting -> the compiler will remove it anyway 🤷‍
@RequiredArgsConstructor
public class RockTetris {

    private final boolean[][] fields = new boolean[10000][7];

    private final List<Direction> directions;

    public int play(int untilStoppedRocks) {
        Rock rock = SquareRock.builder().line(-MinusRock.HEIGHT).build();
        boolean wasMoved = false;
        int stoppedRocks = 0;
        int currentHeight = -MinusRock.HEIGHT;

        for (long move = 0; stoppedRocks < untilStoppedRocks; move++) {
            if (!wasMoved) {
                rock = rock.spanNextRock(currentHeight);
            }

            var direction = direction(move);
            wasMoved = rock.move(direction, fields);

            if (!wasMoved) {
                rock.rest(fields);
                stoppedRocks++;
                currentHeight = Math.max(currentHeight, rock.line);
            }
        }

        System.out.println("Finished Blocks:" + stoppedRocks);
        System.out.println(debug(rock.line + 5));

        return currentHeight + 1;
    }

    public String debug(int lines) {
        return printCave(Arrays.copyOfRange(fields, 0, lines));
    }


    public static RockTetris fromInput(String input) {
        return new RockTetris(input.trim().chars().mapToObj(c -> c == '<' ? Direction.LEFT : Direction.RIGHT).toList());
    }

    public Direction direction(long move) {
        if (move % 2 == 1) return Direction.DOWN;
        return directions.get((int) (move / 2) % directions.size());
    }

    public static String printCave(boolean[][] fields) {
        StringBuilder sb = new StringBuilder();
        for (int y = fields.length - 1; y >= 0; y--) {
            var line = fields[y];
            sb.append("|");
            for (boolean field : line) {
                sb.append(field ? '#' : '.');
            }
            sb.append("|\n");
        }
        sb.append("+-------+");

        return sb.toString();
    }


    public enum Direction {
        LEFT, RIGHT, DOWN
    }

    @SuperBuilder
    public static abstract class Rock {

        final static int SPACING = 3;

        int offset = 0;

        int line;

        abstract void rest(boolean[][] fields);

        abstract boolean canMoveLeft(boolean[][] fields);

        abstract boolean canMoveRight(boolean[][] fields);

        abstract boolean canMoveDown(boolean[][] fields);

        abstract int fieldCount();

        boolean move(Direction direction, boolean[][] fields) {
            return switch (direction) {
                case RIGHT -> moveRight(fields);
                case LEFT -> moveLeft(fields);
                case DOWN -> moveDown(fields);
            };
        }

        boolean moveLeft(boolean[][] fields) {
            if (canMoveLeft(fields)) {
                offset--;
            }
            return true;
        }

        boolean moveRight(boolean[][] fields) {
            if (canMoveRight(fields)) {
                offset++;
            }
            return true;
        }


        boolean moveDown(boolean[][] fields) {
            if (canMoveDown(fields)) {
                line--;
                return true;
            } else {
                return false;
            }
        }

        abstract Rock spanNextRock(int line);

    }

    /**
     * |..@@@@.|
     */
    @SuperBuilder
    public static class MinusRock extends Rock {

        final static int HEIGHT = 1;

        @Override
        public void rest(boolean[][] fields) {
            fields[line][2 + offset] = true;
            fields[line][3 + offset] = true;
            fields[line][4 + offset] = true;
            fields[line][5 + offset] = true;
        }

        @Override
        boolean canMoveLeft(boolean[][] fields) {
            return offset > -2 && !fields[line][2 + offset - 1];
        }

        @Override
        boolean canMoveRight(boolean[][] fields) {
            return offset < 1 && !fields[line][5 + offset + 1];
        }

        @Override
        boolean canMoveDown(boolean[][] fields) {
            return line >= HEIGHT && !fields[line - 1][2 + offset] && !fields[line - 1][3 + offset] && !fields[line - 1][4 + offset] && !fields[line - 1][5 + offset];
        }

        @Override
        int fieldCount() {
            return 4;
        }

        @Override
        Rock spanNextRock(int line) {
            return PlusRock.builder().line(line + PlusRock.HEIGHT + SPACING).build();
        }
    }


    /**
     * |...@...|
     * |..@@@..|
     * |...@...|
     */

    @SuperBuilder
    public static class PlusRock extends Rock {

        final static int HEIGHT = 3;

        @Override
        public void rest(boolean[][] fields) {
            fields[line - 0][3 + offset] = true;
            fields[line - 1][2 + offset] = true;
            fields[line - 1][3 + offset] = true;
            fields[line - 1][4 + offset] = true;
            fields[line - 2][3 + offset] = true;
        }

        @Override
        boolean canMoveLeft(boolean[][] fields) {
            return offset > -2 && !fields[line][3 + offset - 1] && !fields[line - 1][2 + offset - 1] && !fields[line - 2][3 + offset - 1];
        }

        @Override
        boolean canMoveRight(boolean[][] fields) {
            return offset < 2 && !fields[line][3 + offset + 1] && !fields[line - 1][4 + offset + 1] && !fields[line - 2][3 + offset + 1];
        }

        @Override
        boolean canMoveDown(boolean[][] fields) {
            return line >= HEIGHT && !fields[line - 1 - 1][2 + offset] && !fields[line - 1 - 1][4 + offset] && !fields[line - 2 - 1][3 + offset];
        }

        @Override
        int fieldCount() {
            return 5;
        }

        @Override
        Rock spanNextRock(int line) {
            return CornerRock.builder().line(line + CornerRock.HEIGHT + SPACING).build();
        }
    }


    /**
     * |....@..|
     * |....@..|
     * |..@@@..|
     */
    @SuperBuilder
    public static class CornerRock extends Rock {

        final static int HEIGHT = 3;

        @Override
        public void rest(boolean[][] fields) {
            fields[line - 0][4 + offset] = true;
            fields[line - 1][4 + offset] = true;
            fields[line - 2][2 + offset] = true;
            fields[line - 2][3 + offset] = true;
            fields[line - 2][4 + offset] = true;
        }

        @Override
        boolean canMoveLeft(boolean[][] fields) {
            return offset > -2 && !fields[line][4 + offset - 1] && !fields[line - 1][4 + offset - 1] && !fields[line - 2][2 + offset - 1];
        }

        @Override
        boolean canMoveRight(boolean[][] fields) {
            return offset < 2 && !fields[line][4 + offset + 1] && !fields[line - 1][4 + offset + 1] && !fields[line - 2][4 + offset + 1];
        }

        @Override
        boolean canMoveDown(boolean[][] fields) {
            return line >= HEIGHT && !fields[line - 2 - 1][2 + offset] && !fields[line - 2 - 1][3 + offset] && !fields[line - 2 - 1][4 + offset];
        }

        @Override
        int fieldCount() {
            return 5;
        }

        @Override
        Rock spanNextRock(int line) {
            return IRock.builder().line(line + IRock.HEIGHT + SPACING).build();
        }
    }

    /**
     * |..@....|
     * |..@....|
     * |..@....|
     * |..@....|
     */
    @SuperBuilder
    public static class IRock extends Rock {

        final static int HEIGHT = 4;

        @Override
        public void rest(boolean[][] fields) {
            fields[line - 0][2 + offset] = true;
            fields[line - 1][2 + offset] = true;
            fields[line - 2][2 + offset] = true;
            fields[line - 3][2 + offset] = true;
        }

        @Override
        boolean canMoveLeft(boolean[][] fields) {
            return offset > -2 && !fields[line][2 + offset - 1] && !fields[line - 1][2 + offset - 1] && !fields[line - 2][2 + offset - 1] && !fields[line - 3][2 + offset - 1];
        }

        @Override
        boolean canMoveRight(boolean[][] fields) {
            return offset < 4 && !fields[line][2 + offset + 1] && !fields[line - 1][2 + offset + 1] && !fields[line - 2][2 + offset + 1] && !fields[line - 3][2 + offset + 1];
        }

        @Override
        boolean canMoveDown(boolean[][] fields) {
            return line >= HEIGHT && !fields[line - 3 - 1][2 + offset];
        }

        @Override
        int fieldCount() {
            return 4;
        }

        @Override
        Rock spanNextRock(int line) {
            return SquareRock.builder().line(line + SquareRock.HEIGHT + SPACING).build();
        }
    }

    /**
     * |..@@...|
     * |..@@...|
     */
    @SuperBuilder
    public static class SquareRock extends Rock {

        final static int HEIGHT = 2;

        @Override
        public void rest(boolean[][] fields) {
            fields[line - 0][2 + offset] = true;
            fields[line - 0][3 + offset] = true;
            fields[line - 1][2 + offset] = true;
            fields[line - 1][3 + offset] = true;
        }

        @Override
        boolean canMoveLeft(boolean[][] fields) {
            return offset > -2 && !fields[line][2 + offset - 1] && !fields[line - 1][2 + offset - 1];
        }

        @Override
        boolean canMoveRight(boolean[][] fields) {
            return offset < 3 && !fields[line][3 + offset + 1] && !fields[line - 1][3 + offset + 1];
        }

        @Override
        boolean canMoveDown(boolean[][] fields) {
            return line >= HEIGHT && !fields[line - 1 - 1][2 + offset] && !fields[line - 1 - 1][3 + offset];
        }

        @Override
        int fieldCount() {
            return 4;
        }

        @Override
        Rock spanNextRock(int line) {
            return MinusRock.builder().line(line + MinusRock.HEIGHT + SPACING).build();
        }
    }

}
