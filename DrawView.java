package uwo.hci.sketchdroid;

import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

public class DrawView extends View {
	
	private Controller _controller;
	private Model _model;
	private ShapeDrawer _drawer;
	
	private Paint _finalPaint;
	private Paint _drawingPaint;
	private Paint _anchorPaint;
	
	public DrawView(Context context, Model model)
	{
		super(context);
		_model = model;
		_drawer = new ShapeDrawer();
		_finalPaint = new Paint();
		_finalPaint.setStyle(Style.STROKE);
		_finalPaint.setStrokeWidth(10);
		_drawingPaint = new Paint();
		_drawingPaint.setColor(Color.GRAY);
		_drawingPaint.setStyle(Style.STROKE);
		_anchorPaint = new Paint();
		_anchorPaint.setColor(Color.RED);
		_anchorPaint.setStyle(Style.STROKE);
		_anchorPaint.setStrokeWidth((int)(_finalPaint.getStrokeWidth() * 1.2));
		
	}
	
	public void SetController(Controller controller)
	{
		_controller = controller;
	}
	
	public DrawView(Context context)
	{
		super(context);
	}
	
	public DrawView(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		return _controller.onTouchEvent(event);
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		if(_model.GetDrawingShape() != null)
		{
			_drawer.DrawShape(canvas, _model.GetDrawingShape(), _drawingPaint);
			
		}
		
		for(Shape s : _model.GetAllShapes())
		{
			_drawer.DrawShape(canvas, s, _finalPaint, _anchorPaint);
		}
	}

}
