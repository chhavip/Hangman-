package com.example.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setTitle("Choose your poison");
     String[] list = {"Technology" , "F.R.I.E.N.D.S", "World" , "Bollywood"};
     Myadapter ad = new Myadapter(this, list);
     setListAdapter(ad);
    
    	ListView lv = getListView();
    	lv.setBackgroundResource(R.drawable.list_selector);
    	
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	Log.i("positon", ""+position);
    	Intent i = new Intent();
    	switch(position){
    	case 0:
    		
    		i =new Intent(this, GameActivity.class);
    		break;
    	case 1:
  
    		i = new Intent(this, FriendsGameActivity.class);
    		break;
    	case 2:
    		
    		i = new Intent(this, WorldGameActivity.class);
    		break;
    	case 3:
    		i = new Intent(this, BollywoodGameActivity.class);
    		break;
    	
    	
    	
    	}
    startActivity(i);
    	
    
    }
    
    public class Myadapter extends ArrayAdapter<String>{
    	final Context context;
		final String[] values;
		
		public Myadapter(Context context, String[] objects) {
			super(context, R.layout.rowlayout, R.id.label, objects);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.values = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		  LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  
		  View rowview = inflater.inflate(R.layout.rowlayout, parent, false);
		  
           TextView tv = (TextView)rowview.findViewById(R.id.label);
           ImageView im = (ImageView)rowview.findViewById(R.id.icon);
           tv.setText(values[position]);
           
           String s = values[position];
           if(s.equals("Technology"))
        	   im.setImageResource(R.drawable.tech);
           if(s.equals("F.R.I.E.N.D.S"))
        	   im.setImageResource(R.drawable.friends_img);
          if(s.equals("World"))
        	  im.setImageResource(R.drawable.atlas_icon);
          if(s.equals("Bollywood"))
        	  im.setImageResource(R.drawable.bollywood);
        	   
			
		  return rowview;
	
		}
		
		
    	
    	
    	
    	
    	
    	
    	
    }

}
