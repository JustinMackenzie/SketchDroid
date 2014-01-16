package uwo.hci.sketchdroid;

import java.util.ArrayList;

import android.graphics.Point;

public class Line extends Shape {
	
	private Point _startPoint;
	private Point _endPoint;
	
	public Line(Point startPoint, Point endPoint)
	{
		super();
		
		_startPoint = startPoint;
		_endPoint = endPoint;
	}
	
	public Line(Line line)
	{
		super();
		_startPoint = new Point(line.get_startPoint().x, line.get_startPoint().y);
		_endPoint = new Point(line.get_endPoint().x, line.get_endPoint().y);
		SetColour(Integer.valueOf(line.GetColour()));
	}

	public Point get_startPoint() {
		return _startPoint;
	}



	public void set_startPoint(Point _startPoint) {
		this._startPoint = _startPoint;
	}



	public Point get_endPoint() {
		return _endPoint;
	}



	public void set_endPoint(Point _endPoint) {
		this._endPoint = _endPoint;
	}



	@Override
	public ShapeType Type() {
		// TODO Auto-generated method stub
		return ShapeType.Line;
	}



	@Override
	public void Move(int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		_startPoint.x += deltaX;
		_startPoint.y += deltaY;
		
		_endPoint.x += deltaX;
		_endPoint.y += deltaY;
		
	}



	@Override
	public boolean Intersects(Point p) {
		// TODO Auto-generated method stub
		
		if((p.x > Math.max(_endPoint.x,_startPoint.x)) ||
				(p.y > Math.max(_endPoint.y,_startPoint.y)) ||
				(p.x < Math.min(_endPoint.x, _startPoint.x)) ||
				(p.y < Math.min(_endPoint.y, _startPoint.y)))
		{
			return false;
		}
		
		float m = (1.0f * _endPoint.y - _startPoint.y) / (1.0f * _endPoint.x - _startPoint.x);
		
		int b = (int)(_startPoint.y - m * _startPoint.x);
		
		if (Math.abs(p.y - (m * p.x + b)) < 10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ArrayList<Point> GetHandles() {
		// TODO Auto-generated method stub
		ArrayList<Point> result = new ArrayList<Point>();
		
		result.add(_startPoint);
		result.add(_endPoint);
		
		return result;
	}
}
