package es.jysa.priceCalculatorNG.prestaCalculator;

import es.jysa.priceCalculatorNG.prestaData.PrestaConfig;

public class Calculator {

	private PriceProvider priceProvider;
	
	public Calculator(PrestaConfig config) {
		// TODO Auto-generated constructor stub
	}

	public void setPriceProvider(PriceProvider priceProvider) {
		this.priceProvider = priceProvider;		
	}

	public PriceProvider getPriceProvider() {
		return priceProvider;
	}

}
