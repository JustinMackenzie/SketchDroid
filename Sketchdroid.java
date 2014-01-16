package uwo.hci.sketchdroid;

import com.buzzingandroid.ui.HSVColorPickerDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Sketchdroid extends Activity {
	
	private Controller _controller;
	private Model _model;
	private DrawView _drawView;
	
	HSVColorPickerDialog cpd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sketchdroid);
		
		_model = new Model();
		_drawView = new DrawView(this, _model);
		_controller = new Controller(_model, _drawView);
		_drawView.SetController(_controller);
		
		Initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sketchdroid, menu);
		return true;
	}
	
	private void Initialize()
	{
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.viewLayout);
		layout.addView(_drawView, 0);
		
		
		((Button)findViewById(R.id.lineButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.rectangleButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.squareButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.circleButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.ellipseButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.selectButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.polyButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.cutButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.copyButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.pasteButton)).setOnClickListener(_controller);
		((Button)findViewById(R.id.groupButton)).setOnClickListener(_controller);
		
		cpd = new HSVColorPickerDialog(this, 0xFF4488CC, _controller);
		cpd.setTitle("Choose the colour");
		
		findViewById(R.id.colourButton).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				cpd.show();	
			}
		});
		
		cpd = new HSVColorPickerDialog(this, 0xFF4488CC, _controller);
		cpd.setTitle("Choose the colour");
	}

}
