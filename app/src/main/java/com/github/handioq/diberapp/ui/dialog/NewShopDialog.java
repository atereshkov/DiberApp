package com.github.handioq.diberapp.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.model.dto.ShopDto;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewShopDialog extends DialogFragment {

    public interface DialogListener {
        void onNewShopDialogAddClick(DialogFragment dialog, ShopDto shopDto);

        void onNewShopDialogCancelClick(DialogFragment dialog);
    }

    private DialogListener listener;

    private static final String TAG = "NewShopDialog";

    @BindView(R.id.name)
    EditText nameEditView;

    @BindView(R.id.address)
    EditText addressEditView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog");

        Builder builder = new Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_shop, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(R.string.dialog_new_shop_title)
                .setPositiveButton(R.string.dialog_new_shop_create, (dialog, id) -> {
                    String name = nameEditView.getText().toString();
                    String address = addressEditView.getText().toString();

                    listener.onNewShopDialogAddClick(this, new ShopDto(name, address));
                })
                .setNegativeButton(R.string.dialog_new_shop_cancel, (dialog, id) -> {
                    listener.onNewShopDialogCancelClick(this);
                });

        return builder.create();
    }

    public void attachListener(DialogListener dialogListener) {
        this.listener = dialogListener;
    }
}
