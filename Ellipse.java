package uwo.hci.sketchdroid;

import java.util.ArrayList;

import android.graphics.Point;

public class Ellipse extends Shape {

	private Point _vertexOne;
	private Point _vertexTwo;
	
	public Ellipse(Point vertexOne, Point vertexTwo)
	{
		super();
		
		_vertexOne = vertexOne;
		_vertexTwo = vertexTwo;
		
	}
	
	public Ellipse(Ellipse ellipse)
	{
		super();
		
		_vertexOne = new Point(ellipse.getVertexOne().x, ellipse.getVertexOne().y);
		_vertexTwo = new Point(ellipse.getVertexTwo().x, ellipse.getVertexTwo().y);
		SetColour(Integer.valueOf(ellipse.GetColour()));
	}
	
	public Point getVertexOne() {
		return _vertexOne;
	}



	public void setVertexOne(Point vertexOne) {
		this._vertexOne = vertexOne;
	}



	public Point getVertexTwo() {
		return _vertexTwo;
	}



	public void setVertexTwo(Point vertexTwo) {
		this._vertexTwo = vertexTwo;
	}
	
	@Override
	public ShapeType Type() {
		// TODO Auto-generated method stub
		return ShapeType.Ellipse;
	}

	@Override
	public void Move(int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		_vertexOne.x += deltaX;
		_vertexOne.y += deltaY;
		_vertexTwo.x += deltaX;
		_vertexTwo.y += deltaY;
	}

	@Override
	public boolean Intersects(Point p) {
		// TODO Auto-generated method stub
		
		if((p.x > Math.max(_vertexOne.x,_vertexTwo.x)) ||
				(p.y > Math.max(_vertexOne.y,_vertexTwo.y)) ||
				(p.x < Math.min(_vertexOne.x, _vertexTwo.x)) ||
				(p.y < Math.min(_vertexOne.y, _vertexTwo.y)))
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
		
		result.add(new Point((_vertexOne.x + _vertexTwo.x)/2,Math.max(_vertexOne.y, _vertexTwo.y)));
		result.add(new Point((_vertexOne.x + _vertexTwo.x)/2,Math.min(_vertexOne.y, _vertexTwo.y)));
		result.add(new Point(Math.min(_vertexOne.x, _vertexTwo.x),(_vertexOne.y + _vertexTwo.y)/2));
		result.add(new Point(Math.max(_vertexOne.x, _vertexTwo.x),(_vertexOne.y + _vertexTwo.y)/2));
		
		return result;
	}
	

}
