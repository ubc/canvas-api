package edu.ksu.canvas.model.audit;

public class GradeChangeLinks {
    private String course;
    private String student;
    private String grader;
    private String pageView;
    private String assignment;

    public String getCourse() {
        return course;
    }

    public void setCourse(final String course) {
        this.course = course;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(final String student) {
        this.student = student;
    }

    public String getGrader() {
        return grader;
    }

    public void setGrader(final String grader) {
        this.grader = grader;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(final String pageView) {
        this.pageView = pageView;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(final String assignment) {
        this.assignment = assignment;
    }
}
