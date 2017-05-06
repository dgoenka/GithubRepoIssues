package com.divyanshgoenka.omdbsearch.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.divyanshgoenka.omdbsearch.R;
import com.divyanshgoenka.omdbsearch.util.Validations;

import java.util.ArrayList;

/**
 * Created by divyanshgoenka on 04/05/17.
 */

public class DetailRecyclerAdapter extends RecyclerView.Adapter<Detail.ViewHolder> {

    LayoutInflater layoutInflater;
    ArrayList<Detail> details;

    public DetailRecyclerAdapter(ArrayList<Detail> details) {
        this.details = details;
    }

    @Override
    public Detail.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        Detail.ViewHolder viewHolder = new Detail.ViewHolder(layoutInflater.inflate(R.layout.detail_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Detail.ViewHolder holder, int position) {
        Detail detail = getItem(position);
        if (detail != null)
            holder.onBind(detail);
    }

    @Override
    public void onViewRecycled(Detail.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onUnbind();
    }

    public Detail getItem(int position) {
        if (!Validations.isEmptyOrNull(details) && position < details.size())
            return details.get(position);
        return null;
    }

    @Override
    public int getItemCount() {
        return Validations.isEmptyOrNull(details) ? 0 : details.size();
    }
}
