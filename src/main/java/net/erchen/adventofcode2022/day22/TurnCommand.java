package net.erchen.adventofcode2022.day22;

import java.util.Arrays;

public enum TurnCommand implements Command {
    Left, Right;

    public static TurnCommand fromInput(String input) {
        return Arrays.stream(TurnCommand.values()).filter(d -> d.name().charAt(0) == input.charAt(0)).findFirst().orElseThrow();
    }

}
