package com.example.ethan.dream_fit;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.ShareButton;

import org.json.JSONObject;

import java.util.Arrays;
public class settings_page extends AppCompatActivity {

    DatabaseHelper myDB;
    DatabaseHelper_Main main_DB;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
    }





    public void onEmpty(View view){

        myDB = new DatabaseHelper(this);
        main_DB = new DatabaseHelper_Main(this);

        //call the ->Cursor which will get all the contents of the database
        Cursor data = myDB.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(settings_page.this, "database is currently empty", Toast.LENGTH_LONG).show();

        }else{

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure you want to dump current database ?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",

                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            myDB.clearDatabase();
                            main_DB.clearDatabase();
                            Toast.makeText(settings_page.this, "Database Deleted", Toast.LENGTH_LONG).show();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void checkLog(){
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
    System.out.println("nay1");
    }
}
