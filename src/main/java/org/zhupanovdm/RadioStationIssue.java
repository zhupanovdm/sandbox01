package org.zhupanovdm;

import java.util.*;

public class RadioStationIssue {
    private final Map<String, Set<String>> stations = new HashMap<>();

    public void addStation(String station, String ...coverage) {
        stations.put(station, new HashSet<>(Arrays.asList(coverage)));
    }

    public Collection<String> getStations(String ...coverage) {
        Collection<String> result = new LinkedList<>();
        Set<String> uncovered = new HashSet<>(Arrays.asList(coverage));
        Set<String> availableStations = new HashSet<>(stations.keySet());

        while (!uncovered.isEmpty() && !availableStations.isEmpty()) {
            int max = 0;
            String suitable = null;
            for (String station : availableStations) {
                Set<String> stationCoverage = stations.get(station);
                int covers = 0;
                for (String region : stationCoverage)
                    covers += uncovered.contains(region) ? 1 : 0;
                if (covers > max) {
                    max = covers;
                    suitable = station;
                }
            }
            if (suitable == null)
                break;
            result.add(suitable);
            availableStations.remove(suitable);
            uncovered.removeAll(stations.get(suitable));
        }
        return result;
    }

}
