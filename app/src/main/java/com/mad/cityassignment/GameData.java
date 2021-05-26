package com.mad.cityassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.io.Serializable;

import static java.lang.Math.min;

public class GameData implements Serializable {

    private MapData md;
    private int money, gameTime;

    public GameData(int height, int width, int money){
        this.md = MapData.get(height, width);
        this.money = money;
        this.gameTime = 0;
    }

    public MapData getMapData() { return this.md; }

    public int getMoney() { return this.money; }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGameTime() {return this.gameTime;}

    public void setGameTime(int time) {this.gameTime = time;}

    public int getPopulation(int familySize) {
        int resCount = 0;
        int population = familySize;

        for(int i = 0; i < md.getHeight(); i++){
            for (int j = 0; j < md.getWidth(); j++){
                if (md.getElement(i,j).getStructure() != null){
                    if (md.getElement(i,j).getStructure().getLabel().contains("Residential")) {
                        resCount = resCount + 1;
                    }
                }
            }
        }
        population = population * resCount;

        return population;
    }

    public int getEmploymentRate(int shopSize, int population){
        int comCount = 0;

        for(int i = 0; i < md.getHeight(); i++){
            for (int j = 0; j < md.getWidth(); j++){
                if (md.getElement(i,j).getStructure() != null){
                    if (md.getElement(i,j).getStructure().getLabel().contains("Commercial")) {
                        comCount = comCount + 1;
                    }
                }
            }
        }
        System.out.println(comCount);

        //int employmentRate = min(1, (comCount * shopSize / population));
        int employmentRate = (comCount * shopSize / population);

        return employmentRate;
    }
}
