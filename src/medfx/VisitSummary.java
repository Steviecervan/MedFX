package medfx;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VisitSummary {
    private List<Visits> visits;

    public VisitSummary(List<Visits> visits) {
        this.visits = visits;
    }

    // Get all health concerns from the visits
    public List<String> getAllHealthConcerns() {
        return visits.stream()
                     .map(Visits::getHealthConcerns)
                     .collect(Collectors.toList());
    }

    // Get a summary of all prescriptions across the visits
    public List<Medication> getAllPrescriptions() {
        return visits.stream()
                     .flatMap(visit -> visit.getPrescriptions().stream())
                     .distinct()
                     .collect(Collectors.toList());
    }

    // Add a visit to the summary
    public void addVisit(Visits visit) {
        this.visits.add(visit);
    }

    // Remove a visit from the summary
    public void removeVisit(Visits visit) {
        this.visits.remove(visit);
    }

    // Get the number of visits
    public int getNumberOfVisits() {
        return visits.size();
    }

    // Get the latest visit
    public Visits getLatestVisit() {
        return visits.stream()
                     .max((visit1, visit2) -> visit1.getTimestamp().compareTo(visit2.getTimestamp()))
                     .orElse(null);
    }

    // Get the earliest visit
    public Visits getEarliestVisit() {
        return visits.stream()
                     .min((visit1, visit2) -> visit1.getTimestamp().compareTo(visit2.getTimestamp()))
                     .orElse(null);
    }

    // Get visits within a specific date range
    public List<Visits> getVisitsWithinDateRange(Date start, Date end) {
        return visits.stream()
                     .filter(visit -> !visit.getTimestamp().before(start) && !visit.getTimestamp().after(end))
                     .collect(Collectors.toList());
    }

    // Get a map of health concerns and their frequencies
    public Map<String, Long> getHealthConcernFrequencies() {
        return visits.stream()
                     .map(Visits::getHealthConcerns)
                     .flatMap(healthConcerns -> List.of(healthConcerns.split("\\s*,\\s*")).stream())
                     .collect(Collectors.groupingBy(hc -> hc, Collectors.counting()));
    }

    // Getters and Setters
    public List<Visits> getVisits() {
        return visits;
    }

    public void setVisits(List<Visits> visits) {
        this.visits = visits;
    }

    // Additional methods to generate specific reports or summaries as required
}
