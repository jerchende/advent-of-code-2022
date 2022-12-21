package net.erchen.adventofcode2022.day21;

import java.util.function.Function;

public record MathOperationYell(Yell left, Operation operation, Yell right) implements Yell {

    static MathOperationYell fromInput(String input, Function<String, Yell> yellSupplier) {
        return new MathOperationYell(yellSupplier.apply(input.substring(0, 4)), Operation.fromIdentifier(input.charAt(5)), yellSupplier.apply(input.substring(7)));
    }

    @Override
    public long value() {
        return switch (operation) {
            case Plus -> left.value() + right().value();
            case Minus -> left.value() - right().value();
            case Multiply -> left.value() * right().value();
            case Divide -> left.value() / right().value();
        };
    }

    @Override
    public String toString() {
        if (containsHuman()) {
            return " (" + left + " " + operation + " " + right + ") ";
        } else {
            return "" + value();
        }
    }

    @Override
    public boolean containsHuman() {
        return left.containsHuman() || right().containsHuman();
    }

    @Override
    public long resolveHuman(long expectedResult) {
        var fixedValue = (left().containsHuman() ? right() : left()).value();
        var toResolve = left().containsHuman() ? left() : right();

        return switch (operation) {
            case Plus -> toResolve.resolveHuman(expectedResult - fixedValue);
            case Minus -> left.containsHuman() ? toResolve.resolveHuman(expectedResult + fixedValue) : toResolve.resolveHuman(-expectedResult + fixedValue);
            case Multiply -> toResolve.resolveHuman(expectedResult / fixedValue);
            case Divide -> {
                if (!left.containsHuman()) {
                    throw new IllegalArgumentException("not supported, not required ;)");
                }
                yield toResolve.resolveHuman(expectedResult * fixedValue);
            }
        };
    }
}
