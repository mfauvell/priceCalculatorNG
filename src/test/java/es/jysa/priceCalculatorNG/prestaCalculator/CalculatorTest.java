package es.jysa.priceCalculatorNG.prestaCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.prestaData.PrestaConfig;


class CalculatorTest {
	
	private static final PrestaConfig config = mock(PrestaConfig.class);
	private static final PriceProvider priceProvider = mock(PriceProvider.class);

	
	@BeforeAll
	public static void setUp() {
		when(config.getBasicLimit()).thenReturn(4);
		when(config.getFirstLimit()).thenReturn(30);
		when(config.getSecondLimit()).thenReturn(50);
		when(config.getBasicIncrement()).thenReturn(4);
		when(config.getFirstPercentageIncrement()).thenReturn(20);
		when(config.getSecondPercentageIncrement()).thenReturn(10);
		when(config.getThirdPercentageIncrement()).thenReturn(0);
		when(config.getEqualIncrement()).thenReturn(30);
		
	}

	@Test
	void createCalculatorTest() {
		
		Calculator calculator = PrestaFactory.getCalculator(config);
		
		assertThat(calculator).isInstanceOf(Calculator.class);
	}

	@Test
	void checkPriceProviderSetTest() {
		
		Calculator calculator = PrestaFactory.getCalculator(config);
		
		calculator.setPriceProvider(priceProvider);
		
		assertThat(calculator.getPriceProvider()).isEqualTo(priceProvider);
	}
	
	@Test
	void obtainNewPriceTest() {
		
		Calculator calculator = PrestaFactory.getCalculator(config);
		
		calculator.setPriceProvider(priceProvider);
		
		//TODO
	}
	
}
