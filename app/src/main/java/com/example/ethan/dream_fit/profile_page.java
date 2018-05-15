package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        final Context context = this;
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);

        final ListView resultsListView = (ListView) findViewById(R.id.results_listview);
        //Shared Preference Initialisation
        String name = prefs.getString("name", "<nonamespecified>"); ;
        float height = prefs.getFloat("height", 0);
        float weight = prefs.getFloat("weight", 0);
        boolean gender = prefs.getBoolean("gender", true);//Let Male be True, Female be False.
        int dobday = prefs.getInt("dobday", 1);
        int dobmonth = prefs.getInt("dobmonth", 1);
        int dobyear = prefs.getInt("dobyear", 1901);

        //storing values from the shared preferences in a hashmap.
        final HashMap<String, String> userValues = new HashMap<>();
        userValues.put("Name", name);
        userValues.put("Height", Float.toString(height) + "cm");
        userValues.put("Weight", Float.toString(weight) + "kg");
        userValues.put("Gender", gender ? "Male" : "Female");
        userValues.put("Date of Birth", Integer.toString(dobday) + "/" + Integer.toString(dobmonth) + "/" + Integer.toString(dobyear));

        List<HashMap<String, String>> listItems = new ArrayList<>();
        final SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.custom_list_view_cell,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = userValues.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        resultsListView.setAdapter(adapter);

        resultsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                        // get the view1
                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.stat_change, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);
                        final TextView prompt = (TextView) promptsView.findViewById(R.id.prompt);
                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.editTextResult);
                        final HashMap<String, String> obj = (HashMap<String, String>) adapter.getItem(position);
                        Set<String> objKeyset = obj.keySet();
                        String objStr = obj.get("First Line");
                        int choice;

                        switch(objStr){
                            case "Name":
                                prompt.setText(R.string.nameprompt);
                                userInput.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;
                            case "Height":
                                prompt.setText(R.string.heightprompt);
                                userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                                break;
                            case "Weight":
                                prompt.setText(R.string.weightprompt);
                                userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                                break;
                            case "Gender":
                                prompt.setText(R.string.genderprompt);
                                userInput.setVisibility(View.INVISIBLE);
                                break;
                            case "Date of Birth":
                                prompt.setText(R.string.DOBprompt);
                                userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                                break;
                        }
                        for(String key : userValues.keySet()){
                            //if(o)
                        }

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

//                        switch(userValues.get(selectedItem)){
//
//                        }
                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }

                });
    }
}
