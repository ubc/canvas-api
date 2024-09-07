package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.*;

@CanvasObject( postKey = "column_data" )
public class ColumnDatum extends BaseCanvasModel implements Serializable
{
	public static final long serialVersionUID = 1L;

	private Long userId;
	private String content;

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
