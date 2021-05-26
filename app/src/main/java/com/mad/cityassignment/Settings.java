package com.mad.cityassignment;

import java.io.Serializable;

public class Settings implements Serializable {

    private int mapWidth, mapHeight, initialMoney, familySize, shopSize, salary, serviceCost,
            resBuildingCost, commBuildingCost, roadBuildingCost, miscBuildingCost;
    private double taxRate;
    private String cityName;

    public Settings() {
        this.cityName = "Default City";
        this.mapWidth = 50;
        this.mapHeight = 10;
        this.initialMoney = 1000;
        this.familySize = 4;
        this.shopSize = 6;
        this.salary = 10;
        this.serviceCost = 2;
        this.resBuildingCost = 100;
        this.commBuildingCost = 500;
        this.roadBuildingCost = 20;
        this.miscBuildingCost = 10;
        this.taxRate = 0.3;
    }

    public Settings(String name, int mapWidth, int mapHeight, int initialMoney, int familySize, int shopSize,
                     int salary, int serviceCost, int resBuildingCost, int commBuildingCost,
                     int roadBuildingCost, int miscBuildingCost, double taxRate){
        this.cityName = name;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.initialMoney = initialMoney;
        this.familySize = familySize;
        this.shopSize = shopSize;
        this.salary = salary;
        this.serviceCost = serviceCost;
        this.resBuildingCost = resBuildingCost;
        this.commBuildingCost = commBuildingCost;
        this.roadBuildingCost = roadBuildingCost;
        this.miscBuildingCost = miscBuildingCost;
        this.taxRate = taxRate;
    }

    public String getCityName() {
        return cityName;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public int getCommBuildingCost() {
        return commBuildingCost;
    }

    public int getFamilySize() {
        return familySize;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public int getResBuildingCost() {
        return resBuildingCost;
    }

    public int getRoadBuildingCost() {
        return roadBuildingCost;
    }

    public int getSalary() {
        return salary;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public int getShopSize() {
        return shopSize;
    }

    public int getMiscBuildingCost() {
        return miscBuildingCost;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public void setInitialMoney(int initialMoney) {
        this.initialMoney = initialMoney;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCommBuildingCost(int commBuildingCost) {
        this.commBuildingCost = commBuildingCost;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMiscBuildingCost(int miscBuildingCost) {
        this.miscBuildingCost = miscBuildingCost;
    }

    public void setResBuildingCost(int resBuildingCost) {
        this.resBuildingCost = resBuildingCost;
    }

    public void setRoadBuildingCost(int roadBuildingCost) {
        this.roadBuildingCost = roadBuildingCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setShopSize(int shopSize) {
        this.shopSize = shopSize;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}

