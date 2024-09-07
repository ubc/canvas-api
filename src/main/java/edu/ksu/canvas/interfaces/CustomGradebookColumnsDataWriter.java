package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ColumnDatum;

import java.io.IOException;
import java.util.Optional;

public interface CustomGradebookColumnsDataWriter extends CanvasWriter< ColumnDatum, CustomGradebookColumnsDataWriter >
{
	public Optional< ColumnDatum > updateColumnData( String courseId, String columnId, ColumnDatum columnDatum ) throws IOException;
}
