package com.github.handioq.diberapp.ui.shops.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.diberapp.model.dvo.ShopDvo;

import java.util.List;

public class ShopsRecyclerAdapter extends RecyclerView.Adapter<ShopsViewHolder> {

    private List<ShopDvo> items;

    public ShopsRecyclerAdapter(List<ShopDvo> items) {
        this.items = items;
    }

    @Override
    public ShopsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShopsViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final ShopsViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<ShopDvo> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<ShopDvo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<ShopDvo> getItems() {
        return items;
    }


    public void clearItems() {
        this.items.clear();
    }

}