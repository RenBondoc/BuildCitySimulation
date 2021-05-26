package com.mad.cityassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText mapHeight, mapWidth, initialMoney, mapName;
    private Button save;
    private Settings settings;
    private SettingsDBHelper dbHelper;

    public static Intent getIntent(Context c, Settings settings) {
        Intent intent = new Intent(c, SettingsActivity.class);
        intent.putExtra("Settings_Menu", settings);
        //intent.putExtra("Database", helper);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent getIntent = getIntent();
        settings = (Settings) getIntent.getSerializableExtra("Settings_Menu");
        if (settings == null){
            settings = new Settings();
        }

        mapName = (EditText) findViewById(R.id.mapName);
        mapName.setText(settings.getCityName());

        mapHeight = (EditText) findViewById(R.id.settingHeight);
        mapHeight.setText(Integer.toString(settings.getMapHeight()));

        mapWidth = (EditText) findViewById(R.id.settingWidth);
        mapWidth.setText(Integer.toString(settings.getMapWidth()));

        initialMoney = (EditText) findViewById(R.id.settingInitialMoney);
        initialMoney.setText(Integer.toString(settings.getInitialMoney()));

        save = (Button) findViewById(R.id.saveSettings);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setCityName(mapName.getText().toString());
                settings.setMapHeight(Integer.parseInt(mapHeight.getText().toString()));
                settings.setMapWidth(Integer.parseInt(mapWidth.getText().toString()));
                settings.setInitialMoney(Integer.parseInt(initialMoney.getText().toString()));

                Intent returnData = new Intent();
                returnData.putExtra("New_Settings", settings);
                setResult(Activity.RESULT_OK, returnData);
                finish();
            }
        });

    }
}