package es.jysa.priceCalculatorNG;

import es.jysa.priceCalculatorNG.calculator.Calculator;
import es.jysa.priceCalculatorNG.data.BadFileConfigException;
import es.jysa.priceCalculatorNG.data.PrestaConfig;
import es.jysa.priceCalculatorNG.data.Price;
import es.jysa.priceCalculatorNG.data.Product;
import es.jysa.priceCalculatorNG.data.ProductUpdate;
import es.jysa.priceCalculatorNG.utils.PrestaCSVParser;

public final class PrestaFactory {
	private PrestaFactory() {
		
	}

	public static Price getPrice(double cost, double pvp) throws IllegalArgumentException {
		return new Price(cost, pvp);
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

	public static PrestaConfig getConfig(String jsonConfigFilePath) throws BadFileConfigException {
		return new PrestaConfig(jsonConfigFilePath);
	}

	public static PrestaCSVParser getPrestaCSVParser() {
		return new PrestaCSVParser();
	}
}
