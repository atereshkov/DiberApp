package com.github.handioq.diberapp.ui.requests.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.ui.request.RequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.request_courier)
    TextView courierNameView;

    @BindView(R.id.request_date)
    TextView requestDateView;

    private RequestDvo requestDvo;

    static RequestsViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new RequestsViewHolder(view);
    }

    private RequestsViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(v1 -> {
            if (requestDvo != null) {
                Context context = itemView.getContext();
                context.startActivity(RequestActivity.makeIntent(context, requestDvo.getId()));
            }
        });
    }

    public void bind(final RequestDvo item) {
        requestDvo = item;
        courierNameView.setText(item.getCourier().getFullname());

        //removeButtonView.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
                //EventBus.getDefault().post(new RemoveShopEvent(shopDvo));
        //    }
        //});

        /*
        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(orderImage);
        */
    }
}