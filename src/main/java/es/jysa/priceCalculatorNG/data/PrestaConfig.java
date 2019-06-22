package es.jysa.priceCalculatorNG.data;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PrestaConfig {
	private Double basicLimit;
	private Double firstLimit;
	private Double secondLimit;
	private Double basicIncrement;
	private Double firstPercentageIncrement;
	private Double secondPercentageIncrement;
	private Double thirdPercentageIncrement;
	private Double equalIncrement;
	
	public PrestaConfig(String jsonConfigFilePath) throws BadFileConfigException {
		JSONParser jsonParser = new JSONParser();
		
		try {
			JSONObject config = (JSONObject) jsonParser.parse(new FileReader(jsonConfigFilePath));
			
			try {
				basicLimit = Double.parseDouble((String) config.get("BasicLimit"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("BasicLimit is not set properly in config file.");
			} 
			try {
				firstLimit = Double.parseDouble((String) config.get("FirstLimit"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("FirstLimit is not set properly in config file.");
			} 
			try {
				secondLimit = Double.parseDouble((String) config.get("SecondLimit"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("SecondLimit is not set properly in config file.");
			} 
			try {
				basicIncrement = Double.parseDouble((String) config.get("BasicIncrement"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("BasicIncrement is not set properly in config file.");
			} 
			try {
				firstPercentageIncrement = Double.parseDouble((String) config.get("FirstPercentageIncrement"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("firstPercentageIncrement is not set properly in config file.");
			} 
			try {
				secondPercentageIncrement = Double.parseDouble((String) config.get("SecondPercentageIncrement"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("SecondPercentageIncrement is not set properly in config file.");
			} 
			try {
				thirdPercentageIncrement = Double.parseDouble((String) config.get("ThirdPercentageIncrement"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("ThirdPercentageIncrement is not set properly in config file.");
			} 
			try {
				equalIncrement = Double.parseDouble((String) config.get("EqualIncrement"));
			} catch (NullPointerException | NumberFormatException e) {
				throw new BadFileConfigException("EqualIncrement is not set properly in config file.");
			} 
		} catch (IOException e) {
			throw new BadFileConfigException("File:" +jsonConfigFilePath+" not exist.");
		} catch (ParseException e) {
			throw new BadFileConfigException("File:" +jsonConfigFilePath+" is not a valid json file.");
		}
		
	}

	public Double getBasicLimit() {
		return basicLimit;
	}

	public Double getFirstLimit() {
		return firstLimit;
	}

	public Double getSecondLimit() {
		return secondLimit;
	}

	public Double getBasicIncrement() {
		return basicIncrement;
	}

	public Double getFirstPercentageIncrement() {
		return firstPercentageIncrement;
	}

	public Double getSecondPercentageIncrement() {
		return secondPercentageIncrement;
	}

	public Double getThirdPercentageIncrement() {
		return thirdPercentageIncrement;
	}

	public Double getEqualIncrement() {
		return equalIncrement;
	}

}
