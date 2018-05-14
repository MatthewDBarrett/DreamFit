package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);

        ListView resultsListView = (ListView) findViewById(R.id.results_listview);
        String name = prefs.getString("name", ""); ;
        float height = prefs.getFloat("height", 0);
        float weight = prefs.getFloat("weight", 0);
        boolean gender = prefs.getBoolean("gender", true);//Let Male be True, Female be False.
        int dobday = prefs.getInt("dobday", 1);
        int dobmonth = prefs.getInt("dobmonth", 1);
        int dobyear = prefs.getInt("dobyear", 1901);

        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("Name", name);
        nameAddresses.put("Height", Float.toString(height));
        nameAddresses.put("Weight", Float.toString(weight));
        nameAddresses.put("Gender", gender ? "Male" : "Female");
        nameAddresses.put("Date of Birth", Integer.toString(dobday) + "/" + Integer.toString(dobmonth) + "/" + Integer.toString(dobyear));

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.custom_list_view_cell,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        resultsListView.setAdapter(adapter);
    }
}
