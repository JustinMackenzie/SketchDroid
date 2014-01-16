package uwo.hci.sketchdroid;

import java.util.ArrayList;

public class Group 
{
	private ArrayList<Shape> _shapes;
	
	public Group(ArrayList<Shape> shapes)
	{
		_shapes = new ArrayList<Shape>(shapes);
	}
	
	public ArrayList<Shape> GetShapes()
	{
		return _shapes;
	}
	
	public void RemoveShape(Shape s)
	{
		_shapes.remove(s);
	}
	
	public boolean isEmpty()
	{
		return _shapes.isEmpty();
	}
}
