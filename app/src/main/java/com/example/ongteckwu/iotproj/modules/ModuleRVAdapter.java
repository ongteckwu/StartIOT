package com.example.ongteckwu.iotproj.modules;


import android.content.Context;
import android.content.Intent;
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
    private List<Module> modules;
    private Context con;

    public ModuleRVAdapter(List<Module> modules, Context con){
        this.modules = modules;
        this.con = con;
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView moduleName;
        TextView moduleNumber;
        ImageView modulePhoto;
        TextView moduleFeatures;

        ModuleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            moduleName = (TextView)itemView.findViewById(R.id.module_name);
            moduleNumber = (TextView)itemView.findViewById(R.id.module_id);
            modulePhoto = (ImageView)itemView.findViewById(R.id.module_pic);
            moduleFeatures = (TextView) itemView.findViewById(R.id.module_features);
        }

        public CardView getCv() {
            return cv;
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
    public void onBindViewHolder(ModuleViewHolder moduleViewHolder, final int i) {
        moduleViewHolder.moduleName.setText(modules.get(i).getModuleName());
        moduleViewHolder.moduleNumber.setText(modules.get(i).getModuleNumberWithID());
        moduleViewHolder.modulePhoto.setImageResource(modules.get(i).getImageId());
        moduleViewHolder.moduleFeatures.setText(modules.get(i).getFeatures());
        moduleViewHolder.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.startActivity(new Intent(con, modules.get(i).getActivity())
                                    .putExtra("module", modules.get(i))
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
