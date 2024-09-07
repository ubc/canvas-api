package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.*;

@CanvasObject( postKey = "column" )
public class CustomColumn extends BaseCanvasModel implements Serializable
{
	public static final long serialVersionUID = 1L;

	private Long id;
	private Boolean teacherNotes;
	private String title;
	private Long position;
	private Boolean hidden;
	private Boolean readOnly;

	public Long getId( )
	{
		return id;
	}

	public void setId( final Long id )
	{
		this.id = id;
	}

	@CanvasField( postKey = "teacher_notes" )
	public Boolean getTeacherNotes( )
	{
		return teacherNotes;
	}

	public void setTeacherNotes( final Boolean teacherNotes )
	{
		this.teacherNotes = teacherNotes;
	}

	@CanvasField( postKey = "title" )
	public String getTitle( )
	{
		return title;
	}

	public void setTitle( final String title )
	{
		this.title = title;
	}

	@CanvasField( postKey = "position" )
	public Long getPosition( )
	{
		return position;
	}

	public void setPosition( final Long position )
	{
		this.position = position;
	}

	@CanvasField( postKey = "hidden" )
	public Boolean getHidden( )
	{
		return hidden;
	}

	public void setHidden( final Boolean hidden )
	{
		this.hidden = hidden;
	}

	@CanvasField( postKey = "read_only" )
	public Boolean getReadOnly( )
	{
		return readOnly;
	}

	public void setReadOnly( final Boolean readOnly )
	{
		this.readOnly = readOnly;
	}
}
