package br.com.avb.stockportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.avb.stockportfolio.adapter.AdapterStockCompany;
import br.com.avb.stockportfolio.dao.StockCompanyDAO;
import br.com.avb.stockportfolio.entity.StockCompany;

import static br.com.avb.stockportfolio.enums.IntentValue.ACTION;
import static br.com.avb.stockportfolio.enums.IntentValue.EDIT;
import static br.com.avb.stockportfolio.enums.IntentValue.NEW;

public class MainActivity extends AppCompatActivity {

    private ListView lvStock;
    private List<StockCompany> stockCompanyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra(ACTION.toString(), NEW.toString());
            startActivity(intent);
        });

        lvStock = findViewById(R.id.lvStock);
        listAllStockCompanies();
        configListView();

    }

    private void configListView() {
        lvStock.setOnItemClickListener((parent, view, position, id) -> {
            StockCompany selectedStockCompany = stockCompanyList.get(position);
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra(ACTION.toString(), EDIT.toString());
            intent.putExtra("id", selectedStockCompany.getId());
            startActivity(intent);
        });

        lvStock.setOnItemLongClickListener((parent, view, position, id) -> {
            StockCompany selectedStockCompany = stockCompanyList.get(position);
            deleteStockCompany(selectedStockCompany);
            return true;
        });
    }

    private void deleteStockCompany(StockCompany selectedStockCompany) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(android.R.drawable.ic_input_delete);
        alert.setTitle(getResources().getString(R.string.attention));
        alert.setMessage(getResources().getString(R.string.exclusion_of_the_stock_company)
                + selectedStockCompany.getCode()
                + getResources().getString(R.string.question_mark));
        alert.setNeutralButton(R.string.cancel, null);
        alert.setPositiveButton(R.string.yes, (dialogInterface, i) -> {
            StockCompanyDAO.delete(selectedStockCompany.getId(), MainActivity.this);
            listAllStockCompanies();
        });
        alert.show();
    }

    private void listAllStockCompanies() {
        stockCompanyList = StockCompanyDAO.getAllStockCompanies(this);
        lvStock.setAdapter(new AdapterStockCompany(this, stockCompanyList));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listAllStockCompanies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}