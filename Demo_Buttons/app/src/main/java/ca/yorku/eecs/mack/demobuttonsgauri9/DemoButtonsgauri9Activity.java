package ca.yorku.eecs.mack.demobuttonsgauri9;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Demo_Android - with modifications by...
 *
 * Login ID - gauri9
 * Student ID - 212489126
 * Last name - Wahi
 * First name(s) - Gauri
 */

public class DemoButtonsgauri9Activity extends Activity implements View.OnTouchListener
{

	//these are used for savedInstanceState
	private final static String MYDEBUG = "MYDEBUG";
	private final static String BACK = "BACK";
	private final static String CLICK = "CLICK";
	private final static String TOGGLE = "TOGGLE";
	private final static String RADIO = "RADIO";
	private final static String CHECK = "CHECK";
	private final static String SM = "SMILE";
	private final static String COLOR = "COLOR";// for Log.i messages

	Button b;
	CheckBox cb;
	RadioButton rb1, rb2, rb3;
	ToggleButton tb;
	ImageButton backspaceButton, smileyButton;
	TextView buttonClickStatus, checkBoxClickStatus, radioButtonClickStatus, toggleButtonClickStatus,
			backspaceButtonClickStatus, smileyButtonStatus;
	Button exitButton;

	String buttonClickString;
	String backspaceString;
	String smileyButtonText;
	CharSequence radioButton;
	CharSequence toggle;
	CharSequence cc;
	CharSequence smiles;
	CharSequence bspc;

	int color;
	boolean checkStatus;
	boolean rb1Status, rb2Status, rb3Status;
	boolean tbStatus;

	// called when the activity is first created
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String s = "(savedInstanceState bundle is ";
		if(savedInstanceState == null)
		{
			s = s + "null)";
		}
		else
		{
			s = s + "NOT null)";

		}
		Log.i(MYDEBUG, "onCreate!" + s);
		init();
	}

	private void init()
	{
		b = (Button)findViewById(R.id.button);
		cb = (CheckBox)findViewById(R.id.checkbox);
		rb1 = (RadioButton)findViewById(R.id.radiobutton1);
		rb2 = (RadioButton)findViewById(R.id.radiobutton2);
		rb3 = (RadioButton)findViewById(R.id.radiobutton3);
		rb1.toggle();
		tb = (ToggleButton)findViewById(R.id.togglebutton);
		backspaceButton = (ImageButton)findViewById(R.id.backspacebutton);
		smileyButton = (ImageButton) findViewById(R.id.smileyButton);
		// removed the exit button exitButton = (Button)findViewById(R.id.exitbutton);
		//removed the exit button in string.xml as well
		buttonClickStatus = (TextView)findViewById(R.id.buttonclickstatus);
		checkBoxClickStatus = (TextView)findViewById(R.id.checkboxclickstatus);
		radioButtonClickStatus = (TextView)findViewById(R.id.radiobuttonclickstatus);
		toggleButtonClickStatus = (TextView)findViewById(R.id.togglebuttonclickstatus);
		backspaceButtonClickStatus = (TextView)findViewById(R.id.backspacebuttonclickstatus);
		smileyButtonStatus = (TextView) findViewById(R.id.smileButtonClickStatus);

		buttonClickString = "";
		backspaceString = "";
		smileyButtonText = "";
		//we must set the on touch listener outside buttonclicks so it works on the first click
		smileyButton.setOnTouchListener(this);

		buttonClickStatus.setText(buttonClickString);
		checkBoxClickStatus.setText(R.string.unchecked);
		radioButtonClickStatus.setText(R.string.red);
		radioButtonClickStatus.setTextColor(Color.RED);
		toggleButtonClickStatus.setText(R.string.off);
	}


	// handle button clicks
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void buttonClick(View v)
	{
		// plain button
		if (v == b)
		{
			buttonClickString += ".";
			buttonClickStatus.setText(buttonClickString);
		}

		// checkbox
		else if (v == cb)
		{
			if (cb.isChecked())
			{
				cb.setChecked(true);
				checkBoxClickStatus.setText(R.string.checked);
			} else
			{
				cb.setChecked(false);
				checkBoxClickStatus.setText(R.string.unchecked);
			}
		}

		// radio button #1 (RED)
		else if (v == rb1)
		{
			rb1.setChecked(true);
			radioButtonClickStatus.setText(R.string.red);
			radioButtonClickStatus.setTextColor(Color.RED);
		}

		// radio button #2 (GREEN)
		else if (v == rb2)
		{
			rb2.setChecked(true);
			radioButtonClickStatus.setText(R.string.green);
			radioButtonClickStatus.setTextColor(Color.GREEN);
		}

		// radio button #3 (BLUE)
		else if (v == rb3)
		{
			rb3.setChecked(true);
			radioButtonClickStatus.setText(R.string.blue);
			radioButtonClickStatus.setTextColor(Color.BLUE);
		}

		// toggle button
		else if (v == tb)
		{
			tb.setActivated(tb.isChecked());
			if (tb.isChecked())
				toggleButtonClickStatus.setText(R.string.on);
			else
				toggleButtonClickStatus.setText(R.string.off);
		}

		// backspace button
		else if (v == backspaceButton)
		{
			backspaceString += "BK ";
			backspaceButtonClickStatus.setText(backspaceString);
		}
		//smiley button
		else if(v == smileyButton)
		{

			smileyButtonText += "☺";
			smileyButtonStatus.setText(smileyButtonText);

		}

		// exit button
		else if (v == exitButton)
		{
			this.finish();
		}


	}
	//created a random color generator so whenever you click on the button, then it will select a new Integer and give you a different color
	//evrey time the smiley Button is clicked
	public int getRandomColor(){
		Random rnd = new Random();
		return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		cc = buttonClickStatus.getText();
		bspc = backspaceButtonClickStatus.getText();

		savedInstanceState.putCharSequence(BACK, bspc);
		savedInstanceState.putCharSequence(CLICK, cc);
		if(cb.isChecked())
		{
			savedInstanceState.putString(CHECK, "Checked");
		}
		else
		{
			savedInstanceState.putString(CHECK, "Unchecked");
		}
		//for radio buttons
		//get text
		radioButton = radioButtonClickStatus.getText();
		savedInstanceState.putCharSequence(RADIO, radioButton);
		//for toggle button
		//get text
		toggle = toggleButtonClickStatus.getText();
		savedInstanceState.putCharSequence(TOGGLE, toggle);
		smiles = smileyButtonStatus.getText();
		savedInstanceState.putCharSequence(SM, smiles);

		//color = ((ColorDrawable) smileyButton.getBackground()).getColor();
		//savedInstanceState.putInt(COLOR, color);


	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);

		//for the two strings I needed to set the text for the status as well as to append the other strings when the button is clicked
		backspaceButtonClickStatus.setText(savedInstanceState.getString(BACK));
		smileyButtonStatus.setText(savedInstanceState.getString(SM));
		//smileyButton.setBackgroundColor(savedInstanceState.getInt(COLOR));
		buttonClickStatus.setText(savedInstanceState.getString(CLICK));
		backspaceString = savedInstanceState.getString(BACK);
		buttonClickString = savedInstanceState.getString(CLICK);
		smileyButtonText = savedInstanceState.getString(SM);
		checkBoxClickStatus.setText(savedInstanceState.getString(CHECK));
		CharSequence r = savedInstanceState.getString(RADIO);
		if(r.equals("Red"))
		{
			radioButtonClickStatus.setText(savedInstanceState.getString(RADIO));
			radioButtonClickStatus.setTextColor(Color.RED);
		}
		else if(r.equals("Blue"))
		{
			radioButtonClickStatus.setText(savedInstanceState.getString(RADIO));
			radioButtonClickStatus.setTextColor(Color.BLUE);
		}
		else if(r.equals("Green"))
		{
			radioButtonClickStatus.setText(savedInstanceState.getString(RADIO));
			radioButtonClickStatus.setTextColor(Color.GREEN);
		}

		toggleButtonClickStatus.setText(savedInstanceState.getString(TOGGLE));



	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//using a switch to detect when the button is pressed, change to a color and when it is not
		//clear the filter
		//action down is to let the listener know when the button is pressed, and when the button is released the filter is then cleared
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				smileyButton.setColorFilter(getRandomColor());
				smileyButtonText += "☺";
				smileyButtonStatus.setText(smileyButtonText);
				return true;
			case MotionEvent.ACTION_UP:
				smileyButton.clearColorFilter();
				return true;
		}
		return false;
	}
}