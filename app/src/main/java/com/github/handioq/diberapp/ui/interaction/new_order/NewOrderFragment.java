package com.github.handioq.diberapp.ui.interaction.new_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class NewOrderFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.spinner_addresses)
    Spinner addrSpinnerView;

    @BindView(R.id.address_from_edittext)
    AutoCompleteTextView addressFromEditView;

    public static NewOrderFragment newInstance() {
        NewOrderFragment fragment = new NewOrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_order, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);


        initAddressesSpiner();
        initShopsSpinner();
    }

    private void initAddressesSpiner() {
        // stub
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("Home");
        addresses.add("Work");
        //addresses.add("Some big big big name of address!!!");

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, addresses);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addrSpinnerView.setAdapter(countryAdapter);
        //addrSpinnerView.setSelection(countryAdapter.getPosition();
    }

    private void initShopsSpinner() {
        ArrayList<String> shops = new ArrayList<>();
        shops.add("Shop number 1");
        shops.add("Nemiga");
        shops.add("Raduga");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice, shops);
        addressFromEditView.setThreshold(2);
        addressFromEditView.setAdapter(adapter);

        addressFromEditView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // todo send query to server or smth
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
