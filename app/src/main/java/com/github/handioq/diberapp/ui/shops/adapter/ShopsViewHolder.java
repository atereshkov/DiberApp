package com.github.handioq.diberapp.ui.shops.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.ShopDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

class ShopsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.order_name)
    TextView nameView;

    @BindView(R.id.order_price)
    TextView priceView;

    @BindView(R.id.order_image)
    ImageView orderImage;

    private ShopDvo shopDvo;

    static ShopsViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item, parent, false);
        return new ShopsViewHolder(view);
    }

    private ShopsViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopDvo != null) {
                    // Context context = itemView.getContext();
                    // context.startActivity(ProductActivity.makeIntent(context, shopDvo.getId()));
                }
            }
        });
    }

    public void bind(final ShopDvo item) {
        shopDvo = item;
        nameView.setText(item.getName() + " - " + item.getAddress());

        /*
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new AddToCartClickEvent(shopDvo));
            }
        });
        */

        /*
        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(orderImage);
        */
    }
}