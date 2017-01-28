package com.github.handioq.diberapp.ui.orders.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.OrderDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

class OrdersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.order_name)
    TextView nameView;

    @BindView(R.id.order_price)
    TextView priceView;

    @BindView(R.id.order_image)
    ImageView orderImage;

    private OrderDvo orderDvo;

    static OrdersViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item, parent, false);
        return new OrdersViewHolder(view);
    }

    private OrdersViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDvo != null) {
                    // Context context = itemView.getContext();
                    // context.startActivity(ProductActivity.makeIntent(context, orderDvo.getId()));
                }
            }
        });
    }

    public void bind(final OrderDvo item) {
        orderDvo = item;
        nameView.setText(item.getDescription());

        /*
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new AddToCartClickEvent(orderDvo));
            }
        });
        */

        priceView.setText(itemView.getContext().getString(R.string.order_price, item.getPrice()));
        priceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        /*
        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(orderImage);
        */
    }
}