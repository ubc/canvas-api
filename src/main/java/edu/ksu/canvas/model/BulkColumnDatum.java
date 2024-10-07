package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;

import java.io.Serializable;

/**
 * Class to represent a cell in a bulk custom gradebook update. See the <a href=
 * "https://canvas.instructure.com/doc/api/custom_gradebook_columns.html#method.custom_gradebook_column_data_api.bulk_update">Custom
 * Gradebook Columns API</a> documentation.
 */
@SuppressWarnings( "serial" )
public class BulkColumnDatum implements Serializable
{
	private Long columnId;
	private Long userId;
	private String content;

	public Long getColumnId( )
	{
		return columnId;
	}

	public void setColumnId( final Long columnId )
	{
		this.columnId = columnId;
	}

	public Long getUserId( )
	{
		return userId;
	}

	public void setUserId( final Long userId )
	{
		this.userId = userId;
	}

	@CanvasField( postKey = "content" )
	public String getContent( )
	{
		return content;
	}

	public void setContent( final String content )
	{
		this.content = content;
	}
}
