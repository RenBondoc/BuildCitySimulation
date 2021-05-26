package com.mad.cityassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    private TextView rowText, colText, structureText;
    private ImageView structureImage;
    private EditText structureName;
    private Button backButton, setNameButton;
    private Structure structure;

    public static Intent getIntent(Context c, String row, String col, Structure structure) {
        Intent intent = new Intent(c, Details.class);
        intent.putExtra("Row", row);
        intent.putExtra("Col", col);
        intent.putExtra("Structure", structure);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        final String row = intent.getStringExtra("Row");
        final String col = intent.getStringExtra("Col");
        structure = (Structure) intent.getSerializableExtra("Structure");


        rowText = (TextView) findViewById(R.id.rowPosition);
        rowText.setText("Row: [" + row + "]");

        colText = (TextView) findViewById(R.id.colPosition);
        colText.setText("Col: [" + col + "]");

        structureText = (TextView) findViewById(R.id.structureType);
        structureText.setText(structure.getLabel());

        structureImage = (ImageView) findViewById(R.id.structureImage);
        structureImage.setImageResource(structure.getDrawableId());

        structureName = (EditText) findViewById(R.id.structureName);
        if (structure.getName() != null) {
            structureName.setText(structure.getName());
        }
        else {
            structureName.setText(structure.getLabel());
        }

        setNameButton = (Button) findViewById(R.id.setNameButton);
        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                structure.setName(structureName.getText().toString());
                structureName.setText(structure.getName());
                Intent returnData = new Intent();
                returnData.putExtra("Structure_Name", structureName.getText().toString());
                returnData.putExtra("Structure_Row", row);
                returnData.putExtra("Structure_Col", col);
                setResult(Activity.RESULT_OK, returnData);

            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}