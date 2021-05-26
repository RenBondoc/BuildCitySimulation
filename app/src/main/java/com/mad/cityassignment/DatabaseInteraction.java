package com.mad.cityassignment;

import android.content.Context;

import java.io.Serializable;

public class DatabaseInteraction implements Serializable {

    private SettingsDBHelper dbHelper;
    private Settings settings;
    private Context context;
    private GameData gameData;

    public DatabaseInteraction(Context context){
        this.context = context;
        dbHelper = new SettingsDBHelper(context);
    }

    public Settings load(){
        dbHelper.loadSettings(this.context);
        settings = dbHelper.getSettingsLoad();
        return settings;
    }

    public void add(Settings settings){
        dbHelper.addSettings(settings);
    }

    public void addGameData(GameData gameData) {dbHelper.addGame(gameData);}

    public GameData gameLoad(){
        dbHelper.loadGame(this.context);
        gameData = dbHelper.getGameData();
        return gameData;
    }

    public void update(Settings settings){
        dbHelper.updateSettings(settings);
    }
}
