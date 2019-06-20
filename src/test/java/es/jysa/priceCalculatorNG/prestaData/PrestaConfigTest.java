package es.jysa.priceCalculatorNG.prestaData;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;


class PrestaConfigTest {

	@Test
	void testLoadConfigurationFromFile() {
		Double basicLimit = 4.0;
		Double firstLimit = 30.0;
		Double secondLimit = 50.0;
		Double basicIncrement = 4.0;
		Double firstPercentageIncrement = 20.0;
		Double secondPercentageIncrement = 10.0;
		Double thirdPercentageIncrement = 0.0;
		Double equalIncrement = 30.0;
		
		ClassLoader classLoader = getClass().getClassLoader();
		
		String jsonConfigFilePath = classLoader.getResource("configGood.json").getPath();
		
		PrestaConfig config = PrestaFactory.getConfig(jsonConfigFilePath);
		
		assertThat(config.getBasicLimit()).isEqualTo(basicLimit);
		assertThat(config.getFirstLimit()).isEqualTo(firstLimit);
		assertThat(config.getSecondLimit()).isEqualTo(secondLimit);
		assertThat(config.getBasicIncrement()).isEqualTo(basicIncrement);
		assertThat(config.getFirstPercentageIncrement()).isEqualTo(firstPercentageIncrement);
		assertThat(config.getSecondPercentageIncrement()).isEqualTo(secondPercentageIncrement);
		assertThat(config.getThirdPercentageIncrement()).isEqualTo(thirdPercentageIncrement);
		assertThat(config.getEqualIncrement()).isEqualTo(equalIncrement);
	}

}
