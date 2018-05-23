package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class profile_page extends AppCompatActivity {
    //These need to be Global within the profile_page as they are used in inner classes.

    //Shared Preferences and Fields
    SharedPreferences prefs;
    RadioButton maleButton;
    RadioButton femaleButton;
    TextView prompt;
    EditText userInput;

    //Hashmaps and lists
    HashMap<String, String> userValues;
    List<HashMap<String, String>> listItems;

    //Preference Fields
    String name;
    float height;
    float weight;
    boolean gender;
    int dobday;
    int dobmonth;
    int dobyear;

    //Iterator
    Iterator it;

    //Adapters
    SimpleAdapter adapter;

    //Contexts
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        context = this;
        prefs = this.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);


        ListView resultsListView = (ListView) findViewById(R.id.results_listview);
        //Shared Preference Initialisation
        name = prefs.getString("name", "<nonamespecified>");
        height = prefs.getFloat("height", 0);
        weight = prefs.getFloat("weight", 0);
        gender = prefs.getBoolean("gender", true);//Let Male be True, Female be False.
        dobday = prefs.getInt("dobday", 1);
        dobmonth = prefs.getInt("dobmonth", 1);
        dobyear = prefs.getInt("dobyear", 1901);

        //storing values from the shared preferences in a hashmap.
        userValues = new HashMap<>();
        userValues.put("Name", name);
        userValues.put("Height", Float.toString(height) + "cm");
        userValues.put("Weight", Float.toString(weight) + "kg");
        userValues.put("Gender", gender ? "Male" : "Female");
        userValues.put("Date of Birth", Integer.toString(dobday) + "/" + Integer.toString(dobmonth) + "/" + Integer.toString(dobyear));

        //List to store Items
        listItems = new ArrayList<>();

        //Adapter instatiation
        adapter = new SimpleAdapter(this, listItems, R.layout.custom_list_view_cell,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});

        //Iterate through the uservalues and set them in the adapter
        it = userValues.entrySet().iterator();
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
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                        // get the view1
                        final HashMap<String, String> obj = (HashMap<String, String>) adapter.getItem(position);
                        Set<String> objKeyset = obj.keySet();
                        String objStr = obj.get("First Line");

                        final LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.stat_change, null);
                        View genderPromptView = li.inflate(R.layout.gender_change, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);
                        final int choice;

                        switch(objStr){
                            case "Name":
                                promptsView = li.inflate(R.layout.stat_change, null);
                                prompt = (TextView) promptsView.findViewById(R.id.prompt);
                                prompt.setText(R.string.nameprompt);
                                userInput = (EditText) promptsView
                                        .findViewById(R.id.editTextResult);
                                userInput.setInputType(InputType.TYPE_CLASS_TEXT);
                                choice = 1;
                                break;
                            case "Height":
                                promptsView = li.inflate(R.layout.stat_change, null);
                                prompt = (TextView) promptsView.findViewById(R.id.prompt);
                                prompt.setText(R.string.heightprompt);
                                userInput = (EditText) promptsView
                                        .findViewById(R.id.editTextResult);
                                userInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                choice = 2;
                                break;
                            case "Weight":
                                promptsView = li.inflate(R.layout.stat_change, null);
                                prompt = (TextView) promptsView.findViewById(R.id.prompt);
                                prompt.setText(R.string.weightprompt);
                                userInput = (EditText) promptsView
                                        .findViewById(R.id.editTextResult);
                                userInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                choice = 3;
                                break;
                            case "Gender":
                                promptsView = li.inflate(R.layout.gender_change, null);
                                prompt = (TextView) promptsView.findViewById(R.id.prompt);
                                prompt.setText(R.string.genderprompt);
                                maleButton = promptsView.findViewById(R.id.maleRadio);
                                femaleButton = promptsView.findViewById(R.id.femaleRadio);
                                choice = 4;
                                break;
                            case "Date of Birth":
                                promptsView = li.inflate(R.layout.stat_change, null);
                                prompt = (TextView) promptsView.findViewById(R.id.prompt);
                                prompt.setText(R.string.DOBprompt);
                                userInput = (EditText) promptsView
                                        .findViewById(R.id.editTextResult);
                                userInput.setInputType(InputType.TYPE_CLASS_TEXT);
                                choice = 5;
                                break;
                            default:
                                choice = 1;
                                break;

                        }

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                        // set dialog message
                        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                switch(choice){
                                                    case 1:
                                                        //Get the text, set it in the prefs edit and call update adapter
                                                        if(!userInput.getText().toString().equals("")){
                                                             prefs.edit().putString("name", userInput.getText().toString()).apply();
                                                        }

                                                        updateAdapter();
                                                        break;
                                                    case 2:
                                                        if(!userInput.getText().toString().equals("")){
                                                            prefs.edit().putFloat("height", Float.parseFloat(userInput.getText().toString())).apply();
                                                        }

                                                        updateAdapter();
                                                        break;
                                                    case 3:
                                                        if(!userInput.getText().toString().equals("")){
                                                            prefs.edit().putFloat("weight", Float.parseFloat(userInput.getText().toString())).apply();
                                                        }

                                                        updateAdapter();
                                                        break;
                                                    case 4:
                                                        if(maleButton.isChecked()){
                                                            prefs.edit().putBoolean("gender", true).apply();
                                                        }
                                                        if(femaleButton.isChecked()){
                                                            prefs.edit().putBoolean("gender", false).apply();
                                                        }

                                                        updateAdapter();
                                                        break;
                                                    case 5:
                                                        String str = userInput.getText().toString();
                                                        if(!(str.equals("")) && checkDateFormat(str)){
                                                            prefs.edit().putInt("dobday", Integer.parseInt(str.substring(0, 2))).apply();
                                                            prefs.edit().putInt("dobmonth", Integer.parseInt(str.substring(3, 5))).apply();
                                                            prefs.edit().putInt("dobyear", Integer.parseInt(str.substring(6, 10))).apply();
                                                        }
                                                        else{
                                                            Toast.makeText(context, "Invalid Input. Make sure your D.O.B includes forward slashes.", Toast.LENGTH_LONG).show();
                                                        }

                                                        updateAdapter();
                                                        break;
                                                }

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

    private void updateAdapter(){
        userValues.put("Name", prefs.getString("name", "<error>"));
        userValues.put("Height", prefs.getFloat("height", 0) + "cm");
        userValues.put("Weight", prefs.getFloat("weight", 0) + "kg");
        userValues.put("Gender", prefs.getBoolean("gender", true) ? "Male" : "Female");
        userValues.put("Date of Birth", prefs.getInt("dobday", 1) + "/" + prefs.getInt("dobmonth", 1) + "/" + prefs.getInt("dobyear", 1901));

        listItems.clear();
        it = userValues.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }
        adapter.notifyDataSetChanged();
    }

    private Boolean checkDateFormat(String input){
        Boolean day = Integer.parseInt(input.substring(0, 2)) <= 31 && Integer.parseInt(input.substring(0, 2)) >= 1;
        Boolean month = Integer.parseInt(input.substring(3, 5)) <= 12 && Integer.parseInt(input.substring(0, 2)) >= 1;
        Boolean year = Integer.parseInt(input.substring(6, 10)) <= 9999 && Integer.parseInt(input.substring(0, 2)) >= 1;

        return (day && month && year);
    }
}

