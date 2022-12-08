package net.erchen.adventofcode2022.day08;

import net.erchen.codingpuzzlescommon.matrix.Matrix;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public record Forest(Matrix<Integer> trees) {

    public static Forest fromInput(String input) {
        return new Forest(Matrix.fromInput(input, "\n", "", Integer::parseInt));
    }

    public int countVisibleTrees() {
        return (int) trees.allFields().filter(this::isVisible).count();
    }

    public int highestScenicScore() {
        var field = trees.allFields().max(Comparator.comparing(this::scenicScore)).get();
        return scenicScore(field);
    }

    int scenicScore(Matrix<Integer>.Field tree) {
        return visibleTrees(tree, tree.getValue(), Matrix.Field::bottom)
                * visibleTrees(tree, tree.getValue(), Matrix.Field::top)
                * visibleTrees(tree, tree.getValue(), Matrix.Field::left)
                * visibleTrees(tree, tree.getValue(), Matrix.Field::right);
    }

    private boolean isVisible(Matrix<Integer>.Field tree) {
        return isVisible(tree, tree.getValue(), Matrix.Field::bottom)
                || isVisible(tree, tree.getValue(), Matrix.Field::top)
                || isVisible(tree, tree.getValue(), Matrix.Field::left)
                || isVisible(tree, tree.getValue(), Matrix.Field::right);
    }

    private boolean isVisible(Matrix<Integer>.Field tree, int maxHeight, Function<Matrix<Integer>.Field, Optional<Matrix<Integer>.Field>> direction) {
        return direction.apply(tree)
                .map(next -> next.getValue() < maxHeight && isVisible(next, maxHeight, direction))
                .orElse(true);
    }

    int visibleTrees(Matrix<Integer>.Field tree, int maxHeight, Function<Matrix<Integer>.Field, Optional<Matrix<Integer>.Field>> direction) {
        return direction.apply(tree).map(next -> {
            if (next.getValue() < maxHeight) {
                return 1 + visibleTrees(next, maxHeight, direction);
            } else {
                return 1;
            }
        }).orElse(0);
    }

}
