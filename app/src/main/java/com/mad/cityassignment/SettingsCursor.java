package com.mad.cityassignment;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.mad.cityassignment.SettingsSchema.SettingsTable;

public class SettingsCursor extends CursorWrapper {

    public SettingsCursor(Cursor cursor) {
        super(cursor);
    }

    public Settings getSettings(){
        String name = getString(getColumnIndex(SettingsTable.Cols.MAP_NAME));
        int height = getInt(getColumnIndex(SettingsTable.Cols.HEIGHT));
        int width = getInt(getColumnIndex(SettingsTable.Cols.WIDTH));
        int money = getInt(getColumnIndex(SettingsTable.Cols.MONEY));
        int famSize = getInt(getColumnIndex(SettingsTable.Cols.FAM_SIZE));
        int shopSize = getInt(getColumnIndex(SettingsTable.Cols.SHOP_SIZE));
        int salary = getInt(getColumnIndex(SettingsTable.Cols.SALARY));
        double taxRate = getDouble(getColumnIndex(SettingsTable.Cols.TAX_RATE));
        int serviceCost = getInt(getColumnIndex(SettingsTable.Cols.SERVICE_COST));
        int resBuilding = getInt(getColumnIndex(SettingsTable.Cols.RES_COST));
        int comBuilding = getInt(getColumnIndex(SettingsTable.Cols.COM_COST));
        int roadBuilding = getInt(getColumnIndex(SettingsTable.Cols.ROAD_COST));
        int miscBuilding = getInt(getColumnIndex(SettingsTable.Cols.MISC_COST));

        return new Settings(name, width, height, money, famSize, shopSize, salary,
                serviceCost, resBuilding, comBuilding, roadBuilding, miscBuilding, taxRate);

    }
}
