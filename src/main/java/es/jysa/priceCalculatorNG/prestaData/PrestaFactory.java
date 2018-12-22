package es.jysa.priceCalculatorNG.prestaData;

import java.time.LocalDate;

public final class PrestaFactory {
	private PrestaFactory() {
		
	}

	public static Price getPrice(LocalDate date, double cost, double pvp) throws IllegalArgumentException {
		return new Price(date, cost, pvp);
	}

	public static Product getProduct(String reference, String code, Price price) throws IllegalArgumentException {
		return new Product(reference, code, price);
	}

	public static ProductUpdate getProductUpdate(String reference, String code, Price priceNew, Price priceOld) {
		return new ProductUpdate(reference, code, priceNew, priceOld);
	}
}
