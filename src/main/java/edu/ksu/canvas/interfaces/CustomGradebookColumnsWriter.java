package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.CustomColumn;

public interface CustomGradebookColumnsWriter extends CanvasWriter< CustomColumn, CustomGradebookColumnsWriter >
{
	public Optional< CustomColumn > createCustomGradebookColumn( String courseId, CustomColumn column ) throws IOException;
}
