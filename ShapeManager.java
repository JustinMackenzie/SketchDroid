package uwo.hci.sketchdroid;

import java.util.ArrayList;

public class ShapeManager {
	
	private ArrayList<Shape> _shapes;
	
	public ShapeManager()
	{
		_shapes = new ArrayList<Shape>();
	}
	
	public void AddShape(Shape s)
	{
		_shapes.add(s);
	}
	
	public void RemoveShape(Shape s)
	{
		_shapes.remove(s);
	}
	
	public ArrayList<Shape> GetAllShapes()
	{
		return _shapes;
	}

}
