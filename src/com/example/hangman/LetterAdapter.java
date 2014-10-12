package com.example.hangman;

import java.util.zip.Inflater;

import android.content.Context;
import android.provider.Telephony.Sms.Conversations;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class LetterAdapter extends BaseAdapter {
    private String[] letters;
    private LayoutInflater lf;

	
	public LetterAdapter(Context c){
		
		letters = new String[26];
		for(int a = 0; a < letters.length; a++){
			letters[a] = ""+(char)(a + 'A');
			lf = LayoutInflater.from(c);
		}
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return letters.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        Button ltrbutton;
        if(convertView == null){
        	ltrbutton = (Button)lf.inflate(R.layout.letter, parent, false);
             }
        else{
        	ltrbutton = (Button)convertView;
        	
        }	
		ltrbutton.setText(letters[position]);
		
		return ltrbutton;
	}

}
