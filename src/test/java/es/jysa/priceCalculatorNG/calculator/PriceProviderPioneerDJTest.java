package es.jysa.priceCalculatorNG.calculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.Price;

class PriceProviderPioneerDJTest {

	@Test
	void testConstructorAndGetNewPrices() throws NotPriceFindException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		String pricePioneerList = classLoader.getResource("pioneerPriceList.txt").getPath();;
		String priceProviderBrand = "PioneerDJ";
		PriceProvider pricePioneerDJ = PrestaFactory.getPriceProvider(priceProviderBrand, pricePioneerList);
		
		String code1 = "CODE1";
		String code3 = "CODE3";
		String code4 = "CODE4";
		String code5 = "CODE5";
		String code6 = "NOTEXIT";
		
		Price priceCode1 = PrestaFactory.getPrice(100.10, 120.10);
		Price priceCode3 = PrestaFactory.getPrice(300.13, 320.13);
		Price priceCode5 = PrestaFactory.getPrice(500.15, 520.15);
		
		assertThat(pricePioneerDJ.getClass()).isEqualTo(PriceProviderPioneerDJ.class);
		
		assertThat(pricePioneerDJ.getNewPrice(code1)).isEqualTo(priceCode1);
		assertThat(pricePioneerDJ.getNewPrice(code3)).isEqualTo(priceCode3);
		assertThat(pricePioneerDJ.getNewPrice(code4)).isEqualTo(priceCode3);
		assertThat(pricePioneerDJ.getNewPrice(code5)).isEqualTo(priceCode5);
		
		assertThrows(NotPriceFindException.class, () -> {pricePioneerDJ.getNewPrice(code6);}, "If code not in list must be thrown NotPriceFindException");
	}
	
}
