package com.github.handioq.diberapp.ui.shops.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.event.RemoveShopEvent;
import com.github.handioq.diberapp.model.dvo.ShopDvo;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

class ShopsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shop_id)
    TextView nameView;

    @BindView(R.id.delete_button)
    ImageButton removeButtonView;

    private ShopDvo shopDvo;

    static ShopsViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopsViewHolder(view);
    }

    private ShopsViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(v1 -> {
            if (shopDvo != null) {
                // Context context = itemView.getContext();
                // context.startActivity(ProductActivity.makeIntent(context, shopDvo.getId()));
            }
        });
    }

    public void bind(final ShopDvo item) {
        shopDvo = item;
        nameView.setText(item.getName() + " - " + item.getAddress());

        removeButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new RemoveShopEvent(shopDvo));
            }
        });

        /*
        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(orderImage);
        */
    }
}