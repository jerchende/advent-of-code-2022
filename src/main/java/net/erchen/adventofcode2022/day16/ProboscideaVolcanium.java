package net.erchen.adventofcode2022.day16;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;
import org.paukov.combinatorics3.Generator;

import java.util.*;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProboscideaVolcanium {

    private final Map<String, Valve> valves;

    private final Map<Route, Integer> costs;

    public static ProboscideaVolcanium fromInput(String input) {
        var valves = SeparatorParser.parseInput(input, "\n", Valve::fromInput);

        return new ProboscideaVolcanium(filterValves(valves), calculateCosts(valves));
    }

    private static Map<String, Valve> filterValves(List<Valve> valves) {
        return valves.stream().filter(valve -> valve.flowRate() > 0 || valve.id().equals("AA")).collect(toMap(Valve::id, v -> v));
    }

    private static Map<Route, Integer> calculateCosts(List<Valve> valves) {
        Map<Route, Integer> costs = new HashMap<>();

        for (Valve valve : valves) {
            valve.routes().forEach(target -> costs.put(new Route(valve.id(), target), 1));
        }

        for (Valve via : valves) {
            for (Valve from : valves) {
                for (Valve to : valves) {
                    if (from == to || from == via || to == via) {
                        continue;
                    }

                    Route part1 = new Route(from.id(), via.id());
                    Route part2 = new Route(via.id(), to.id());

                    if (costs.containsKey(part1) && costs.containsKey(part2)) {
                        Route direct = new Route(from.id(), to.id());

                        var viaCosts = costs.get(part1) + costs.get(part2);
                        if (viaCosts < costs.getOrDefault(direct, Integer.MAX_VALUE)) {
                            costs.put(direct, viaCosts);
                        }
                    }
                }
            }
        }

        var irrelevantValves = valves.stream().filter(valve -> valve.flowRate() == 0 && !valve.id().equals("AA")).map(Valve::id).toList();
        var irrelevantRoutes = costs.keySet().stream().filter(route -> irrelevantValves.contains(route.from) || irrelevantValves.contains(route.to)).toList();
        irrelevantRoutes.forEach(costs::remove);

        return costs;
    }


    public int maximumPressureRelease() {
        return maximumPressureRelease(new State(30, "AA", 0, new HashSet<>())).currentPressure;
    }

    private State maximumPressureRelease(State initialState) {
        LinkedList<State> todo = new LinkedList<>();
        todo.add(initialState);

        State max = null;

        while (!todo.isEmpty()) {
            var state = todo.poll();

            var newStates = costs.keySet().stream().filter(route -> route.from.equals(state.currentValveId))
                    .filter(route -> !state.visited.contains(route.to))
                    .filter(route -> costs.get(route) + 1 <= state.minutes)
                    .map(route -> state.moveToAndOpen(route.to(), costs.get(route), valves.get(route.to()).flowRate()))
                    .toList();

            if (newStates.size() == 0) {
                if (max == null || state.currentPressure > max.currentPressure) {
                    max = state;
                }
            }
            todo.addAll(newStates);
        }

        return max;
    }

    public int maximumPressureReleaseWithElephantHelp() {
        var targets = new HashSet<>(valves.keySet());
        targets.remove("AA");
        return Generator.combination(targets)
                .simple(targets.size() / 2)
                .stream()
                .parallel()
                .mapToInt(valvesToVisit -> {
                    var otherValvesToVisit = targets.stream().filter(not(valvesToVisit::contains)).collect(toSet());

                    var myValves = maximumPressureRelease(new State(26, "AA", 0, new HashSet<>(valvesToVisit)));
                    var elefantValves = maximumPressureRelease(new State(26, "AA", 0, new HashSet<>(otherValvesToVisit)));
                    return myValves.currentPressure + elefantValves.currentPressure;

                }).max().orElse(0);
    }

    private record Route(String from, String to) {

    }

    @Builder
    @RequiredArgsConstructor
    private static class State {

        private final int minutes;
        private final String currentValveId;
        private final int currentPressure;
        private final HashSet<String> visited;

        State moveToAndOpen(String moveTo, int costs, int targetFlowrate) {
            var visited = new HashSet<>(this.visited);
            visited.add(currentValveId);
            return State.builder()
                    .minutes(minutes - costs - 1)
                    .currentValveId(moveTo)
                    .currentPressure(currentPressure + targetFlowrate * (minutes - costs - 1))
                    .visited(visited)
                    .build();
        }
    }

}
