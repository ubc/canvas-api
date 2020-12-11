package edu.ksu.canvas.model.audit;

import java.util.ArrayList;
import java.util.List;

public class GradeChangeWrapper {
    private List<GradeChange> events = new ArrayList<>();

    public List<GradeChange> getEvents() {
        return events;
    }

    public void setEvents(final List<GradeChange> events) {
        this.events = events;
    }
}
