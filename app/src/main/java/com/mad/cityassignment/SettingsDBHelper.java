package com.mad.cityassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.mad.cityassignment.SettingsSchema.SettingsTable;
import com.mad.cityassignment.GameSchema.GameTable;


public class SettingsDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "settings.db";
    private static final String GAME_DATABASE_NAME = "game_data.db";
    private Settings settingsLoad;
    private SQLiteDatabase db;
    private GameData gd;

    public SettingsDBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + GameTable.NAME + "(" +
                GameTable.Cols.GAME + " BLOB)");

        db.execSQL("CREATE TABLE " + SettingsTable.NAME + "(" +
                SettingsTable.Cols.MAP_NAME + " TEXT, " +
                SettingsTable.Cols.HEIGHT + " INTEGER, " +
                SettingsTable.Cols.WIDTH + " INTEGER, " +
                SettingsTable.Cols.MONEY + " INTEGER, " +
                SettingsTable.Cols.FAM_SIZE + " INTEGER, " +
                SettingsTable.Cols.SHOP_SIZE + " INTEGER, " +
                SettingsTable.Cols.SALARY + " INTEGER, " +
                SettingsTable.Cols.TAX_RATE + " REAL, " +
                SettingsTable.Cols.SERVICE_COST + " INTEGER, " +
                SettingsTable.Cols.RES_COST + " INTEGER, " +
                SettingsTable.Cols.COM_COST + " INTEGER, " +
                SettingsTable.Cols.ROAD_COST + " INTEGER, " +
                SettingsTable.Cols.MISC_COST + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void loadSettings(Context context){
        this.db = new SettingsDBHelper(
                context.getApplicationContext()).getWritableDatabase();
        SettingsCursor cursor = new SettingsCursor(db.query(SettingsTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null));
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                settingsLoad = cursor.getSettings();
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

    }

    public void loadGame(Context context){
        this.db = new SettingsDBHelper(
                context.getApplicationContext()).getWritableDatabase();

        GameCursor gameCursor = new GameCursor(db.query(GameTable.NAME,
                null,null, null,
                null, null,null));
        try {
            gameCursor.moveToFirst();
            while (!gameCursor.isAfterLast()) {
                gd = gameCursor.getGameData();
                gameCursor.moveToNext();
            }
        } finally {
            gameCursor.close();
        }
    }

    public void addGame(GameData gameData) {
        db = this.getWritableDatabase();

        Gson gson = new Gson();

        ContentValues cv = new ContentValues();
        cv.put(GameTable.Cols.GAME, gson.toJson(gameData).getBytes());
        db.insert(GameTable.NAME, null, cv);
    }

    public Settings getSettingsLoad() {
        return settingsLoad;
    }

    public GameData getGameData(){
        return gd;
    }

    public void addSettings(Settings settings){

        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SettingsTable.Cols.MAP_NAME, settings.getCityName());
        cv.put(SettingsTable.Cols.HEIGHT, settings.getMapHeight());
        cv.put(SettingsTable.Cols.WIDTH, settings.getMapWidth());
        cv.put(SettingsTable.Cols.MONEY, settings.getInitialMoney());
        cv.put(SettingsTable.Cols.FAM_SIZE, settings.getFamilySize());
        cv.put(SettingsTable.Cols.SHOP_SIZE, settings.getShopSize());
        cv.put(SettingsTable.Cols.SALARY, settings.getSalary());
        cv.put(SettingsTable.Cols.TAX_RATE, settings.getTaxRate());
        cv.put(SettingsTable.Cols.SERVICE_COST, settings.getServiceCost());
        cv.put(SettingsTable.Cols.RES_COST, settings.getResBuildingCost());
        cv.put(SettingsTable.Cols.COM_COST, settings.getCommBuildingCost());
        cv.put(SettingsTable.Cols.ROAD_COST, settings.getRoadBuildingCost());
        cv.put(SettingsTable.Cols.MISC_COST, settings.getMiscBuildingCost());
        db.insert(SettingsTable.NAME, null, cv);
    }

    public void updateSettings(Settings settings){
        ContentValues cv = new ContentValues();
        cv.put(SettingsTable.Cols.MAP_NAME, settings.getCityName());
        cv.put(SettingsTable.Cols.HEIGHT, settings.getMapHeight());
        cv.put(SettingsTable.Cols.WIDTH, settings.getMapWidth());
        cv.put(SettingsTable.Cols.MONEY, settings.getInitialMoney());
        cv.put(SettingsTable.Cols.FAM_SIZE, settings.getFamilySize());
        cv.put(SettingsTable.Cols.SHOP_SIZE, settings.getShopSize());
        cv.put(SettingsTable.Cols.SALARY, settings.getSalary());
        cv.put(SettingsTable.Cols.TAX_RATE, settings.getTaxRate());
        cv.put(SettingsTable.Cols.SERVICE_COST, settings.getServiceCost());
        cv.put(SettingsTable.Cols.RES_COST, settings.getResBuildingCost());
        cv.put(SettingsTable.Cols.COM_COST, settings.getResBuildingCost());
        cv.put(SettingsTable.Cols.ROAD_COST, settings.getRoadBuildingCost());
        cv.put(SettingsTable.Cols.MISC_COST, settings.getMiscBuildingCost());

        String[] whereValue = {String.valueOf(settings.getCityName())};
        db.update(SettingsTable.NAME, cv, SettingsTable.Cols.MAP_NAME + "=?", whereValue);

    }

}
