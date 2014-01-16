package uwo.hci.sketchdroid;

import java.util.ArrayList;

import android.graphics.Point;

public class Square extends Shape {

	private Point _origin;
	private float _radius;
	
	public Square(Point origin, float radius)
	{
		super();
		
		_origin = origin;
		_radius = radius;
	}
	
	public Square(Square square)
	{
		super();
		_origin = new Point(square.getOrigin().x, square.getOrigin().y);
		_radius = square.getRadius();
		SetColour(Integer.valueOf(square.GetColour()));
	}
	
	
	public Point getOrigin() {
		return _origin;
	}



	public void setOrigin(Point origin) {
		this._origin = origin;
	}



	public float getRadius() {
		return _radius;
	}



	public void setRadius(float radius) {
		this._radius = radius;
	}



	@Override
	public ShapeType Type() {
		// TODO Auto-generated method stub
		return ShapeType.Square;
	}



	@Override
	public void Move(int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		_origin.x += deltaX;
		_origin.y += deltaY;
		
	}



	@Override
	public boolean Intersects(Point p) {
		// TODO Auto-generated method stub
		
		if((p.x > (_origin.x + 0.707 * _radius)) ||
				(p.y > (_origin.y + 0.707 * _radius)) ||
				(p.x < (_origin.x - 0.707 * _radius)) ||
				(p.y < (_origin.y - 0.707 * _radius)))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public ArrayList<Point> GetHandles() {
		// TODO Auto-generated method stub
		ArrayList<Point> result = new ArrayList<Point>();
		
		result.add(new Point((int)(_origin.x - 0.707 *_radius), (int)(_origin.y + 0.707 *_radius)));
		result.add(new Point((int)(_origin.x + 0.707 *_radius), (int)(_origin.y + 0.707 *_radius)));
		result.add(new Point((int)(_origin.x - 0.707 *_radius), (int)(_origin.y - 0.707 *_radius)));
		result.add(new Point((int)(_origin.x + 0.707 *_radius), (int)(_origin.y - 0.707 *_radius)));
		
		return result;
	}


}
