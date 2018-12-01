package prestaData;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PriceTest {
	
	private static final double cost = 1.5;
	private static final double pvp = 5.5;
	private static final LocalDate date = LocalDate.of(2018, 10, 30);

	@Test
	public void createPriceWithPredefiniedDateCostPVP() {
		Price price = PrestaFactory.getPrice(date, cost, pvp);
		
		assertThat(price.getCost()).isEqualTo(cost);
		assertThat(price.getPvp()).isEqualTo(pvp);
		assertThat(price.getDate()).isEqualTo(date);
	}
	
	@Test
	public void createPriceWithNegativeCostOrNegativePvpThrowException() {
		double badCost = -1.5;
		double badPvp = -5.5;
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(date, badCost, pvp);},"Here must throw IllegalArgumentException because cost is negative" );
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(date, cost, badPvp);},"Here must throw IllegalArgumentException because pvp is negative" );
	}
	
	@Test
	public void createPriceCostGreaterPvpThrowExeption() {
		double lowerPvp = 0.5;
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getPrice(date, cost, lowerPvp);}, "Here must throw IllegalArgumentException because cost is greater than pvp");
	}
}
