package uwo.hci.sketchdroid;

import java.util.ArrayList;

public class Model {
	
	/* Used to store shapes. */
	private ShapeManager _shapeManager;
	
	/* Used to store groupings. */
	private ArrayList<Group> _groupManager;
	
	/* Used to store shapes for copy/cut and paste operations. */
	private ArrayList<Shape> _shapeBuffer;
	
	/* Used to store which shapes are selected. */
	private ArrayList<Shape> _selection;
	
	private Shape _drawingShape;
	
	public Model()
	{
		_shapeManager = new ShapeManager();
		_groupManager = new ArrayList<Group>();
		_shapeBuffer = new ArrayList<Shape>();
		_selection = new ArrayList<Shape>();
		_drawingShape = null;
	}
	
	public void SetDrawingShape(Shape s)
	{
		_drawingShape = s;
	}
	
	public void RemoveShape(Shape s)
	{
		_shapeManager.RemoveShape(s);
	}
	
	public Shape GetDrawingShape()
	{
		return _drawingShape;
	}
	
	public void AddShape(Shape s)
	{
		_shapeManager.AddShape(s);
	}

	public ArrayList<Shape> GetAllShapes()
	{
		return _shapeManager.GetAllShapes();
	}
	
	public boolean AllSameGroup()
	{
		Group g = _selection.get(0).GetGroup();
		
		if(g == null)
		{
			return false;
		}
		
		for(Shape s : _selection)
		{
			if(s.GetGroup() != g)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void Group()
	{
		Group group = new Group(_selection);
		
		for(Shape s : _selection)
		{
			/*if(s.GetGroup() != null)
			{
				s.GetGroup().RemoveShape(s);
			}*/
			s.SetGroup(group);
		}
		
		_groupManager.add(group);
	}
	
	public void UnGroup()
	{
		Group group = _selection.get(0).GetGroup();
		
		for(Shape s : group.GetShapes())
		{
			s.SetGroup(null);
		}
		_groupManager.remove(group);
		_selection.clear();
	}
	
	public void AddShapeToSelection(Shape s)
	{
		if(!_selection.contains(s))
		{
			s.SetSelected(true);
			_selection.add(s);
			
			Group g = s.GetGroup();
			
			if(g != null)
			{
				for(Shape t : g.GetShapes())
				{
					if(!_selection.contains(t))
					{
						t.SetSelected(true);
						_selection.add(t);
					}
				}
			}
		}
	}
	
	public void Cut()
	{
		_shapeBuffer.clear();
		for(Shape s : _selection)
		{
			_shapeBuffer.add(s);
			_shapeManager.RemoveShape(s);
			s.SetSelected(false);
		}
		_selection.clear();
	}
	
	public void Copy()
	{	
		_shapeBuffer.clear();
		for(Shape s : _selection)
		{
			_shapeBuffer.add(s);
			s.SetSelected(false);
		}
		
		_selection.clear();
		
	}
	
	public void Paste()
	{
		if(_shapeBuffer == null)
		{
			return;
		}
		
		for(Shape s : _shapeBuffer)
		{
			Shape shape = null;
			switch(s.Type())
			{
			case Circle:
				shape = new Circle((Circle)s);
				break;
				
			case Line:
				shape = new Line((Line)s);
				break;
				
			case Rectangle:
				shape = new Rectangle((Rectangle)s);
				break;
				
			case Square:
				shape = new Square((Square)s);
				break;
				
			case Ellipse:
				shape = new Ellipse((Ellipse)s);
				break;
				
			case Poly:
				shape = new Poly((Poly)s);
				break;
			}
			
			_shapeManager.AddShape(shape);
		}
		
	}
	
	public void ClearSelection()
	{
		for(Shape s : _selection)
		{
			s.SetSelected(false);
		}
		_selection.clear();
	}
	
	public ArrayList<Shape> GetAllSelection()
	{
		return _selection;
	}
	
	
	

}
