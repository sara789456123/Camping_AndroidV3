package com.example.camping_androidv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CampingActivityAdapter extends RecyclerView.Adapter<CampingActivityAdapter.CampingActivityViewHolder> {

    private List<CampingActivity> campingActivities;

    public CampingActivityAdapter(List<CampingActivity> campingActivities) {
        this.campingActivities = campingActivities;
    }

    @NonNull
    @Override
    public CampingActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_camping_activity, parent, false);
        return new CampingActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampingActivityViewHolder holder, int position) {
        CampingActivity activity = campingActivities.get(position);
        holder.bind(activity);
    }

    @Override
    public int getItemCount() {
        return campingActivities.size();
    }

    public class CampingActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView locationTextView;
        private TextView dateTextView;
        private Button participateButton;

        public CampingActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            participateButton = itemView.findViewById(R.id.participateButton);
        }

        public void bind(CampingActivity activity) {
            titleTextView.setText(activity.getTitle());
            locationTextView.setText(activity.getLocation());
            dateTextView.setText(activity.getDate());

            participateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implémentez la logique pour participer à l'activité
                }
            });
        }
    }
}

