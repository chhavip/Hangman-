package com.example.hangman;

import java.util.Random;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi") public class GameActivity extends Activity {
 
	private String[] words;
	private String[] hints;
	private Random rand;
	private String currword;
	private LinearLayout wordlayout;
	private TextView[] charviews;
	private GridView letters;
	private LetterAdapter ltrAdapter;
	private ImageView[] bodyparts;
	private int numparts = 6;
	private int currpart;
	private int numchars;//total characters
	private int numcorrect;//correctly guessed
	private String currHint;
	TextView hintview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Resources res = getResources();
		words = res.getStringArray(R.array.words_tech);
		hints = res.getStringArray(R.array.comp_hints);
		rand = new Random();
		currword = "";
		wordlayout = (LinearLayout)findViewById(R.id.words);
		letters = (GridView)findViewById(R.id.letters);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		//start the game
		bodyparts = new ImageView[numparts];
		bodyparts[0] = (ImageView)findViewById(R.id.head);
		bodyparts[1] = (ImageView)findViewById(R.id.body);
		bodyparts[2] = (ImageView)findViewById(R.id.armleft);
		bodyparts[3] = (ImageView)findViewById(R.id.armright);
		bodyparts[4] = (ImageView)findViewById(R.id.legleft);
		bodyparts[5] = (ImageView)findViewById(R.id.legright);
		
		playGame();
		
		ActionBar bar = getActionBar();
		bar.setHomeButtonEnabled(true);	
		bar.setTitle("Go home");
	}
	
	private void playGame(){
		//play a new game
		int id = rand.nextInt(words.length);
		String newword = words[id];
		while(newword.equals(currword)){
			id = rand.nextInt(words.length);
		
		  newword = words[id];
		 
		}
		
		 currHint = hints[id];
		 hintview = (TextView)findViewById(R.id.hint_view);
		 hintview.setText(currHint);
		currword = newword;
		charviews = new TextView[currword.length()];
		wordlayout.removeAllViews();
		
		for(int c = 0; c< currword.length(); c++){
			charviews[c] = new TextView(this);
			charviews[c].setText(""+currword.charAt(c));
			
			charviews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			charviews[c].setGravity(Gravity.CENTER);
			charviews[c].setTextColor(color.white);
			charviews[c].setBackgroundResource(R.drawable.letter_bg);
			// add to layout
			
			wordlayout.addView(charviews[c]);
		}
		
		
		
		ltrAdapter = new LetterAdapter(this);
		letters.setAdapter(ltrAdapter);		
		
		currpart = 0;
	    numchars = currword.length();
	    numcorrect = 0;
		
	    for(int p = 0; p< numparts; p++){
	    	bodyparts[p].setVisibility(View.INVISIBLE);
	    	
	    }
	}
	
	public void letterPressed(View v){
		
		
		String ltr = ((TextView)v).getText().toString();
		
		char letterchar = ltr.charAt(0);
		Log.i("word", ""+letterchar);
		v.setEnabled(false);
		v.setBackgroundResource(R.drawable.letter_down);
		
		boolean correct = false;
		
		for(int k = 0; k < currword.length(); k++){
			if(currword.charAt(k) == letterchar){
				
				correct = true;
				numcorrect++;
				charviews[k].setTextColor(Color.BLACK);
				
				
			}
			
		}
		Log.i("currword",currword);
		
		if(correct){
		
			if(numcorrect == numchars){
				Log.i("game end" , "win");
				disableBtns();
				
				AlertDialog.Builder build = new AlertDialog.Builder(this);
				Log.i("gf", "df");
				build.setTitle("HURRAY!");
				build.setMessage("You win!\n\nBit of a smartass arent ya?");
				build.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// TODO Auto-generated method stub
						GameActivity.this.playGame();
					}
				});
				
				build.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						GameActivity.this.finish();
					}
							});
			
				
				build.create().show();
				Log.i("builder", "created");
				
			}
		}
		
		else {
			currpart++;
		
			if(currpart < numparts){
			
			bodyparts[currpart-1].setVisibility(View.VISIBLE);
			
			
		}
		     else{
			
			disableBtns();
			
			AlertDialog.Builder loss = new AlertDialog.Builder(this);
			loss.setTitle("MURDERER!");
			loss.setMessage("The answer is "+currword+ "\n\nThe pain is just too much to bear :'-(");
			loss.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int id) {
					// TODO Auto-generated method stub
					GameActivity.this.playGame();
				}
			});
			
			loss.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					GameActivity.this.finish();
				}
			});
			loss.create().show();
			
			
		}
		
		}
		
	}
	
	
	
	
	//separate method to disable all buttons. on event of win or loss.
	public void disableBtns() {
		  int numLetters = letters.getChildCount();
		  for (int l = 0; l < numLetters; l++) {
		    letters.getChildAt(l).setEnabled(false);
		  }
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if(id == android.R.id.home){
			NavUtils.navigateUpFromSameTask(this);
			return true;
			
		}
		else if(id == R.id.action_help){
			showHelp();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showHelp(){
		
		AlertDialog.Builder help = new AlertDialog.Builder(this);
		help.setTitle("CANT FIGURE OUT ON YOUR OWN?");
		help.setMessage("Listen up bud! \n\n you got  6,  get  that? 6  chances only  or  say  goodbye  to  the  pretty  boy.");
		help.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
			      	dialog.dismiss();
			}
		});
		
		help.create().show();
		
	}
	
}