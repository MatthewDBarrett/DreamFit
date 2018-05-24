package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class calorie_tracker_main_page extends AppCompatActivity {


    DatabaseHelper_Main main_DB;

    //for implementing list view
    ArrayList<Item> itemList;
    ListView mainListView;
    Item thisItem;
    LayoutInflater layoutinflater;
    TextView calorieLimitTextView;
    TextView calorieAmountTextView;

    //for circular progress bar
    private int limitAmnt = 1000;
    private int calorieInt = 0 ;
    private SharedPreferences sharedPrefObj;
    private SharedPreferences.Editor mEditor;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_main_page);

        mainListView = (ListView) findViewById(R.id.foodList);
        main_DB = new DatabaseHelper_Main(context);


        //------------------------Shared Preferences-----------------------------------------

        // sharedPrefObj is declarig the Shared.Pref
        sharedPrefObj = PreferenceManager.getDefaultSharedPreferences(this);
        // Editor object used as a 'tool' to put items into Share.Pref.
        mEditor = sharedPrefObj.edit();
        //check previous Saved Scenario

        Integer storedCalories = sharedPrefObj.getInt(getString(R.string.calorieKey),0);
        Integer storedLimit = sharedPrefObj.getInt(getString(R.string.limitKey), 1000);

        //Initialize the calorieInt and LimitAmount according to shared Preferences
        calorieInt = storedCalories;
        limitAmnt  = storedLimit;

        //Initializing the 'limit' and progress of the circular bar
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress((float)(calorieInt/limitAmnt));

        calorieLimitTextView = findViewById(R.id.calorieLimit);
        calorieAmountTextView = findViewById(R.id.calorieAmount);
        calorieLimitTextView.setText(Integer.toString(limitAmnt));
        calorieAmountTextView.setText(Integer.toString(calorieInt));

        //------------------------------------------OnLimit Handler-----------------------------------------------------------------

        //initialise the prompts.
        Button changeButton = (Button) findViewById(R.id.limitBtn);


    //-----------------------------------------------create array List adapter and set it to list view----------------------------------------------------------

        //1. create an array list and
        //2. populate it through database
        //3. adapt that through list adapter

        //ArrayList<String> thisList = new ArrayList<>();
        //Instead of the above code; we will use an arrayList of items
        itemList = new ArrayList<>();

        //call the ->Cursor which will get all the contents of the database
        Cursor data = main_DB.getListContents();

        //populate the array list with the data now
        if (data.getCount() == 0)
            Toast.makeText(calorie_tracker_main_page.this, "database is currently empty", Toast.LENGTH_LONG).show();
        else{
            while(data.moveToNext()){
                //continue looping through

                //We will store the user's food item.
                thisItem = new Item(data.getString(1),data.getString(2));

                //-> '1' here means the 'column no' for 'description 'in the database
                //-> '2' here means the 'column no' for 'calorie 'in the database

                //Now add items to the array_List called 'itemList'
                itemList.add(thisItem);

                two_Column_ListAdapter adapter = new two_Column_ListAdapter(this,R.layout.adapter_view_layout, itemList);


                mainListView.setAdapter(adapter);


                //THis is what Carlos suggested
                mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                //----------------------------------------- OnItemClick handler for the list view--------------------------------------------
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        // get the current Index
                        Item thisItem = itemList.get(i);

                        //Integer storedCalorie = sharedPrefObj.getInt(getString(R.string.calorieKey),calorieInt);
                        //calorieInt = storedCalorie;

                        if(calorieInt <= limitAmnt){
                            Toast.makeText(calorie_tracker_main_page.this, " all calories, Tracked!", Toast.LENGTH_SHORT).show();

                            int calorie = Integer.parseInt((thisItem.getCalorie()).toString());

                               calorieInt += calorie;
                              //Update sharedPref
                              mEditor.putInt(getString(R.string.calorieKey),calorieInt);
                              mEditor.commit();

                              changeProgress(calorieInt,limitAmnt);

                            //Integer newCalorie = sharedPrefObj.getInt(getString(R.string.calorieKey),calorieInt);

                            calorieAmountTextView.setText(Integer.toString(calorieInt));
                        }else
                            Toast.makeText(calorie_tracker_main_page.this, " dogged", Toast.LENGTH_SHORT).show();

                    }
                });

                //------------------------------------------------------------------------------------------------------------------------------

            }
        }
    }


    @Override
    protected void onResume() {

        super.onResume();
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        Integer cal = sharedPrefObj.getInt(getString(R.string.calorieKey), 0);
        calorieAmountTextView.setText(Integer.toString(cal));

        Integer lim = sharedPrefObj.getInt(getString(R.string.limitKey), 1000);
        calorieLimitTextView.setText(Integer.toString(lim));

        changeProgress(cal,lim);
    }

    private void changeProgress(int step, int limit){
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress((float)(100 * step/ limit));
    }

    public void onAdd(View view){
        Intent intent = new Intent(this, calorie_tracker_add_list.class);
        startActivity(intent);
    }

    public void onRemove(View view){
      Intent intent = new Intent(this, calorie_tracker_remove_List.class);
      startActivity(intent);
    }

    public void onLimit(View view){
        // get the view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.alertprompt_2, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextResult);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                limitAmnt = Integer.parseInt(userInput.getText().toString());

                                //Update the progressbar
                                changeProgress(calorieInt, limitAmnt);

                                //update the sharedPreferences
                                mEditor.putInt(getString(R.string.limitKey),limitAmnt);
                                mEditor.commit();

                                //clear textField
                                calorieLimitTextView.setText(Integer.toString(limitAmnt));
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

    public void onReset(View view){

        // This method is somewhat similar  to the  implementation done in step-Counter by "Ethan" , of course ! with heavy modification

        Button changeButton = (Button) findViewById(R.id.reset);

        //prompts class


                // get the view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.warning_reset_2, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextResult);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        calorieInt = 0;
                                        limitAmnt = 1000;

                                        //Update the progressbar
                                        changeProgress(calorieInt,limitAmnt);

                                        //update the sharedPreferences
                                        mEditor.putInt(getString(R.string.calorieKey),calorieInt);
                                        mEditor.putInt(getString(R.string.limitKey),limitAmnt);
                                        mEditor.commit();

                                        //clear textField
                                        calorieAmountTextView.setText("0");
                                        calorieLimitTextView.setText("1000");

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



}
