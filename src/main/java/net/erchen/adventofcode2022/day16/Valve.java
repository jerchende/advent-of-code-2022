package net.erchen.adventofcode2022.day16;

import java.util.Arrays;
import java.util.List;

public record Valve(String id, int flowRate, List<String> routes) {

    public static Valve fromInput(String input) {
        var valveId = input.substring("Valve ".length(), 8);
        var flowRate = Integer.parseInt(input.substring("Valve AA has flow rate=".length(), input.indexOf(";")));
        var routes = Arrays.stream(input.split(",")).map(s -> s.substring(s.length() - 2)).toList();

        return new Valve(valveId, flowRate, routes);
    }
}
