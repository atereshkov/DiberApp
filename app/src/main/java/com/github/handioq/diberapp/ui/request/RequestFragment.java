package com.github.handioq.diberapp.ui.request;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.base.RecyclerViewEmptySupport;
import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dvo.RequestDvo;
import com.github.handioq.diberapp.model.dvo.ReviewDvo;
import com.github.handioq.diberapp.ui.request.interaction.RequestInteractionMvp;
import com.github.handioq.diberapp.ui.reviews.ReviewsMvp;
import com.github.handioq.diberapp.ui.reviews.adapter.ReviewsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RequestFragment extends BaseFragment implements RequestMvp.View, ReviewsMvp.View, RequestInteractionMvp.View {

    private static final String TAG = "RequestFragment";
    private static final String REQUEST_ID_KEY = "request";
    private long requestId;

    private ReviewsRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.request_content)
    LinearLayout requestContentLayout;

    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @BindView(R.id.requestInteractionProgressBar)
    ProgressBar requestInteractionProgressView;

    @BindView(R.id.requestInteractionLayout)
    LinearLayout requestInteractionLayoutView;

    @BindView(R.id.request_courier_name)
    TextView courierNameView;

    @BindView(R.id.request_courier_rating)
    TextView courierRatingView;

    @BindView(R.id.recycler_view)
    RecyclerViewEmptySupport recyclerView;

    @BindView(R.id.empty_recycler_view)
    View emptyView;

    @Inject
    ReviewsMvp.Presenter userReviewsPresenter;

    @Inject
    RequestMvp.Presenter requestPresenter;

    @Inject
    RequestInteractionMvp.Presenter acceptRequestPresenter;

    public static RequestFragment newInstance(long requestId) {
        RequestFragment fragment = new RequestFragment();

        Bundle args = new Bundle();
        args.putLong(REQUEST_ID_KEY, requestId);
        fragment.setArguments(args);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            requestId = bundle.getLong(REQUEST_ID_KEY);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        readBundle(getArguments());

        return inflater.inflate(R.layout.fragment_request, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        adapter = new ReviewsRecyclerAdapter(new ArrayList<>());
        initRecycler();

        acceptRequestPresenter.setView(this);
        userReviewsPresenter.setView(this);
        requestPresenter.setView(this);
        requestPresenter.getRequestDetails(requestId);
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(getContext()); // 1 order in a row
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);
    }

    @Override
    public void setRequest(RequestDvo request) {
        Log.i(TAG, "get request: " + request);

        bindRequestInformation(request);
        userReviewsPresenter.getUserReviews(request.getCourier().getId());
    }

    private void bindRequestInformation(RequestDvo request) {
        courierNameView.setText(request.getCourier().getFullname());
    }

    @Override
    public void showLoadRequestProgress() {
        requestContentLayout.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadRequestProgress() {
        requestContentLayout.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadRequestError(Throwable error) {
        Log.e(TAG, error.toString());
    }

    // UserReviews

    @Override
    public void showLoadReviewsProgress() {

    }

    @Override
    public void hideLoadReviewsProgress() {

    }

    @Override
    public void setReviews(List<ReviewDvo> reviews) {
        if (getActivity() != null) {
            adapter.setItems(reviews);
        }
    }

    @Override
    public void showLoadReviewsError(Throwable error) {
        Log.e(TAG, error.toString());
    }

    // RequestInteractionMVP:

    @Override
    public void onRequestAcceptSuccess(RequestDvo request) {
        Log.e(TAG, "Accept SUCCESS!!!");
        Toast.makeText(getActivity(), "Request has been accepted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestAcceptError(Throwable e) {
        Log.e(TAG, "Accept ERROR!!!");
        Toast.makeText(getActivity(), "Error during request accepting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestDeclineSuccess(RequestDvo request) {
        Log.e(TAG, "Decline SUCCESS!!!");
        Toast.makeText(getActivity(), "Request has been declined", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestDeclineError(Throwable e) {
        Log.e(TAG, "Decline ERROR!!!");
        Toast.makeText(getActivity(), "Error during request declining", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRequestInteractionProgress() {
        requestInteractionLayoutView.setVisibility(View.GONE);
        requestInteractionProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRequestInteractionProgress() {
        requestInteractionLayoutView.setVisibility(View.VISIBLE);
        requestInteractionProgressView.setVisibility(View.GONE);
    }

    // Accept / Decline request Actions

    @OnClick(R.id.button_accept)
    public void onRequestAcceptButtonClick() {
        Log.i(TAG, "Accept Button Click for request with id: " + requestId);
        RequestStatusDto status = new RequestStatusDto("Accepted");
        acceptRequestPresenter.acceptRequest(requestId, status);
    }

    @OnClick(R.id.button_decline)
    public void onRequestDecllineButtonClick() {
        Log.i(TAG, "Decline Button Click for request with id: " + requestId);
        RequestStatusDto status = new RequestStatusDto("Canceled by customer");
        acceptRequestPresenter.declineRequest(requestId, status);
    }

}