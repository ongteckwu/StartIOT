package com.example.ongteckwu.iotproj.modules;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ongteckwu.iotproj.R;

import java.util.List;

/**
 * Created by ongteckwu on 30/11/16.
 */
public class ModuleRVAdapter extends RecyclerView.Adapter<ModuleRVAdapter.ModuleViewHolder> {
    List<Module> modules;

    public ModuleRVAdapter(List<Module> modules){
        this.modules = modules;
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView moduleName;
        TextView moduleNumber;
        ImageView modulePhoto;

        ModuleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            moduleName = (TextView)itemView.findViewById(R.id.module_name);
            moduleNumber = (TextView)itemView.findViewById(R.id.module_id);
            modulePhoto = (ImageView)itemView.findViewById(R.id.module_pic);
        }
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_card, viewGroup, false);
        ModuleViewHolder mvh = new ModuleViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder moduleViewHolder, int i) {
        moduleViewHolder.moduleName.setText(modules.get(i).getModuleName());
        moduleViewHolder.moduleNumber.setText(modules.get(i).getModuleNumber());
        moduleViewHolder.modulePhoto.setImageResource(modules.get(i).getImageId());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
