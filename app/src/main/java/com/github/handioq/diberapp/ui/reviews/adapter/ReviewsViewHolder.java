package com.github.handioq.diberapp.ui.reviews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dvo.ReviewDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.review_user_name)
    TextView reviewUserName;

    @BindView(R.id.review)
    TextView reviewTextView;

    private ReviewDvo reviewDvo;

    static ReviewsViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewsViewHolder(view);
    }

    private ReviewsViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(v1 -> {
            if (reviewDvo != null) {
                //Context context = itemView.getContext();
                //context.startActivity(ReviewActivity.makeIntent(context, reviewDvo.getId()));
            }
        });
    }

    public void bind(final ReviewDvo item) {
        reviewDvo = item;
        reviewUserName.setText(item.getUser().getFullname());
        reviewTextView.setText(item.getReview());

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