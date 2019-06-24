package es.jysa.priceCalculatorNG.data;

import com.opencsv.bean.CsvBindByName;

public class MapperProductToCsv {
	
	@CsvBindByName(column = "Reference #",required = true)
	private String reference;
	@CsvBindByName(column = "Code")
	private String code;
	@CsvBindByName(column = "Wholesale price")
	private Double cost;
	@CsvBindByName(column = "Price tax excluded")
	private Double pvp;
	
	public String getReference() {
		return reference;
	}
	
	public String getCode() {
		return code;
	}
	
	public Double getCost( ) {
		return cost;
	}
	
	public Double getPvp() {
		return pvp;
	}
	
}