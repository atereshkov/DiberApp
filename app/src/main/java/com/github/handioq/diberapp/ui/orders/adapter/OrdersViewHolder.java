package com.github.handioq.diberapp.ui.orders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.ui.order.OrderActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

class OrdersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.order_id)
    TextView orderIdView;

    @BindView(R.id.order_status)
    TextView statusView;

    @BindView(R.id.order_date)
    TextView dateView;

    @BindView(R.id.order_from)
    TextView addressFromView;

    @BindView(R.id.order_to)
    TextView orderToView;

    private OrderDvo orderDvo;

    static OrdersViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new OrdersViewHolder(view);
    }

    private OrdersViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDvo != null) {
                    Context context = itemView.getContext();
                    context.startActivity(OrderActivity.makeIntent(context, orderDvo.getId()));
                }
            }
        });
    }

    public void bind(final OrderDvo item) {
        orderDvo = item;
        orderIdView.setText(String.valueOf(item.getId()));
        statusView.setText(item.getStatus());
        dateView.setText(item.getDate());
        addressFromView.setText(item.getAddress().getCountry() + ", " + item.getAddress().getAddress());
        orderToView.setText(item.getShop().getAddress());

        /*
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new AddToCartClickEvent(orderDvo));
            }
        });
        */

        //priceView.setText(itemView.getContext().getString(R.string.order_price, item.getPrice()));
        //priceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        /*
        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(orderImage);
        */
    }
}