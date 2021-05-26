package com.mad.cityassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button playButton, settingsButton;
    private Settings settings;
    private static final int REQUEST_CODE_SETTINGS = 0;
    private static final int REQUEST_CODE_PLAY = 101;
    private DatabaseInteraction db;
    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.playButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);

        //settings = new Settings();
        db = new DatabaseInteraction(this);
        settings = db.load();
        gameData = db.gameLoad();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings != null) {
                    if (gameData == null) {
                        gameData = new GameData(settings.getMapHeight(), settings.getMapWidth(), settings.getInitialMoney());
                    }
                    startActivityForResult(MapActivity.getIntent(MainActivity.this, settings, gameData), REQUEST_CODE_PLAY);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Unable to Start!");
                    builder.setMessage("Please Configure Settings First!");
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(SettingsActivity.getIntent(MainActivity.this, settings), REQUEST_CODE_SETTINGS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SETTINGS){
            settings = (Settings) data.getSerializableExtra("New_Settings");
            db.add(settings);
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PLAY){
            gameData = (GameData) data.getSerializableExtra("Game_Data_Update");
            db.addGameData(gameData);
        }
        else{
            System.out.println("ERROR IN LOADING RESULTS");
        }
    }
}