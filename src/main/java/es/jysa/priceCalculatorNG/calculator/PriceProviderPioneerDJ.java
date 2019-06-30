package es.jysa.priceCalculatorNG.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.Price;

public class PriceProviderPioneerDJ implements PriceProvider {
	
	private Map<String, PioneerItem> items;

	public PriceProviderPioneerDJ(String pricePath, Character delimeter, Character quote) throws IOException {
		items = new HashMap<String,PioneerItem>();
		parseData(pricePath,delimeter, quote);
	}

	@Override
	public Price getNewPrice(String code) throws NotPriceFindException {
		PioneerItem element = null;
		boolean found = false;
		//If code isObsolete get substitutive
		do {
			element = items.get(code);
			if (element == null) {
				throw new NotPriceFindException("Code: " + code + " is not correct.");
			}
			found = element.isObsolete();
			if (found) {
				code = element.getNewCode();
			}
		} while(found);
		return  PrestaFactory.getPrice(element.getCost(), element.getPvp());
	}
	
	/*
	 * Parse data of filepath into a map
	 * 
	 * @param filePath file with raw data
	 */
	private void parseData(String filePath, Character delimeter, Character quote) throws IOException {
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(delimeter)
				.withQuoteChar(quote)
				.build();
		CSVReader reader = new CSVReaderBuilder(Files.newBufferedReader(Paths.get(filePath)))
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
		
		List<String[]> csvData = reader.readAll();

		Iterator<String[]> itCsvData = csvData.iterator();
		
		while (itCsvData.hasNext()){
			String[] element = itCsvData.next();
			PioneerItem eData = null;
			String code = element[0].trim();
			String fCode = element[3].trim();
			if (!(fCode.equals("")) && !code.equals(fCode)) {
				eData = new PioneerItem(Double.parseDouble(element[4]), Double.parseDouble(element[5]), fCode);
			} else {
				eData = new PioneerItem(Double.parseDouble(element[4]), Double.parseDouble(element[5]));
			}
			items.put(code, eData);
		}
	}

	
	/*
	 * Private class to relate data of item pioneer
	 */
	private class PioneerItem {
		
		private Price price; //Prices of data
		private boolean obsolete; //Flag if is obsolete el code
		private String newCode; //Code substitutive
		
		public PioneerItem (Double cost, Double pvp) {
			price = PrestaFactory.getPrice(cost, pvp);
			obsolete = false;
		}
		
		public PioneerItem (Double cost, Double pvp, String newCode) {
			this(cost, pvp);
			this.newCode = newCode;
			obsolete = true;
		}
		
		public Double getCost() {
			return price.getCost();
		}
		
		public Double getPvp() {
			return price.getPvp();
		}
		
		public String getNewCode() {
			return newCode;
		}
		
		public boolean isObsolete() {
			return obsolete;
		}
		
		public String toString() {
			return price.getPvp() + " " + price.getCost() + " " + " " + obsolete + " " + newCode;
		}
	}
}
