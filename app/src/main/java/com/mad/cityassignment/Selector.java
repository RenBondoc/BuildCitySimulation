package com.mad.cityassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Selector extends Fragment {

    private RecyclerView recyclerView;
    private StructureData structureData;
    private SelectorAdapter selectorAdapter;
    private Settings settings;


    public Selector(Settings settings) {
        this.settings = settings;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selector, container, false);

        structureData = new StructureData(settings.getResBuildingCost(),
                settings.getCommBuildingCost(),
                settings.getRoadBuildingCost(), settings.getMiscBuildingCost());

        selectorAdapter = new SelectorAdapter(getActivity(), structureData);

        recyclerView = (RecyclerView) view.findViewById(R.id.selectorRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(selectorAdapter);

        selectorAdapter.setOnClickListener(new SelectorAdapter.OnClickListener() {
            @Override
            public void onClickItem(int position) {
                ((MapActivity)getActivity()).setStructure(structureData.get(position));
            }
        });

        return view;
    }

}