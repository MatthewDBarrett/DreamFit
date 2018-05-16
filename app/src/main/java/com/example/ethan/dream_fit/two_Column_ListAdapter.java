package com.example.ethan.dream_fit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// Note - https://www.youtube.com/watch?v=jpt3Md9aDIQ
// Note - this class is harder to implement and has been implemented by 'heavy' referencing from the about source

public class two_Column_ListAdapter extends ArrayAdapter<Item>{

   private LayoutInflater thisInfalter;
   private ArrayList<Item> items ;
   private int thisViewResourceId;

   public two_Column_ListAdapter(Context context, int textViewResourceId, ArrayList<Item> items){
       super(context, textViewResourceId, items);
       this.items = items;
       thisInfalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       thisViewResourceId = textViewResourceId;
   }

   public View getView(int postion, View convertView, ViewGroup parents){
       convertView = thisInfalter.inflate(thisViewResourceId,null);

       Item item = items.get(postion);

       if(item != null){
           TextView description = (TextView) convertView.findViewById(R.id.foodItemId);
           TextView calorie = (TextView) convertView.findViewById(R.id.cal);

           if(description != null){
               description.setText(item.getDescription());
           }
           if(calorie != null){
               calorie.setText(item.getCalorie());
           }

       }

       return convertView;


   }
}
