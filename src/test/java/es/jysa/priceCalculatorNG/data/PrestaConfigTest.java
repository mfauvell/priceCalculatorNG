package es.jysa.priceCalculatorNG.data;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.BadFileConfigException;
import es.jysa.priceCalculatorNG.data.PrestaConfig;


class PrestaConfigTest {

	@Test
	void testLoadConfigurationFromFile() throws BadFileConfigException {
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
	
	@Test
	void testLoadConfigurationNotExistFile() {
		assertThrows(BadFileConfigException.class, () -> {@SuppressWarnings("unused")
		PrestaConfig config = PrestaFactory.getConfig("notExist.json");}, "BadFileConfigException must be thrown because file not exist");
	}
	
	@Test
	void testLoadConfigurationFileNotJson() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String jsonConfigFilePath = classLoader.getResource("configNotJson.json").getPath();
		assertThrows(BadFileConfigException.class, () -> {@SuppressWarnings("unused")
		PrestaConfig config = PrestaFactory.getConfig(jsonConfigFilePath);}, "BadFileConfigException must be thrown because file is not json");
	}

	@Test
	void testLoadConfigurationFileFaultyValor() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String jsonConfigFilePath = classLoader.getResource("configBadFaultyValor.json").getPath();
		assertThrows(BadFileConfigException.class, () -> {@SuppressWarnings("unused")
		PrestaConfig config = PrestaFactory.getConfig(jsonConfigFilePath);}, "BadFileConfigException must be thrown because a config value is not present");
	}
	
	@Test
	void testLoadConfigurationFileErrorValor() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		String jsonConfigFilePath = classLoader.getResource("configBadValor.json").getPath();
		assertThrows(BadFileConfigException.class, () -> {@SuppressWarnings("unused")
		PrestaConfig config = PrestaFactory.getConfig(jsonConfigFilePath);}, "BadFileConfigException must be thrown because a erroneus valor is set in a config value");
	}
}
