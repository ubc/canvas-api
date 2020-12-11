package edu.ksu.canvas.model.audit;

import java.util.ArrayList;
import java.util.List;

public class GradeChangeWrapper {
    private List<GradeChangeEvent> events = new ArrayList<>();
    private GradeChangeLinked linked;

    public GradeChangeLinked getLinked() {
        return linked;
    }

    public void setLinked(final GradeChangeLinked linked) {
        this.linked = linked;
    }

    public List<GradeChangeEvent> getEvents() {
        return events;
    }

    public void setEvents(final List<GradeChangeEvent> events) {
        this.events = events;
    }

}
