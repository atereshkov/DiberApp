package com.github.handioq.diberapp.ui.addresses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.AddressDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

class AddressesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.address_id)
    TextView addressView;

    private AddressDvo addressDvo;

    static AddressesViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressesViewHolder(view);
    }

    private AddressesViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressDvo != null) {
                    // Context context = itemView.getContext();
                    // context.startActivity(ProductActivity.makeIntent(context, addressDvo.getId()));
                }
            }
        });
    }

    public void bind(final AddressDvo item) {
        addressDvo = item;
        addressView.setText(item.getAddress());

        /*
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new AddToCartClickEvent(addressDvo));
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