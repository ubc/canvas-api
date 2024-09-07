package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.CustomColumn;

public interface CustomGradebookColumnsReader extends CanvasReader< CustomColumn, CustomGradebookColumnsReader >
{
	public List< CustomColumn > listCustomGradebookColumns( String courseId, boolean includeHidden ) throws IOException;
}
