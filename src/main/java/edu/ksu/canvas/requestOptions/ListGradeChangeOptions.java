package edu.ksu.canvas.requestOptions;

public class ListGradeChangeOptions extends BaseOptions {

    private final String description;

    private ListGradeChangeOptions(final String idType, final String idValue) {
        description = idType + " " + idValue;
        addSingleItem(idType, idValue);
    }

    public String getDescription() {
        return description;
    }

    public static ListGradeChangeOptions forAssignment(final String assignmentId) {
        return new ListGradeChangeOptions("assignment_id", assignmentId);
    }

    public ListGradeChangeOptions startDate(final String startDate) {
        addSingleItem("start_date", startDate);
        return this;
    }

    public ListGradeChangeOptions endDate(final String endDate) {
        addSingleItem("end_date", endDate);
        return this;
    }
}
