package com.example.camping_androidv3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.camping_androidv3.Model.CampingActivity;

import java.util.List;

public class CampingActivityAdapter extends RecyclerView.Adapter<CampingActivityAdapter.ViewHolder> {
    private List<CampingActivity> activities;
    private Context context;

    public CampingActivityAdapter(Context context, List<CampingActivity> activities) {
        this.context = context;
        this.activities = activities;
    }

    public void setActivities(List<CampingActivity> activities) {
        this.activities = activities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_camping_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CampingActivity activity = activities.get(position);

        holder.nomAnimation.setText(activity.getNomAnimation());
        holder.nomLieu.setText(activity.getNomLieu());
        holder.dateHeure.setText(activity.getDateHeure());
        holder.descriptif.setText(activity.getDescriptifAnimation());

        Log.d("ADAPTER", "Titre: " + activity.getNomAnimation());
        Log.d("ADAPTER", "Lieu: " + activity.getNomLieu());
        Log.d("ADAPTER", "Date: " + activity.getDateHeure());
        Log.d("ADAPTER", "Desc: " + activity.getDescriptifAnimation());

        // Chargement de l'image
        Glide.with(context)
                .load(activity.getImg())
                .placeholder(R.drawable.default_image)
                .into(holder.imageView);

        // Gestion du bouton "Participer"
        holder.btnParticiper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, InscriptionActivity.class);
                //recup infos en param intent
                intent.putExtra("titre", activity.getNomAnimation());
                intent.putExtra("lieu", activity.getNomLieu());
                intent.putExtra("date", activity.getDateHeure());
                intent.putExtra("description", activity.getDescriptifAnimation());
                intent.putExtra("image", activity.getImg());
                intent.putExtra("idCreneau", activity.getIdCreneau());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities != null ? activities.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomAnimation, nomLieu, dateHeure, descriptif;
        ImageView imageView;
        Button btnParticiper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomAnimation = itemView.findViewById(R.id.tvNomAnimation);
            nomLieu = itemView.findViewById(R.id.tvLieu);
            dateHeure = itemView.findViewById(R.id.tvDateHeure);
            descriptif = itemView.findViewById(R.id.tvDescriptif);
            imageView = itemView.findViewById(R.id.imageView);
            btnParticiper = itemView.findViewById(R.id.btnParticiper);
        }
    }
}
