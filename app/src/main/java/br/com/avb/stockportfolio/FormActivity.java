package br.com.avb.stockportfolio;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import br.com.avb.stockportfolio.dao.CompanyDAO;
import br.com.avb.stockportfolio.dao.StockCompanyDAO;
import br.com.avb.stockportfolio.entity.Company;
import br.com.avb.stockportfolio.entity.StockCompany;

import static br.com.avb.stockportfolio.enums.IntentValue.ACTION;
import static br.com.avb.stockportfolio.enums.IntentValue.EDIT;
import static br.com.avb.stockportfolio.enums.IntentValue.NEW;

public class FormActivity extends AppCompatActivity {

    private Spinner code;
    private EditText quantity;
    private EditText value;
    private EditText date;
    private Button btnSave;

    private String action;
    private StockCompany stockCompany;
    private List<Company> listCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        code = findViewById(R.id.code);
        quantity = findViewById(R.id.quantity);
        value = findViewById(R.id.value);
        date = findViewById(R.id.date);
        btnSave = findViewById(R.id.btnSave);

        listCode = CompanyDAO.getAllStockCompanies(this);

        spinnerAdapter();

        action = getIntent().getStringExtra(ACTION.toString());
        if (action.equals(EDIT.toString())) {
            uploadForm();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        if (quantity.getText().toString().isEmpty()
                || code.getSelectedItem().toString().isEmpty()
                || date.getText().toString().isEmpty()
                || value.getText().toString().isEmpty()) {
            Toast.makeText(FormActivity.this,
                    getResources().getString(R.string.fields_validation),
                    Toast.LENGTH_LONG).show();
        } else {
            if (action.equals(NEW.toString())) {
                stockCompany = new StockCompany();
            }
            stockCompany.setCode(code.getSelectedItem().toString());
            stockCompany.setPurchaseDate(date.getText().toString());
            Integer quantity = Integer.valueOf(this.quantity.getText().toString());
            stockCompany.setQuantity(quantity);
            Double value = Double.valueOf(this.value.getText().toString());
            stockCompany.setValue(value);
            BigDecimal totalValue = getTotalValue(quantity, value);
            stockCompany.setTotalValue(totalValue.doubleValue());
            stockCompany.setName(listCode.stream()
                    .filter(c -> c.getCode().equals(code.getSelectedItem().toString()))
                    .findFirst().orElse(null).getName());

            if (action.equals(EDIT.toString())) {
                StockCompanyDAO.edit(stockCompany, this);
                finish();
            } else {
                StockCompanyDAO.insert(stockCompany, this);
                finish();
            }
        }

    }

    private void uploadForm() {
        Integer id = getIntent().getIntExtra("id", 0);
        if (!id.equals(0)) {
            stockCompany = StockCompanyDAO.getStockCompanyById(this, id);

            for (int i = 1; i < listCode.size(); i++) {
                if (String.valueOf(listCode.get(i).getCode()) == stockCompany.getCode()) {
                    code.setSelection(i);
                }
            }

            date.setText(stockCompany.getPurchaseDate());
            quantity.setText(stockCompany.getQuantity().toString());
            value.setText(stockCompany.getValue().toString());
        }
    }

    private void spinnerAdapter() {
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, listCode.stream()
                .map(Company::getCode).collect(Collectors.toList()));

        code.setAdapter(spinnerAdapter);
    }

    private BigDecimal getTotalValue(Integer quantity, Double value) {
        return BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(value))
                .setScale(2, RoundingMode.HALF_UP);
    }


}