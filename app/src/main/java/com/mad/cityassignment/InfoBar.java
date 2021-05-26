package com.mad.cityassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InfoBar extends Fragment {

    private TextView moneyText, addMoney, gameTimeText, populationText, employmentRateText;
    private int money, gameTime;
    private Button details, delete;
    private boolean triggered, delTriggered;

    public InfoBar(int money, int gameTime) {
        this.money = money;
        this.triggered = false;
        this.delTriggered = false;
        this.gameTime = gameTime;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    public void setMoneyText(int money){
        this.money = money;
        if (this.money < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setCancelable(false);
            builder.setTitle("GAME OVER!");
            builder.setMessage("You Have Gone Bankcrupt");
            builder.setPositiveButton("Go Back to Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((MapActivity)getActivity()).gameOver();
                }
            });

            builder.show();
        }
        moneyText.setText("$"+Integer.toString(this.money));
    }

    public void setAddMoney(int money){
        addMoney.setText("+ $" + Integer.toString(money));
    }

    public void setGameTimeText(int time) {
        this.gameTime = time;
        gameTimeText.setText(Integer.toString(this.gameTime));
    }

    public void setPopulationText(int population) {
        populationText.setText("population: " + Integer.toString(population));
    }

    public void setEmploymentRateText(int employmentRate) {
        employmentRateText.setText("Employment rate: %" + Integer.toString(employmentRate));
    }


    public void setDetButtonText(String string){
        this.details.setText(string);
    }

    public void setDeleteButtonText(String string){
        this.delete.setText(string);
    }

    public boolean isTriggered() {
        return this.triggered;
    }

    public boolean isDelTriggered() {
        return delTriggered;
    }

    public void setDelTriggered(boolean delTriggered) {
        this.delTriggered = delTriggered;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_bar, container, false);

        moneyText = (TextView) view.findViewById(R.id.moneyText);
        moneyText.setText("$"+Integer.toString(this.money));

        addMoney = (TextView) view.findViewById(R.id.additionalMoney);
        addMoney.setText("+ $0");

        gameTimeText = (TextView) view.findViewById(R.id.dayTime);
        gameTimeText.setText(Integer.toString(gameTime));

        employmentRateText = (TextView) view.findViewById(R.id.employmentRateText);
        populationText = (TextView) view.findViewById(R.id.populationText);

        details = (Button) view.findViewById(R.id.detailsButton);
        delete = (Button) view.findViewById(R.id.delete);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (triggered == false) {
                    setDetButtonText("Cancel");
                    triggered = true;
                }
                else if (triggered == true) {
                    setDetButtonText("Details");
                    triggered = false;
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delTriggered == false) {
                    setDeleteButtonText("Cancel");
                    delTriggered = true;
                }
                else if (delTriggered == true){
                    setDeleteButtonText("Remove");
                    delTriggered = false;
                }
            }
        });

        return view;
    }
}