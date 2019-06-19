package es.jysa.priceCalculatorNG.prestaCalculator;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.prestaData.PrestaConfig;
import es.jysa.priceCalculatorNG.prestaData.Price;
import es.jysa.priceCalculatorNG.prestaData.Product;

public class Calculator {

	private PriceProvider priceProvider;
	private PrestaConfig config;
	
	public Calculator(PrestaConfig config) {
		this.config = config;
	}

	public void setPriceProvider(PriceProvider priceProvider) {
		this.priceProvider = priceProvider;		
	}

	public PriceProvider getPriceProvider() {
		return priceProvider;
	}

	public Price calculatePrice(Product productHasUpdate) {
		Price originalPrice = priceProvider.getNewPrice(productHasUpdate.getCode());
		
		Double increment = 0.0;
		Double newPVP;
		if (((Double)originalPrice.getCost()).compareTo(originalPrice.getPvp()) == 0) {
			increment = (originalPrice.getPvp() * config.getEqualIncrement()) / 100;
			newPVP = Math.round((originalPrice.getPvp() + increment)* 100.0)/100.0;
		} else if ( originalPrice.getCost() < config.getBasicLimit()) {
			increment = config.getBasicIncrement();
			newPVP = Math.round((originalPrice.getCost() + increment)* 100.0)/100.0;
		} else if (originalPrice.getCost() >= config.getBasicLimit() && originalPrice.getCost() < config.getFirstLimit()) {
			increment = (originalPrice.getPvp() * config.getFirstPercentageIncrement() ) / 100;
			newPVP = Math.round((originalPrice.getPvp() + increment)* 100.0)/100.0;
		} else if (originalPrice.getCost() >= config.getFirstLimit() && originalPrice.getCost() < config.getSecondLimit()) {
			increment = (originalPrice.getPvp() * config.getSecondPercentageIncrement() ) / 100;
			newPVP = Math.round((originalPrice.getPvp() + increment)* 100.0)/100.0;
		} else {
			increment = (originalPrice.getPvp() * config.getThirdPercentageIncrement() ) / 100;
			newPVP = Math.round((originalPrice.getPvp() + increment)* 100.0)/100.0;
		}
		
		return PrestaFactory.getPrice(originalPrice.getCost(), newPVP);
	}
}
