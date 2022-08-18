package com.webtech.anirudh.dealers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.webtech.anirudh.dealers.adapters.HomeListAdapter;
import com.webtech.anirudh.dealers.models.DealerEntry;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private List<DealerEntry> dealerList = new ArrayList<>();
    private List<DealerEntry> filteredList = new ArrayList<>();
    private Set<Integer> activeFilters = new HashSet<>();
    private HomeListAdapter adapter;
    private boolean isAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.home_list);
        InputStream stream = null;
        CSVReader csvReader = null;
        try {
            stream = getAssets().open("data.csv");
            csvReader = new CSVReader(new InputStreamReader(stream));
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                dealerList.add(new DealerEntry(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (csvReader != null) {
                    csvReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dealerList.sort(Comparator.comparing(d -> d.getDealerName().toLowerCase()));
        if (!isAscending) Collections.reverse(dealerList);
        filteredList = new ArrayList<>(dealerList);
        adapter = new HomeListAdapter(this);
        adapter.setData(dealerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton button = findViewById(R.id.up_button);
        button.setOnClickListener(view -> recyclerView.smoothScrollToPosition(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String finalNewText = newText.toLowerCase();
                ;
                List<DealerEntry> searchFilteredList = filteredList.stream().filter(dealerEntry ->
                        dealerEntry.getDealerName().toLowerCase().contains(finalNewText) ||
                                dealerEntry.getMobile().toLowerCase().contains(finalNewText) ||
                                dealerEntry.getGstin().toLowerCase().contains(finalNewText))
                        .collect(Collectors.toList());
                adapter.setData(searchFilteredList);
                return true;
            }
        });
        MenuItem sortMenuItem = menu.findItem(R.id.sort_alpha);
        if (isAscending) {
            sortMenuItem.getIcon().setTint(Color.parseColor("#00FF00"));
        } else {
            sortMenuItem.getIcon().setTint(Color.parseColor("#FF0000"));
        }
        sortMenuItem.setOnMenuItemClickListener(menuItem1 -> {
            isAscending = !isAscending;
            Collections.reverse(dealerList);
            Collections.reverse(filteredList);
            adapter.setData(filteredList);
            if (isAscending) {
                sortMenuItem.getIcon().setTint(Color.parseColor("#00FF00"));
            } else {
                sortMenuItem.getIcon().setTint(Color.parseColor("#FF0000"));
            }
            return false;
        });
        MenuItem filterMenuItem = menu.findItem(R.id.filter);
        SubMenu filterMenu = filterMenuItem.getSubMenu();
        getMenuInflater().inflate(R.menu.filter_menu, filterMenu);
        List<MenuItem> filters = new ArrayList<>();
        filters.add(filterMenu.findItem(R.id.filter_2017_2018));
        filters.add(filterMenu.findItem(R.id.filter_2018_2019));
        filters.add(filterMenu.findItem(R.id.filter_2019_2020));
        filters.add(filterMenu.findItem(R.id.filter_2020_2021));
        filters.add(filterMenu.findItem(R.id.filter_2021_2022));
        filters.add(filterMenu.findItem(R.id.filter_2022_2023));
        setOnClickForFilters(filters);
        return super.onCreateOptionsMenu(menu);
    }

    private void setOnClickForFilters(List<MenuItem> filters) {
        for (MenuItem item : filters) {
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
            item.setActionView(new View(this));
            item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return false;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    return false;
                }
            });
            item.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) menuItem -> {
                menuItem.setChecked(!menuItem.isChecked());
                if (menuItem.isChecked()) {
                    activeFilters.add(menuItem.getItemId());
                } else {
                    activeFilters.remove(menuItem.getItemId());
                }
                setFilters();
                return false;
            });
        }
    }

    private void setFilters() {
        if (activeFilters.isEmpty()) {
            filteredList = new ArrayList<>(dealerList);
            adapter.setData(filteredList);
            return;
        }
        filteredList = new ArrayList<>();
        for (DealerEntry dealerEntry : dealerList) {
            if (activeFilters.contains(R.id.filter_2017_2018)) {
                String taxPaid = dealerEntry.getTaxPaid2017_2018();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            } if (activeFilters.contains(R.id.filter_2018_2019)) {
                String taxPaid = dealerEntry.getTaxPaid2018_2019();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            } if (activeFilters.contains(R.id.filter_2019_2020)) {
                String taxPaid = dealerEntry.getTaxPaid2019_2020();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            } if (activeFilters.contains(R.id.filter_2020_2021)) {
                String taxPaid = dealerEntry.getTaxPaid2020_2021();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            } if (activeFilters.contains(R.id.filter_2021_2022)) {
                String taxPaid = dealerEntry.getTaxPaid2021_2022();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            } if (activeFilters.contains(R.id.filter_2022_2023)) {
                String taxPaid = dealerEntry.getTaxPaid2022_2023();
                if (taxPaid.isEmpty() || taxPaid.equals("0")) {
                    filteredList.add(dealerEntry);
                }
            }
        }
        adapter.setData(filteredList);
    }
}