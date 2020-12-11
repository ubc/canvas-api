package edu.ksu.canvas.model.audit;

import java.util.Date;

public class GradeChange {
    private String id;
    private Date createdAt;
    private String eventType;
    private String gradeBefore;
    private String gradeAfter;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public String getGradeBefore() {
        return gradeBefore;
    }

    public void setGradeBefore(final String gradeBefore) {
        this.gradeBefore = gradeBefore;
    }

    public String getGradeAfter() {
        return gradeAfter;
    }

    public void setGradeAfter(final String gradeAfter) {
        this.gradeAfter = gradeAfter;
    }

    @Override
    public String toString() {
        return "GradeChange{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", eventType='" + eventType + '\'' +
                ", gradeBefore='" + gradeBefore + '\'' +
                ", gradeAfter='" + gradeAfter + '\'' +
                '}';
    }
}
