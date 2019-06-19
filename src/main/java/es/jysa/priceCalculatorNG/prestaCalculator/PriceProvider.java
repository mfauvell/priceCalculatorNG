package es.jysa.priceCalculatorNG.prestaCalculator;

import es.jysa.priceCalculatorNG.prestaData.Price;

public interface PriceProvider {

	public Price getNewPrice(String code);

}
