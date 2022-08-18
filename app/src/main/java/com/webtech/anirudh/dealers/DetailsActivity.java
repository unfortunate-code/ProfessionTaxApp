package com.webtech.anirudh.dealers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webtech.anirudh.dealers.models.DealerEntry;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    private DealerEntry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        entry = (DealerEntry) getIntent().getSerializableExtra("entry");
        ((TextView) findViewById(R.id.dealer_name_dialog)).setText(entry.getDealerName());
        ((TextView) findViewById(R.id.gstin_dialog)).setText(entry.getGstin());
        ((TextView) findViewById(R.id.phone_number_dialog)).setText(entry.getMobile());
        ((TextView) findViewById(R.id.ptin_dialog)).setText(entry.getPtin());
        if (entry.getAddress().trim().length() > 0) {
            ((TextView) findViewById(R.id.address)).setText(entry.getAddress());
        } else {
            findViewById(R.id.address).setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.entry)).setText(entry.getEntryNumber());
        if (entry.getEmail().trim().length() > 0) {
            ((TextView) findViewById(R.id.email)).setText(entry.getEmail());
        } else {
            findViewById(R.id.email).setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.edr_value)).setText(entry.getEdr());
        ((TextView) findViewById(R.id.tax_2017_2018)).setText(entry.getTaxPaid2017_2018());
        ((TextView) findViewById(R.id.tax_2018_2019)).setText(entry.getTaxPaid2018_2019());
        ((TextView) findViewById(R.id.tax_2019_2020)).setText(entry.getTaxPaid2019_2020());
        ((TextView) findViewById(R.id.tax_2020_2021)).setText(entry.getTaxPaid2020_2021());
        ((TextView) findViewById(R.id.tax_2021_2022)).setText(entry.getTaxPaid2021_2022());
        ((TextView) findViewById(R.id.tax_2022_2023)).setText(entry.getTaxPaid2022_2023());
        ImageView phoneView = findViewById(R.id.phone_icon);
        if (entry.getMobile().length() > 0) {
            phoneView.setOnClickListener(view -> {
                makeCall(entry.getMobile());
            });
            ((TextView) findViewById(R.id.phone_number_dialog)).setOnClickListener(
                    view -> makeCall(entry.getMobile()));
        } else {
            phoneView.setVisibility(View.GONE);
        }
    }

    private void makeCall(String mobile) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:+91" + mobile));
        startActivity(callIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        MenuItem editItem = menu.findItem(R.id.edit);
        editItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                DetailsDialog dialog = new DetailsDialog(entry);
                dialog.show(getSupportFragmentManager(), "Details dialog");
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}