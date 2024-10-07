package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.BulkColumnData;
import edu.ksu.canvas.model.ColumnDatum;
import edu.ksu.canvas.model.Progress;

import java.io.IOException;
import java.util.Optional;

public interface CustomGradebookColumnsDataWriter extends CanvasWriter< ColumnDatum, CustomGradebookColumnsDataWriter >
{
	public Optional< ColumnDatum > updateColumnData( String courseId, String columnId, ColumnDatum columnDatum ) throws IOException;

	public Optional< Progress > bulkUpdateColumnData( String courseId, BulkColumnData data ) throws IOException;
}
