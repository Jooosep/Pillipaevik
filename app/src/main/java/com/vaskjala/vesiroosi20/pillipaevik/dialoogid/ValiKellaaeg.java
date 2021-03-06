package com.vaskjala.vesiroosi20.pillipaevik.dialoogid;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;
import com.vaskjala.vesiroosi20.pillipaevik.BuildConfig;
import com.vaskjala.vesiroosi20.pillipaevik.teenused.Tooriistad;

import java.util.Calendar;

/**
 * Created by mihkel on 19.05.2016.
 */
public class ValiKellaaeg extends android.app.DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

    private final Calendar c = Calendar.getInstance();
    private AjaMuutuseTeavitus KuupaevaOmanik;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if(BuildConfig.DEBUG) Log.d("Valikellaaeg", "Loon kellaaja valiku dialoogi");

        try {
            KuupaevaOmanik = (AjaMuutuseTeavitus) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement Callback interface");
        }

        c.setTime(Tooriistad.KuupaevKellaAegStringist(getArguments().getString("datetime")));
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        if(BuildConfig.DEBUG) Log.d("ValikKellaaeg",hour + ":" + minute + " 24H" + DateFormat.is24HourFormat(getActivity()));

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    public void onTimeSet(TimePicker view, int hour, int minute) {
        // Kellaaega sätitakse minuti täpsusega. Nulli sekundid.
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), hour, minute, 0);
        KuupaevaOmanik.AegMuudetud(c.getTime(), getArguments().getBoolean("muudaalgust"));
        if(BuildConfig.DEBUG) Log.d("ValiKellaaeg", hour + ":" + minute + " " + c.getTime());
    }
}
