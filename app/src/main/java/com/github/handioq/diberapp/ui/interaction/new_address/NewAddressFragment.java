package com.github.handioq.diberapp.ui.interaction.new_address;

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
import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.util.AuthPreferences;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewAddressFragment extends BaseFragment implements NewAddressMvp.View {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @BindView(R.id.new_address_scroll_view)
    ScrollView contentScrollView;

    @BindView(R.id.name)
    EditText nameEditView;

    @BindView(R.id.country)
    EditText countryEditView;

    @BindView(R.id.city)
    EditText cityEditView;

    @BindView(R.id.region)
    EditText regionEditView;

    @BindView(R.id.address)
    EditText addressEditView;

    @BindView(R.id.postalcode)
    EditText postalCodeEditView;

    @BindView(R.id.phone)
    EditText phoneEditView;

    @Inject
    NewAddressMvp.Presenter newAddressPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static NewAddressFragment newInstance() {
        return new NewAddressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_address, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        newAddressPresenter.setView(this);
    }

    @OnClick(R.id.create_address_button)
    public void onCreateAddressClick() {
        String name = nameEditView.getText().toString();
        String country = addressEditView.getText().toString();
        String city = cityEditView.getText().toString();
        String region = regionEditView.getText().toString();
        Integer postalCode = Integer.parseInt(postalCodeEditView.getText().toString());
        String address = addressEditView.getText().toString();
        String phone =phoneEditView.getText().toString();

        AddressDto shopDto = new AddressDto(name, postalCode, country, city, region, address, phone);

        // todo add all checks for validity of address
        newAddressPresenter.addAddress(authPreferences.getUserId(), shopDto);
    }

    @Override
    public void showAddAddressProgress() {
        progressView.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideAddAddressProgress() {
        progressView.setVisibility(View.GONE);
        contentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddressAdded(AddressDvo addressDvo) {
        Log.i(TAG, "Shop added: " + addressDvo.toString());
        Toast.makeText(getContext(), "Shop added!", Toast.LENGTH_LONG).show();
        getActivity().finish();

        // todo send eventbus event
    }

    @Override
    public void onAddAddressError(Throwable error) {
        Log.e(TAG, error.toString());
    }

}