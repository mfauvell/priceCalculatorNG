package es.jysa.priceCalculatorNG.prestaData;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.prestaData.Price;

public class PriceTest {
	
	private static final double cost = 1.5;
	private static final double pvp = 5.5;

	@Test
	public void createPriceWithPredefiniedCostPVP() {
		Price price = PrestaFactory.getPrice(cost, pvp);
		
		assertThat(price.getCost()).isEqualTo(cost);
		assertThat(price.getPvp()).isEqualTo(pvp);
	}
	
	@Test
	public void createPriceWithNegativeCostOrNegativePvpThrowException() {
		double badCost = -1.5;
		double badPvp = -5.5;
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(badCost, pvp);},"Here must throw IllegalArgumentException because cost is negative" );
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(cost, badPvp);},"Here must throw IllegalArgumentException because pvp is negative" );
	}
	
	@Test
	public void createPriceCostGreaterPvpThrowExeption() {
		double lowerPvp = 0.5;
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(cost, lowerPvp);}, "Here must throw IllegalArgumentException because cost is greater than pvp");
	}
}
