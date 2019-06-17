package es.jysa.priceCalculatorNG;

import java.time.LocalDate;

import es.jysa.priceCalculatorNG.prestaCalculator.Calculator;
import es.jysa.priceCalculatorNG.prestaData.PrestaConfig;
import es.jysa.priceCalculatorNG.prestaData.Price;
import es.jysa.priceCalculatorNG.prestaData.Product;
import es.jysa.priceCalculatorNG.prestaData.ProductUpdate;

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

	public static Calculator getCalculator(PrestaConfig config) {
		return new Calculator(config);
	}
}
