package com.github.handioq.diberapp.ui.addresses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.diberapp.model.dvo.AddressDvo;

import java.util.List;

public class AddressesRecyclerAdapter extends RecyclerView.Adapter<AddressesViewHolder> {

    private List<AddressDvo> items;

    public AddressesRecyclerAdapter(List<AddressDvo> items) {
        this.items = items;
    }

    @Override
    public AddressesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AddressesViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final AddressesViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<AddressDvo> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<AddressDvo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<AddressDvo> getItems() {
        return items;
    }

    public void clearItems() {
        this.items.clear();
    }

}