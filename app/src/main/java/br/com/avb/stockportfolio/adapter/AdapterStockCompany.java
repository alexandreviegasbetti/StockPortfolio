package br.com.avb.stockportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.avb.stockportfolio.R;
import br.com.avb.stockportfolio.entity.StockCompany;
import br.com.avb.stockportfolio.entity.SupportItem;

import static java.util.Objects.isNull;

public class AdapterStockCompany extends BaseAdapter {

    private List<StockCompany> stockCompanyList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterStockCompany(Context context, List<StockCompany> stockCompanyList) {
        this.stockCompanyList = stockCompanyList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stockCompanyList.size();
    }

    @Override
    public Object getItem(int position) {
        return stockCompanyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return stockCompanyList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SupportItem item;

        if (isNull(convertView)) {
            convertView = inflater.inflate(R.layout.layout_stock_company_list, null);

            item = SupportItem.builder()
                    .name(convertView.findViewById(R.id.nameList))
                    .code(convertView.findViewById(R.id.codeList))
                    .quantity(convertView.findViewById(R.id.quantityList))
                    .value(convertView.findViewById(R.id.valueList))
                    .totalValue(convertView.findViewById(R.id.totalValueList))
                    .purchaseDate(convertView.findViewById(R.id.purchaseDateList))
                    .layout(convertView.findViewById(R.id.listBackground))
                    .build();

            convertView.setTag(item);
        } else {
            item = (SupportItem) convertView.getTag();
        }

        StockCompany stockCompany = stockCompanyList.get(position);
        item.getName().setText(stockCompany.getName());
        item.getCode().setText(stockCompany.getCode());
        item.getQuantity().setText(String.valueOf(stockCompany.getQuantity()));
        item.getValue().setText(String.valueOf(stockCompany.getValue()));
        item.getTotalValue().setText(String.valueOf(stockCompany.getTotalValue()));
        item.getPurchaseDate().setText(stockCompany.getPurchaseDate());

        return convertView;
    }

}
