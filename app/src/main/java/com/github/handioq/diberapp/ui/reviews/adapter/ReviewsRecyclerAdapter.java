package com.github.handioq.diberapp.ui.reviews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.diberapp.model.dvo.ReviewDvo;

import java.util.List;

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {

    private List<ReviewDvo> items;

    public ReviewsRecyclerAdapter(List<ReviewDvo> items) {
        this.items = items;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewsViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final ReviewsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<ReviewDvo> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<ReviewDvo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<ReviewDvo> getItems() {
        return items;
    }


    public void clearItems() {
        this.items.clear();
    }

}