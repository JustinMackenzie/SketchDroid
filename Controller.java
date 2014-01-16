package uwo.hci.sketchdroid;

import java.util.ArrayList;

import com.buzzingandroid.ui.HSVColorPickerDialog.OnColorSelectedListener;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class Controller implements OnClickListener, OnColorSelectedListener {
	
	private Model _model;
	private DrawView _view;
	private Tool _tool;
	private Point p0;
	private Point p1;
	private ArrayList<Point> _pointList;
	private Integer _currentColour;

	public Controller (Model model, DrawView view)
	{
		_model = model;
		_view = view;
		_tool = Tool.Select;
	}
	
	private Shape CreateShape(Tool tool)
	{
		Shape result = null;
		
		switch(tool)
		{
		
		case Line:
			result = new Line(p0, p1);
			break;
			
		case Rectangle:
			result = new Rectangle(p0, p1);
			break;
			
		case Square:
			result = new Square(p0,(float)Math.sqrt(Math.pow(p1.x - p0.x, 2) + Math.pow(p1.y - p0.y, 2)));
			break;
			
		case Ellipse:
			result = new Ellipse(p0, p1);
			break;
			
		case Circle:
			result = new Circle(p0,(float)Math.sqrt(Math.pow(p1.x - p0.x, 2) + Math.pow(p1.y - p0.y, 2)));
			break;
			
		case PolyLine:
			result = new Poly((Poly)_model.GetDrawingShape());
			break;
			
		}
		
		return result;
	}
	
	private void AddShape(Shape s)
	{
		s.SetColour(_currentColour);
		_model.AddShape(s);
	}

	public void onClick(View view) 
	{
		if(_model.GetDrawingShape() != null)
		{
			return;
		}
		
		switch(view.getId())
		{
		case R.id.lineButton:
			_tool = Tool.Line;
			break;
			
		case R.id.rectangleButton:
			_tool = Tool.Rectangle;
			break;
			
		case R.id.squareButton:
			_tool = Tool.Square;
			break;
			
		case R.id.circleButton:
			_tool = Tool.Circle;
			break;
			
		case R.id.ellipseButton:
			_tool = Tool.Ellipse;
			break;
			
		case R.id.selectButton:
			_tool = Tool.Select;
			break;
			
		case R.id.polyButton:
			_tool = Tool.PolyLine;
			break;
			
		case R.id.cutButton:
			_model.Cut();
			_view.invalidate();
			break;
			
		case R.id.copyButton:
			_model.Copy();
			break;
			
		case R.id.pasteButton:
			_model.Paste();
			_view.invalidate();
			break;
			
		case R.id.groupButton:
			
			if(_model.AllSameGroup())
			{
				_model.UnGroup();
				_view.invalidate();
			}
			else
			{
				_model.Group();
			}
			break;
		}
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		switch(event.getAction())
		{
		
		case MotionEvent.ACTION_DOWN:
			
			if(_tool == Tool.PolyLine)
			{
				if(_model.GetDrawingShape() == null)
				{
					//_pointList = new ArrayList<Point>();
					_model.SetDrawingShape(new Poly());
				}
				
				if(((Poly)_model.GetDrawingShape()).GetPoints().size() > 0)
				{
					if((Math.abs(x - ((Poly)_model.GetDrawingShape()).GetPoints().get(0).x) < 10) 
							&& (Math.abs(x - ((Poly)_model.GetDrawingShape()).GetPoints().get(0).x) < 10)
							&& (((Poly)_model.GetDrawingShape()).GetPoints().size() > 2))
					{
						((Poly)_model.GetDrawingShape()).AddPoint(new Point(((Poly)_model.GetDrawingShape()).GetPoints().get(0).x, 
								((Poly)_model.GetDrawingShape()).GetPoints().get(0).y));
						AddShape(CreateShape(_tool));
						_model.SetDrawingShape(null);
						
					}
					else
					{
						((Poly)_model.GetDrawingShape()).AddPoint(new Point(x,y));
					}
				}
				else
				{
					((Poly)_model.GetDrawingShape()).AddPoint(new Point(x,y));
					
				}
				_view.invalidate();
				
			}
			else
			{
				p0 = new Point(x,y);
			}
			
			if(_tool == Tool.Select)
			{
				boolean hit = false;
				
				for(Shape s : _model.GetAllShapes())
				{
					if(s.Intersects(p0))
					{
						_model.AddShapeToSelection(s);
						hit = true;
						break;
					}
				}
				
				if(!hit)
				{
					_model.ClearSelection();
				}
				_view.invalidate();
			}
			
			/*if(_tool == Tool.Erase)
			{
				boolean hit = false;
				
				for(Shape s : _model.GetAllShapes())
				{
					if(s.Intersects(p0))
					{
						_model.RemoveShape(s);
						hit = true;
						break;
					}
				}
				
				if(hit)
				{
					_view.invalidate();
				}
			}*/
			break;
			
		case MotionEvent.ACTION_UP:
			
			if(!(_tool == Tool.Select) && !(_tool == Tool.PolyLine))
			{
				_model.SetDrawingShape(null);
				AddShape(CreateShape(_tool));
				_view.invalidate();
			}
			
			break;
			
		case MotionEvent.ACTION_MOVE:
			
			p1 = new Point(x,y);
			
			if(_tool == Tool.Select)
			{
				for(Shape s : _model.GetAllSelection())
				{
					s.Move(p1.x - p0.x, p1.y - p0.y);
				}
				
				p0 = p1;
			}
			else
			{	
				_model.SetDrawingShape(CreateShape(_tool));
			}
			
			_view.invalidate();
			
			break;
			
		}
		
		return true;
	}

	@Override
	public void colorSelected(Integer color) {
		// TODO Auto-generated method stub
		
		_currentColour = color;
		
		for(Shape s : _model.GetAllSelection())
		{
			s.SetColour(_currentColour);
		}
		
		_view.invalidate();
		
	}
}
