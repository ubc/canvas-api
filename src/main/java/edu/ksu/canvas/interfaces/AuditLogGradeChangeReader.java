package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.audit.GradeChange;
import edu.ksu.canvas.requestOptions.ListGradeChangeOptions;

import java.io.IOException;
import java.util.List;

public interface AuditLogGradeChangeReader extends CanvasReader<GradeChange, AuditLogGradeChangeReader> {
    List<GradeChange> listGradeChanges(final ListGradeChangeOptions options) throws IOException;
}
