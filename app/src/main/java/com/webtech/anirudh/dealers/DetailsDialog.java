package com.webtech.anirudh.dealers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.webtech.anirudh.dealers.models.DealerEntry;

public class DetailsDialog extends AppCompatDialogFragment {
    private final DealerEntry entry;

    public DetailsDialog(DealerEntry entry) {
        this.entry = entry;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CurvedBorderDialog);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.details_dialog, null);
        ((TextView) view.findViewById(R.id.dealer_name_dialog)).setText(entry.getDealerName());
        ((TextView) view.findViewById(R.id.gstin_dialog)).setText(entry.getGstin());
        ((TextView) view.findViewById(R.id.phone_number_dialog)).setText(entry.getMobile());
        ((TextView) view.findViewById(R.id.ptin_dialog)).setText(entry.getPtin());
        ((TextView) view.findViewById(R.id.address)).setText(entry.getAddress());
        ((TextView) view.findViewById(R.id.entry)).setText(entry.getEntryNumber());
        ((TextView) view.findViewById(R.id.email)).setText(entry.getEmail());
        ((TextView) view.findViewById(R.id.tax_2017_2018)).setText(entry.getTaxPaid2017_2018());
        ((TextView) view.findViewById(R.id.tax_2018_2019)).setText(entry.getTaxPaid2018_2019());
        ((TextView) view.findViewById(R.id.tax_2019_2020)).setText(entry.getTaxPaid2019_2020());
        ((TextView) view.findViewById(R.id.tax_2020_2021)).setText(entry.getTaxPaid2020_2021());
        ((TextView) view.findViewById(R.id.tax_2021_2022)).setText(entry.getTaxPaid2021_2022());
        ((TextView) view.findViewById(R.id.tax_2022_2023)).setText(entry.getTaxPaid2022_2023());
        builder.setView(view);
        return builder.create();
    }
}
