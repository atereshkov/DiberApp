package com.github.handioq.diberapp.ui.interaction.new_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dto.NewOrderDto;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.ui.addresses.AddressesMvp;
import com.github.handioq.diberapp.ui.dialog.CustomDatePickerDialog;
import com.github.handioq.diberapp.ui.dialog.CustomTimePickerDialog;
import com.github.handioq.diberapp.ui.dialog.NewShopDialog;
import com.github.handioq.diberapp.ui.shops.ShopsMvp;
import com.github.handioq.diberapp.util.AuthPreferences;
import com.github.handioq.diberapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewOrderFragment extends BaseFragment implements NewOrderMvp.View, NewShopDialog.DialogListener,
    ShopsMvp.View, AddressesMvp.View, CustomDatePickerDialog.DialogListener, CustomTimePickerDialog.DialogListener {

    private static final String ADD_SHOP_DIALOG = "NewShopDialog";
    private static final String DATE_PICKER_DIALOG = "DatePickerDialog";
    private static final String TIME_PICKER_DIALOG = "TimePickerDialog";
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.spinner_addresses)
    Spinner addrSpinnerView;

    @BindView(R.id.spinner_delivery)
    Spinner shopsSpinnerView;

    @BindView(R.id.progress_addresses_spinner)
    ProgressBar progressBarAddresses;

    @BindView(R.id.progress_shops_spinner)
    ProgressBar progressBarShops;

    @Inject
    ShopsMvp.Presenter shopsPresenter;

    @Inject
    AddressesMvp.Presenter addressesPresenter;

    @Inject
    NewOrderMvp.Presenter newOrderPresenter;

    @Inject
    AuthPreferences authPreferences;

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
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        newOrderPresenter.setView(this);

        shopsPresenter.setView(this);
        shopsPresenter.getUserShops(authPreferences.getUserId());

        addressesPresenter.setView(this);
        addressesPresenter.getUserAddresses(authPreferences.getUserId());
    }

    private void initAddressesSpinner(List<AddressDvo> addresses) {
        ArrayList<String> strAddresses = new ArrayList<>();

        for (AddressDvo addressDvo : addresses) {
            strAddresses.add(addressDvo.getName());
        }

        ArrayAdapter<String> addressesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strAddresses);
        addressesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addrSpinnerView.setAdapter(addressesAdapter);

        addrSpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "onAddressSelected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void initShopsSpinner(List<ShopDvo> shops) {

        ArrayList<String> strShops = new ArrayList<>();
        for (ShopDvo shopDvo : shops) {
            strShops.add(shopDvo.getName());
        }

        ArrayAdapter<String> shopsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strShops);
        shopsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        shopsSpinnerView.setAdapter(shopsAdapter);

        shopsSpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "onShopSelected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @OnClick(R.id.create_order_button)
    public void onCreateOrderClick() {
        NewOrderDto orderDto = new NewOrderDto();
        orderDto.setDate("2018-03-22 15:10:19");
        orderDto.setDescription("Description asdasd sjbfsdgnjdgdfg dfhj");
        orderDto.setPrice(25.5);
        orderDto.setStatus(Constants.STATUS_NEW);

        AddressDto addressDto = new AddressDto("New addr", 200311, "Russia", "Moscow", "Region 2", "Frolova 20-23", "375252156474");
        ShopDto shopDto = new ShopDto("Colins2", "Sovetskaya 20");

        orderDto.setAddress(addressDto);
        orderDto.setShop(shopDto);

        newOrderPresenter.addOrder(authPreferences.getUserId(), orderDto);
    }

    // New order methods:

    @Override
    public void showAddOrderProgress() {

    }

    @Override
    public void hideAddOrderProgress() {

    }

    @Override
    public void onOrderAdded(OrderDvo orderDvo) {
        Log.i(TAG, "Order added: " + orderDvo.toString());
        Toast.makeText(getContext(), "Order added!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddOrderError(Throwable error) {
        Log.e(TAG, error.toString());
    }

    @OnClick(R.id.addShopButton)
    public void addShopClick() {
        NewShopDialog dialog = new NewShopDialog();
        dialog.attachListener(this);
        dialog.show(getFragmentManager(), ADD_SHOP_DIALOG);
    }

    // New shop dialog:

    @Override
    public void onNewShopDialogAddClick(DialogFragment dialog, ShopDto shopDto) {

    }

    @Override
    public void onNewShopDialogCancelClick(DialogFragment dialog) {

    }

    // Shops methods:

    @Override
    public void showLoadShopsProgress() {
        progressBarShops.setVisibility(View.VISIBLE);
        shopsSpinnerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadShopsProgress() {
        progressBarShops.setVisibility(View.GONE);
        shopsSpinnerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setShops(List<ShopDvo> shops) {
        initShopsSpinner(shops);
    }

    @Override
    public void showLoadShopsError(Throwable error) {
        Log.e(TAG, error.toString());
    }

    // Addresses methods:

    @Override
    public void showLoadAddressesProgress() {
        progressBarAddresses.setVisibility(View.VISIBLE);
        addrSpinnerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadAddressesProgress() {
        progressBarAddresses.setVisibility(View.GONE);
        addrSpinnerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAddresses(List<AddressDvo> addresses) {
        initAddressesSpinner(addresses);
    }

    @Override
    public void showLoadAddressesError(Throwable error) {
        Log.e(TAG, "Load addresses error: " + error.toString());
    }

    // Date & Time

    @Override
    public void onDateSetClick(DialogFragment dialog, int year, int month, int day) {
        Log.e(TAG, year + " " + month + " " + day);
    }

    @Override
    public void onTimeSetClick(DialogFragment dialog, int hourOfDay, int minute) {
        Log.e(TAG, hourOfDay + " " + minute);
    }

    @OnClick(R.id.set_date_button)
    void setDateButtonClick() {
        CustomDatePickerDialog dialog = new CustomDatePickerDialog();
        dialog.attachListener(this);
        dialog.show(getFragmentManager(), DATE_PICKER_DIALOG);
    }

    @OnClick(R.id.set_time_button)
    void setTimeButtonClick() {
        CustomTimePickerDialog dialog = new CustomTimePickerDialog();
        dialog.attachListener(this);
        dialog.show(getFragmentManager(), TIME_PICKER_DIALOG);
    }

}
