package uwo.hci.sketchdroid;

import android.graphics.Point;
import java.util.ArrayList;

public class Poly extends Shape {

	private ArrayList<Point> _points;
	
	public Poly(ArrayList<Point> points)
	{
		_points = points;
	}
	
	public Poly()
	{
		_points = new ArrayList<Point>();
	}
	
	public Poly(Poly poly)
	{
		_points = new ArrayList<Point>();
		
		for(Point p : poly.GetPoints())
		{
			_points.add(new Point(p.x, p.y));
		}
		SetColour(Integer.valueOf(poly.GetColour()));
	}
	
	public void AddPoint(Point p)
	{
		_points.add(p);
	}
	
	public ArrayList<Point> GetPoints()
	{
		return _points;
	}
	
	@Override
	public ShapeType Type() {
		// TODO Auto-generated method stub
		return ShapeType.Poly;
	}

	@Override
	public void Move(int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		
		for(Point p : _points)
		{
			p.x += deltaX;
			p.y += deltaY;
		}
		
	}

	@Override
	public boolean Intersects(Point p) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < _points.size(); i++)
		{
			Point p1 = _points.get(i % _points.size());
			Point p2 = _points.get((i + 1) % _points.size());
			
			if((p.x > Math.max(p2.x,p1.x)) ||
					(p.y > Math.max(p2.y,p1.y)) ||
					(p.x < Math.min(p2.x, p1.x)) ||
					(p.y < Math.min(p2.y, p1.y)))
			{
				continue;
			}
			
			float m = (1.0f * p2.y - p1.y) / (1.0f * p2.x - p1.x);
			
			int b = (int)(p1.y - m * p1.x);
			
			if (Math.abs(p.y - (m * p.x + b)) < 10)
			{
				return true;
			}
			else
			{
				continue;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Point> GetHandles() {
		// TODO Auto-generated method stub
		
		ArrayList<Point> result = new ArrayList<Point>();
		
		for(Point p : _points)
		{
			result.add(p);
		}
		
		return result;
	}

}
