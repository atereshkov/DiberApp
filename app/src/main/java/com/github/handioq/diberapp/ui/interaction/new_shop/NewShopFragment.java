package com.github.handioq.diberapp.ui.interaction.new_shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.util.AuthPreferences;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewShopFragment extends BaseFragment implements NewShopMvp.View {

    private final String TAG = this.getClass().getSimpleName();
    private ShopDto shopDto = new ShopDto();

    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @BindView(R.id.new_shop_scroll_view)
    ScrollView contentScrollView;

    @BindView(R.id.name)
    EditText nameEditView;

    @BindView(R.id.address)
    EditText addressEditView;

    @Inject
    NewShopMvp.Presenter newShopPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static NewShopFragment newInstance() {
        return new NewShopFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_shop, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        newShopPresenter.setView(this);
    }

    @OnClick(R.id.create_shop_button)
    public void onCreateShopClick() {
        String shopName = nameEditView.getText().toString();
        String shopAddress = addressEditView.getText().toString();

        ShopDto shopDto = new ShopDto(shopName, shopAddress);

        // todo add all checks for validity of order
        newShopPresenter.addShop(authPreferences.getUserId(), shopDto);
    }

    @Override
    public void showAddShopProgress() {
        progressView.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideAddShopProgress() {
        progressView.setVisibility(View.GONE);
        contentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShopAdded(ShopDvo shopDvo) {
        Log.i(TAG, "Shop added: " + shopDvo.toString());
        Toast.makeText(getContext(), "Shop added!", Toast.LENGTH_LONG).show();
        getActivity().finish();

        // todo send eventbus event
    }

    @Override
    public void onAddShopError(Throwable error) {
        Log.e(TAG, error.toString());
    }

}
