package com.example.ethan.dream_fit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import static android.view.View.INVISIBLE;

public class StepCounter extends AppCompatActivity implements SensorEventListener {
    /*
    * 3) set an alarm/reciever to set the stepInt to 0 at 12AM of the next day
    * 4) notify the user that the counter only works when the app is open on the stepcounter page.
    * */

    /*
    *  You’ll burn one calorie for every 20 steps you burn, indicates the website of Shape Up America!,
    *  Website - https://www.livestrong.com/article/320124-how-many-calories-does-the-average-person-use-per-step/
    * */

    private int stepInt;
    private int limitAmnt;
    private int burntCal ;
    private int calLimitToBurn;
    TextView calorieBurntTextView ;
    TextView calorieLimitTextView ;
    final Context context = this;
    SharedPreferences prefs;

    SharedPreferences sharedPrefObj;               //CalorieBurnt
    private SharedPreferences.Editor mEditor;      //CaloriesBurnt


    //TextView tv_step;                   //STEP COUNTER

    SensorManager sensorManager;        //STEP COUNTER

    boolean running = false;            //STEP COUNTER

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

        /*
        *   CALORIES TO BURN
        * */

        Integer cal = sharedPrefObj.getInt(getString(R.string.calorieToBurnKey), 0);
        calorieBurntTextView.setText(String.valueOf(cal));
        //calorieLimitTextView.setText(String.valueOf(calLimitToBurn));
        if(burntCal!=0){
            changeBurntCalProg(cal, calLimitToBurn);
            Toast.makeText(this, " found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //if you unregister the hardware will stop detecting steps
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            stepInt++;
            prefs.edit().putInt("stepAmnt", stepInt).apply();
            TextView textElement = (TextView) findViewById(R.id.sampleText3);
            textElement.setText(String.valueOf(stepInt));
            //motivationDecision();
            changeProgress(stepInt, limitAmnt);

            //tv_step.setText(String.valueOf(sensorEvent.values[0]));

            /*
            * FOR CALORIES BURNT
            * 1. n % x == 0
            * 2. Means that n can be divided by x. So... for instance, in your case:
            * 3. stepInt can be divided by 20 i.e ) One calorie burnt
            **/

            if(stepInt == 20){
                ++burntCal;
                calorieBurntTextView.setText(String.valueOf(burntCal));
                changeBurntCalProg(burntCal, calLimitToBurn);
                //add to shared preference
                mEditor.putInt(getString(R.string.calorieToBurnKey),burntCal);                                      //BURNT CALORIES
                mEditor.commit();                                                                                   //BURNT CALORIES
            }else if (stepInt % 20 == 0){
                ++burntCal;
                calorieBurntTextView.setText(String.valueOf(burntCal));
                changeBurntCalProg(burntCal, calLimitToBurn);
                //add to shared preference
                mEditor.putInt(getString(R.string.calorieToBurnKey),burntCal);                                      //BURNT CALORIES
                mEditor.commit();                                                                                   //BURNT CALORIES
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        prefs = this.getSharedPreferences(
                "com.example.ethan.dream_fit", Context.MODE_PRIVATE);

        sharedPrefObj = PreferenceManager.getDefaultSharedPreferences(this);                      //BURNT CALORIES
        mEditor = sharedPrefObj.edit();                                                                  //BURNT CALORIES


        stepInt = prefs.getInt("stepAmnt", 0);
        limitAmnt = prefs.getInt("stepLimit", 10000);

        //mEditor.putInt(getString(R.string.calorieToBurnKey),burntCal);                                      //BURNT CALORIES
        //mEditor.commit();
        burntCal= sharedPrefObj.getInt(getString(R.string.calorieToBurnKey),0);//BURNT CALORIES
        calLimitToBurn = sharedPrefObj.getInt(getString(R.string.calorieKey),0);                     //BURNT CALORIES

        //tv_step = (TextView) findViewById(R.id.tv_step);                                 //STEP COUNTER
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);        //STEP COUNTER

        //TextViews of the text elements on the screen
        TextView textElement = (TextView) findViewById(R.id.sampleText3);
        final TextView textElement2 = (TextView) findViewById(R.id.sampleText2);
        TextView motivation = (TextView) findViewById(R.id.motivationText);

        //TextView for Calories Burnt
         calorieBurntTextView = (TextView) findViewById(R.id.calBurn1);
         calorieLimitTextView = (TextView) findViewById(R.id.calLim);

        //set the text to the default values, initialise the progress bar.
        textElement.setText(String.valueOf(stepInt));
        textElement2.setText(String.valueOf(limitAmnt));
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress((float)(stepInt/limitAmnt));
        motivation.setVisibility(INVISIBLE);

        /*
        * set the text to the default values and Intialize the progress bar for calories burnt
        * */

        calorieBurntTextView.setText(String.valueOf(burntCal));
        calorieLimitTextView.setText(String.valueOf(calLimitToBurn));
        CircularProgressBar caloriesProgress = findViewById(R.id.calorieBurnt_Bar);
        caloriesProgress.setProgress(burntCal);

        //initialise the prompts.
        Button changeButton = (Button) findViewById(R.id.changeLim);

        //prompts class
        changeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get the view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.alertprompt, null);

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
                                        prefs.edit().putInt("stepLimit",Integer.parseInt(userInput.getText().toString())).apply();
                                        limitAmnt = prefs.getInt("stepLimit", 10000);
                                        changeProgress(stepInt, limitAmnt);
                                        textElement2.setText(String.valueOf(limitAmnt));
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

        Button resetButton = findViewById(R.id.reset);

        //prompts class
        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get the view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.warning_reset, null);

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
                                        prefs.edit().putInt("stepAmnt", 0).apply();
                                        stepInt = prefs.getInt("stepAmnt", 0);
                                        TextView textElement = (TextView) findViewById(R.id.sampleText3);
                                        textElement.setText(String.valueOf(stepInt));
                                        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
                                        changeProgress(stepInt,limitAmnt);
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

    public void onReset(View view){
        //motivationDecision();
    }

    private void changeProgress(int step, int limit){
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress((float)(100 * step/ limit));
    }

    private void changeBurntCalProg(int step, int limit){
        CircularProgressBar progressBar = findViewById(R.id.calorieBurnt_Bar);
        progressBar.setProgress((float)(100 * step/ limit));
    }

    private void motivationDecision(){
        TextView motivation = (TextView) findViewById(R.id.motivationText);
        if(stepInt/limitAmnt == 0){
            motivation.setText(R.string.progress1);
        }
        if(stepInt/limitAmnt >= (0.25) && stepInt/limitAmnt < (0.5)){
            motivation.setText(R.string.progress2);
        }
        if(stepInt/limitAmnt >= (0.5) && stepInt/limitAmnt < 1){
            motivation.setText(R.string.progress3);
        }
        if(stepInt/limitAmnt == 1){
            motivation.setText(R.string.progress4);
        }
        if(stepInt/limitAmnt > 1){
            motivation.setText(R.string.progress5);
        }
    }

}