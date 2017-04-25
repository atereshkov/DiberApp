package com.github.handioq.diberapp.ui.request;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dvo.RequestDvo;

import javax.inject.Inject;

import butterknife.BindView;

public class RequestFragment extends BaseFragment implements RequestMvp.View {

    private static final String TAG = "RequestFragment";
    private static final String REQUEST_ID_KEY = "request";
    private long requestId;

    @BindView(R.id.request_content)
    LinearLayout requestContentLayout;

    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @BindView(R.id.request_courier_name)
    TextView courierNameView;

    @BindView(R.id.request_courier_rating)
    TextView courierRatingView;

    @Inject
    RequestMvp.Presenter requestPresenter;

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

        requestPresenter.setView(this);
        requestPresenter.getRequestDetails(requestId);
    }

    @Override
    public void setRequest(RequestDvo request) {
        Log.i(TAG, "get request: " + request);

        bindRequestInformation(request);
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

}