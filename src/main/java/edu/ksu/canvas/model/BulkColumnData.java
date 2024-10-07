package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;

import java.io.Serializable;
import java.util.List;

/**
 * Class to represent a cell in a bulk custom gradebook update. See the <a href=
 * "https://canvas.instructure.com/doc/api/custom_gradebook_columns.html#method.custom_gradebook_column_data_api.bulk_update">Custom
 * Gradebook Columns API</a> documentation.
 */
@SuppressWarnings( "serial" )
public class BulkColumnData extends BaseCanvasModel implements Serializable
{
	private List< BulkColumnDatum > columnData;

	@CanvasField( postKey = "column_data" )
	public List< BulkColumnDatum > getColumnData( )
	{
		return columnData;
	}

	public void setColumnData( final List< BulkColumnDatum > columnData )
	{
		this.columnData = columnData;
	}
}
