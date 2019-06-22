package es.jysa.priceCalculatorNG.calculator;

import es.jysa.priceCalculatorNG.data.Price;

public interface PriceProvider {

	public Price getNewPrice(String code) throws NotPriceFindException;

}
