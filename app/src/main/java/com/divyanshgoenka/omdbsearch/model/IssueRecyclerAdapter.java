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

public class IssueRecyclerAdapter extends RecyclerView.Adapter<Issue.ViewHolder> {

    LayoutInflater layoutInflater;
    ArrayList<Issue> issues;

    public IssueRecyclerAdapter(ArrayList<Issue> details) {
        this.issues = details;
    }

    @Override
    public Issue.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        Issue.ViewHolder viewHolder = new Issue.ViewHolder(layoutInflater.inflate(R.layout.detail_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Issue.ViewHolder holder, int position) {
        Issue issue = getItem(position);
        if (issue != null)
            holder.onBind(issue);
    }

    @Override
    public void onViewRecycled(Issue.ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onUnbind();
    }

    public Issue getItem(int position) {
        if (!Validations.isEmptyOrNull(issues) && position < issues.size())
            return issues.get(position);
        return null;
    }

    @Override
    public int getItemCount() {
        return Validations.isEmptyOrNull(issues) ? 0 : issues.size();
    }
}
