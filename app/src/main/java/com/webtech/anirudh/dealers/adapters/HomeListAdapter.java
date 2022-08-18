package com.webtech.anirudh.dealers.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webtech.anirudh.dealers.DetailsActivity;
import com.webtech.anirudh.dealers.DetailsDialog;
import com.webtech.anirudh.dealers.MainActivity;
import com.webtech.anirudh.dealers.R;
import com.webtech.anirudh.dealers.models.DealerEntry;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
    public final MainActivity mainActivity;
    private List<DealerEntry> dealerList = new ArrayList<>();

    public HomeListAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.home_list_item, parent, false);
        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int i) {
        holder.dealerName.setText(dealerList.get(i).getDealerName());
        holder.gstin.setText(dealerList.get(i).getGstin());
        holder.ptin.setText(dealerList.get(i).getPtin());
        holder.itemView.setOnClickListener(view -> {
            Intent myIntent = new Intent(mainActivity, DetailsActivity.class);
            myIntent.putExtra("entry", dealerList.get(i));
            mainActivity.startActivity(myIntent);
        });
    }

    @Override
    public int getItemCount() {
        return dealerList.size();
    }

    public void setData(List<DealerEntry> dealerList) {
        this.dealerList = dealerList;
        notifyDataSetChanged();
    }

    public static class HomeListViewHolder extends RecyclerView.ViewHolder {
        public final TextView dealerName;
        public final TextView gstin;
        public final TextView ptin;

        public HomeListViewHolder(@NonNull View itemView) {
            super(itemView);
            dealerName = itemView.findViewById(R.id.dealer_name);
            gstin = itemView.findViewById(R.id.gstin);
            ptin = itemView.findViewById(R.id.ptin);
        }
    }
}
