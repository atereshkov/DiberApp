package com.github.handioq.diberapp.ui.orders.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.diberapp.model.dvo.OrderDvo;

import java.util.List;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersViewHolder> {

    private List<OrderDvo> items;

    public OrdersRecyclerAdapter(List<OrderDvo> items) {
        this.items = items;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OrdersViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final OrdersViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<OrderDvo> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<OrderDvo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<OrderDvo> getItems() {
        return items;
    }
}