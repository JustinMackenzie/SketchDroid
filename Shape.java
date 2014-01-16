package uwo.hci.sketchdroid;

import java.util.ArrayList;

import android.graphics.Point;

public abstract class Shape {
	
	/* The group that the shape belongs to. */
	private Group _group;
	
	/* Determines whether the shape is selected or not. */
	private boolean _selected;
	
	/* The colour of this shape. */
	private Integer _colour;
	
	
	public Shape()
	{
		_group = null;
		_selected = false;
	}
	
	public abstract ShapeType Type();
	public abstract void Move(int deltaX, int deltaY);
	public abstract boolean Intersects(Point p);
	public abstract ArrayList<Point> GetHandles();
	
	public Integer GetColour()
	{
		return _colour;
	}
	
	public void SetColour(Integer colour)
	{
		_colour = colour;
	}
	
	public Group GetGroup()
	{
		return _group;
	}
	public void SetGroup(Group group)
	{
		_group = group;
	}
	
	public boolean Selected()
	{
		return _selected;
	}
	
	public void SetSelected(boolean selected)
	{
		_selected = selected;
	}

}
