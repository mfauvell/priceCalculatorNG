package es.jysa.priceCalculatorNG.prestaCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.prestaData.PrestaConfig;
import es.jysa.priceCalculatorNG.prestaData.Price;
import es.jysa.priceCalculatorNG.prestaData.Product;


class CalculatorTest {
	
	private static final PrestaConfig config = mock(PrestaConfig.class);
	private static final PriceProvider priceProvider = mock(PriceProvider.class);

	
	@BeforeAll
	public static void setUp() {
		when(config.getBasicLimit()).thenReturn(4.0);
		when(config.getFirstLimit()).thenReturn(30.0);
		when(config.getSecondLimit()).thenReturn(50.0);
		when(config.getBasicIncrement()).thenReturn(4.0);
		when(config.getFirstPercentageIncrement()).thenReturn(20.0);
		when(config.getSecondPercentageIncrement()).thenReturn(10.0);
		when(config.getThirdPercentageIncrement()).thenReturn(0.0);
		when(config.getEqualIncrement()).thenReturn(30.0);
		
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
	
	@ParameterizedTest(name = "obtainNewPriceTest: {arguments}")
	@CsvSource({"3.81,4.95,3.81,7.81","4.81,6.25,4.81,7.50","30.81,40.05,30.81,44.06","50.81,66.05,50.81,66.05","30.0,30.0,30.0,39.0"})
	void obtainNewPriceTest(Double cost, Double pvp, Double expectedCost, Double expectedPvp ) throws NotPriceProviderException, NotPriceFindException {
		Double costBrand = cost;
		Double pvpBrand = pvp;
		Double costResult = expectedCost;
		Double pvpResult = expectedPvp;
		
		String codeHasUpdate = "CODE1";
		Price priceHasUpdate = mock(Price.class);
		
		Price priceBrand = mock(Price.class);
		when(priceBrand.getCost()).thenReturn(costBrand);
		when(priceBrand.getPvp()).thenReturn(pvpBrand);
		
		//PriceProvider config
		when(priceProvider.getNewPrice(codeHasUpdate)).thenReturn(priceBrand);
		
		Calculator calculator = PrestaFactory.getCalculator(config);
		
		calculator.setPriceProvider(priceProvider);
		
		Product productHasUpdate = mock(Product.class);
		when(productHasUpdate.getCode()).thenReturn(codeHasUpdate);
		when(productHasUpdate.getPrice()).thenReturn(priceHasUpdate);
		
		Price priceResult = calculator.calculatePrice(productHasUpdate);
		
		verify(priceProvider).getNewPrice(codeHasUpdate);
		reset(priceProvider); //Reset priceProvider calls to ensure that verify check getNewPrice only is called once
		
		assertThat(priceResult.getCost()).isEqualTo(costResult);
		assertThat(priceResult.getPvp()).isEqualTo(pvpResult);
		
	}
	
	@Test
	void obtainNewPriceNoResult() throws NotPriceFindException {
		String codeNotHasUpdate = "CODE2";
		
		Product productNotHasUpdate = mock(Product.class);
		when(productNotHasUpdate.getCode()).thenReturn(codeNotHasUpdate);
		
		Calculator calculator = PrestaFactory.getCalculator(config);
		
		when(priceProvider.getNewPrice(codeNotHasUpdate)).thenThrow(NotPriceFindException.class);
		
		assertThrows(NotPriceProviderException.class, ()->{calculator.calculatePrice(productNotHasUpdate); }, 
				"Must be throw NotPriceProviderException because there aren't priceProvider set");
		
		calculator.setPriceProvider(priceProvider);
		
		assertThrows(NotPriceFindException.class, ()->{calculator.calculatePrice(productNotHasUpdate); }, 
				"Must be throw NotPriceFindException because there aren't a price to return");
		
	}
	
}
