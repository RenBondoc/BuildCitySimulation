package com.mad.cityassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GameData gd;
    private MyAdapter adapter;
    private InfoBar infoBar;
    private Selector selector;
    private Structure structure;
    private Settings settings;
    private Button nextDay, backMenu;
    private TextView cityName;
    private DatabaseInteraction db;

    private final static int REQUEST_CODE_DETAILS = 1;

    public static Intent getIntent(Context c, Settings settings, GameData gameData) {
        Intent intent = new Intent(c, MapActivity.class);
        intent.putExtra("Settings", settings);
        intent.putExtra("Game_Data", gameData);
        return intent;
    }

    public void setStructure(Structure structure){
        this.structure = structure;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        nextDay = (Button) findViewById(R.id.run);
        backMenu = (Button) findViewById(R.id.backMenu);
        cityName = (TextView) findViewById(R.id.cityName);

        Intent intent = getIntent();
        settings = (Settings) intent.getSerializableExtra("Settings");
        int height = settings.getMapHeight();
        int width = settings.getMapWidth();

        gd = (GameData) intent.getSerializableExtra("Game_Data");

        FragmentManager fm = getSupportFragmentManager();

        cityName.setText(settings.getCityName());

        buildInfoFragment(fm);
        buildRecyclerView(height, width);
        buildSelectorFragment(fm);

        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int population = gd.getPopulation(settings.getFamilySize());
                if (population < 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("Unable to Move to Next Day!");
                    builder.setMessage("Population = 0, please add Residential Buildings");
                    builder.show();
                }
                else {
                    int employmentRate = gd.getEmploymentRate(settings.getShopSize(), population);
                    int addMoney = (int) (population * (employmentRate * settings.getSalary() * settings.getTaxRate() - settings.getServiceCost()));
                    int finalMoney = gd.getMoney() + addMoney;
                    gd.setMoney(finalMoney);
                    gd.setGameTime(gd.getGameTime() + 1);
                    infoBar.setMoneyText(finalMoney);
                    infoBar.setGameTimeText(gd.getGameTime());
                    infoBar.setAddMoney(addMoney);
                    infoBar.setEmploymentRateText(employmentRate);
                    infoBar.setPopulationText(population);
                }
            }
        });

        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnData = new Intent();
                returnData.putExtra("Game_Data_Update", gd);
                setResult(RESULT_OK, returnData);
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_DETAILS) {
            int row = Integer.parseInt(data.getStringExtra("Structure_Row"));
            int col = Integer.parseInt(data.getStringExtra("Structure_Col"));
            String name = data.getStringExtra("Structure_Name");
            gd.getMapData().getElement(row,col).getStructure().setName(name);
        }
    }

    public void buildRecyclerView(int height, int width) {

        adapter = new MyAdapter(MapActivity.this, height, width, gd.getMapData());

        recyclerView = (RecyclerView) findViewById(R.id.mapRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(
                this,
                gd.getMapData().getHeight(),
                GridLayoutManager.HORIZONTAL,
                false));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int row = position % gd.getMapData().getHeight();
                int col = position / gd.getMapData().getHeight();

                if (infoBar.isTriggered()==true) {

                    if (gd.getMapData().getElement(row,col).getStructure() != null) {
                        startActivityForResult(Details.getIntent(MapActivity.this, Integer.toString(row), Integer.toString(col), gd.getMapData().getElement(row,col).getStructure()), REQUEST_CODE_DETAILS);

                    }
                    infoBar.setTriggered(false);
                    infoBar.setDetButtonText("Details");
                    adapter.notifyItemChanged(position);
                }
                else if (infoBar.isDelTriggered()==true){
                    gd.getMapData().getElement(row,col).setStructure(null);
                    infoBar.setDelTriggered(false);
                    infoBar.setDeleteButtonText("Remove");
                    adapter.notifyItemChanged(position);
                }
                else {
                    if (structure != null) {
                        if (gd.getMapData().getElement(row, col).isBuildable() == true && gd.getMapData().getElement(row, col).getStructure() == null) {
                            if (adjacentRoads(gd.getMapData(), row, col) == true) {
                                gd.getMapData().getElement(row, col).setStructure(structure);

                                int newMoney = gd.getMoney() - structure.getCost();
                                gd.setMoney(newMoney);
                                infoBar.setMoneyText(newMoney);
                                adapter.notifyItemChanged(position);
                                structure = null;
                            } else if (structure.getLabel().contains("Road")) {
                                gd.getMapData().getElement(row, col).setStructure(structure);

                                int newMoney = gd.getMoney() - structure.getCost();
                                gd.setMoney(newMoney);
                                infoBar.setMoneyText(newMoney);
                                adapter.notifyItemChanged(position);
                                structure = null;
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

                                builder.setTitle("Unable to Build!");
                                builder.setMessage("Must build a road first!");
                                builder.setCancelable(true);
                                builder.show();
                            }
                        }
                    }
                }
            }
        });

    }

    private boolean adjacentRoads(MapData md, int row, int col){
        boolean adjacentToRoad = false;
        boolean isEdge = false;
        String labelTop, labelBottom, labelRight, labelLeft;
        int top, bottom, left, right;

        top = row - 1;
        bottom = row + 1;
        left = col - 1;
        right = col + 1;

        if (top < 0 || bottom < 0 || left < 0 || right < 0) {
            isEdge = true;
        }


        if(md.getElement(top,col).getStructure() != null && !isEdge) {
            labelTop = md.getElement(top, col).getStructure().getLabel();
            if (labelTop.contains("Road")) {
                adjacentToRoad = true;
            }
        }
        else if(md.getElement(bottom,col).getStructure() != null && !isEdge) {
            labelBottom = md.getElement(bottom, col).getStructure().getLabel();
            if (labelBottom.contains("Road")) {
                adjacentToRoad = true;
            }
        }
        else if(md.getElement(row, right).getStructure() != null && !isEdge) {
            labelRight = md.getElement(row, right).getStructure().getLabel();
            if (labelRight.contains("Road")) {
                adjacentToRoad = true;
            }
        }
        else if(md.getElement(row, left).getStructure() != null && !isEdge) {
            labelLeft = md.getElement(row, left).getStructure().getLabel();
            if (labelLeft.contains("Road")) {
                adjacentToRoad = true;
            }
        }

        return adjacentToRoad;
    }

    private void buildInfoFragment(FragmentManager fm){
        infoBar = (InfoBar) fm.findFragmentById(R.id.info_bar);
        if (infoBar == null) {
            infoBar = new InfoBar(gd.getMoney(), gd.getGameTime());
            fm.beginTransaction().add(R.id.info_bar, infoBar).commit();
        }
    }

    private void buildSelectorFragment(FragmentManager fm) {
        selector = (Selector) fm.findFragmentById(R.id.selector);
        if (selector == null) {
            selector = new Selector(settings);
            fm.beginTransaction().add(R.id.selector, selector).commit();

        }
    }

    public void gameOver(){
        gd.getMapData().regenerate(settings.getMapHeight(), settings.getMapWidth());
        finish();
    }

}