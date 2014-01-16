package uwo.hci.sketchdroid;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class ShapeDrawer 
{	
	public void DrawShape(Canvas canvas, Shape s, Paint p)
	{
		switch(s.Type())
		{
		case Line:
			canvas.drawLine(((Line)s).get_startPoint().x, 
					((Line)s).get_startPoint().y,
					((Line)s).get_endPoint().x,
					((Line)s).get_endPoint().y,
					p);
			break;
			
		case Rectangle:
			canvas.drawRect(((Rectangle)s).getVertexOne().x,
					((Rectangle)s).getVertexOne().y,
					((Rectangle)s).getVertexTwo().x,
					((Rectangle)s).getVertexTwo().y, p);
			break;
		
		case Square:
			canvas.drawRect(((Square)s).getOrigin().x - (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().y + (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().x + (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().y - (int) (0.707 * ((Square)s).getRadius()),p);
			break;
			
		case Circle:
			canvas.drawCircle(((Circle)s).getOrigin().x,((Circle)s).getOrigin().y,
					((Circle)s).getRadius(),p);
			break;
			
		case Ellipse:
			RectF o = new RectF(((Ellipse)s).getVertexOne().x,
					((Ellipse)s).getVertexOne().y,
					((Ellipse)s).getVertexTwo().x,
					((Ellipse)s).getVertexTwo().y);
			
			canvas.drawOval(o, p);
			break;
			
		case Poly:
			ArrayList<Point> points = ((Poly)s).GetPoints();
			if(points.size() > 1)
			{
				for(int i = 1; i < points.size(); i++)
				{
					canvas.drawLine(points.get(i-1).x,points.get(i-1).y,
							points.get(i % points.size()).x,points.get(i % points.size()).y, p);
				}
			}
			break;
		
		}
		
	}
	
	public void DrawShape(Canvas canvas, Shape s, Paint p, Paint q)
	{
		if(s.GetColour() == null)
		{
			p.setColor(Color.BLACK);
		}
		else
		{
			p.setColor(s.GetColour());
		}
		
		switch(s.Type())
		{
		case Line:
			canvas.drawLine(((Line)s).get_startPoint().x, 
					((Line)s).get_startPoint().y,
					((Line)s).get_endPoint().x,
					((Line)s).get_endPoint().y,
					p);
			
			break;
			
		case Rectangle:
			canvas.drawRect(((Rectangle)s).getVertexOne().x,
					((Rectangle)s).getVertexOne().y,
					((Rectangle)s).getVertexTwo().x,
					((Rectangle)s).getVertexTwo().y, p);
			
			break;
		
		case Square:
			canvas.drawRect(((Square)s).getOrigin().x - (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().y + (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().x + (int) (0.707 * ((Square)s).getRadius()),
					((Square)s).getOrigin().y - (int) (0.707 * ((Square)s).getRadius()),p);
			
			break;
			
		case Circle:
			canvas.drawCircle(((Circle)s).getOrigin().x,((Circle)s).getOrigin().y,
					((Circle)s).getRadius(),p);
			
			break;
			
		case Ellipse:
			RectF o = new RectF(((Ellipse)s).getVertexOne().x,
					((Ellipse)s).getVertexOne().y,
					((Ellipse)s).getVertexTwo().x,
					((Ellipse)s).getVertexTwo().y);
			
			canvas.drawOval(o, p);
			
			break;
			
		case Poly:
			ArrayList<Point> points = ((Poly)s).GetPoints();
			
			if(points.size() > 1)
			{
				for(int i = 1; i < points.size(); i++)
				{
					canvas.drawLine(points.get(i-1).x,points.get(i-1).y,
							points.get(i).x,points.get(i).y, p);
				}
			}
			break;
		
		}
		
		if(s.Selected())
		{
			for(Point u : s.GetHandles())
			{
				canvas.drawPoint(u.x, u.y, q);
			}
		}
		
	}

}
