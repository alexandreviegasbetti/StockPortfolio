package br.com.avb.stockportfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCompany {

    private Integer id;
    private String name;
    private String code;
    private String purchaseDate;

}
