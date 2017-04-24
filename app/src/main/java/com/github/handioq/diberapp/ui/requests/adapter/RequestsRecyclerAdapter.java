package com.github.handioq.diberapp.ui.requests.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.diberapp.model.dvo.RequestDvo;

import java.util.List;

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private List<RequestDvo> items;

    public RequestsRecyclerAdapter(List<RequestDvo> items) {
        this.items = items;
    }

    @Override
    public RequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RequestsViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final RequestsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<RequestDvo> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<RequestDvo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<RequestDvo> getItems() {
        return items;
    }


    public void clearItems() {
        this.items.clear();
    }

}