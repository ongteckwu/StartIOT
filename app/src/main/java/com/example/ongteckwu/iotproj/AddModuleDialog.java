package com.example.ongteckwu.iotproj;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ongteckwu.iotproj.modules.CategoryType;
import com.example.ongteckwu.iotproj.modules.DataModType;
import com.example.ongteckwu.iotproj.modules.ModType;
import com.example.ongteckwu.iotproj.modules.ModulesList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ongteckwu on 12/12/16.
 */
public class AddModuleDialog extends DialogFragment implements AdapterView.OnItemSelectedListener{
    private ArrayAdapter<String> categoriesArray;
    private ArrayAdapter<String> typesArray;
    private ArrayAdapter<String> modsArray;
    private View typeSpinnerView;
    private View modSpinnerView;
    private List<String> categories;
    private List<String> dataTypes;
    private List<String> mods;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // initialize views

        View view = inflater.inflate(R.layout.add_module_dialog, null);
        typeSpinnerView = view.findViewById(R.id.module_type_spinner);
        modSpinnerView = view.findViewById(R.id.module_mod_spinner);

        // Spinner logic
        dataTypes = new ArrayList<>();
        dataTypes.add("A");
        dataTypes.add("B");
        dataTypes.add("C");

        Spinner spinType = (Spinner) typeSpinnerView;
        typesArray = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, dataTypes);
        typesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(typesArray);
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (selectedItem.equals("Data")) {
                    typeSpinnerView.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Door")) {
                    typeSpinnerView.setVisibility(View.GONE);
                    modSpinnerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categories = new ArrayList<>();
        for (CategoryType ct : ModulesList.cat) {
            categories.add(ct.getName());
        }
        Spinner spinCat = (Spinner) view.findViewById(R.id.module_category_spinner);
        categoriesArray = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, categories);
        categoriesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCat.setAdapter(categoriesArray);

        // Inflate and set the layout for the dialo
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle(getResources().getString(R.string.add_module_dialog_title))
                .setView(view)
                .setPositiveButton("Just add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });



        return builder.create();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String selectedItem = (String) parent.getItemAtPosition(pos);
        Log.i("DATA", selectedItem);
        switch(view.getId()) {
            case R.id.module_category_spinner: {
                if (selectedItem.equals("Data")) {
                    typeSpinnerView.setVisibility(View.VISIBLE);
                }
                else if (selectedItem.equals("Door")) {
                    typeSpinnerView.setVisibility(View.GONE);
                    modSpinnerView.setVisibility(View.GONE);
                }

                break;
            }
            case R.id.module_type_spinner: {
                mods = new ArrayList<>();
                if (selectedItem.equals("A")) {
                    for (ModType dmt: ModulesList.A) {
                        mods.add(dmt.getName());
                    }
                }
                else if (selectedItem.equals("B")) {
                    for (ModType dmt: ModulesList.B) {
                        mods.add(dmt.getName());
                    }
                }
                else if (selectedItem.equals("C")) {
                    for (ModType dmt: ModulesList.C) {
                        mods.add(dmt.getName());
                    }
                }
                Spinner spinMod = (Spinner) modSpinnerView;
                typesArray = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mods);
                typesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinMod.setAdapter(typesArray);

                modSpinnerView.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
