package net.erchen.adventofcode2022.day22;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public interface Command {


    static List<Command> fromInput(String input) {
        Pattern directionPattern = Pattern.compile("[LR]");
        Pattern numberPattern = Pattern.compile("[0-9]+");

        List<Command> commands = new LinkedList<>();

        var scanner = new Scanner(input.trim());
        scanner.useDelimiter(directionPattern);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                commands.add(new MoveCommand(scanner.nextInt()));
                scanner.useDelimiter(numberPattern);
            } else if (scanner.hasNext(directionPattern)) {
                commands.add(TurnCommand.fromInput(scanner.next(directionPattern)));
                scanner.useDelimiter(directionPattern);
            }
        }

        return commands;
    }
}
