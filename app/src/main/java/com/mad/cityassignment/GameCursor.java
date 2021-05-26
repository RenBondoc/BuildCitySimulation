package com.mad.cityassignment;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.cityassignment.GameSchema.GameTable;

public class GameCursor extends CursorWrapper {

    public GameCursor(Cursor cursor) {
        super(cursor);
    }

    public GameData getGameData(){
        byte[] blob = getBlob(getColumnIndex(GameTable.Cols.GAME));
        String json = new String(blob);
        Gson gson = new Gson();
        GameData gameData= gson.fromJson(json, new TypeToken<GameData>(){}.getType());
        return gameData;

    }
}
