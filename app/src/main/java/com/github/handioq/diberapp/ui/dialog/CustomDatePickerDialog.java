package com.github.handioq.diberapp.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.github.handioq.diberapp.R;

import java.util.Calendar;

public class CustomDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface DialogListener {
        void onDateSetClick(DialogFragment dialog, int year, int month, int day);
    }

    private DialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // todo zone, etc..

        // Create a new instance of CustomDatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        listener.onDateSetClick(this, year, month, day);
    }

    public void attachListener(DialogListener dialogListener) {
        this.listener = dialogListener;
    }
}