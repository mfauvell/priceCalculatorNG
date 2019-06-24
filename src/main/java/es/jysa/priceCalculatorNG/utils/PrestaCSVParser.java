package es.jysa.priceCalculatorNG.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.MapperProductToCsv;
import es.jysa.priceCalculatorNG.data.Product;

public class PrestaCSVParser {
	

	public PrestaCSVParser() {
	}

	public List<Product> getProducts(String csvFilePath, Character delimiter, Character quote) throws ParserCSVErrorException {
		
		try {
			return new CsvToBeanBuilder<MapperProductToCsv>(new FileReader(csvFilePath))
				.withSeparator(delimiter)
				.withQuoteChar(quote)
				.withType(MapperProductToCsv.class)
				.build()
				.parse()
				.parallelStream()
				.filter(element-> !element.getCode().equals(""))
				.map(protoProduct -> {
					return PrestaFactory.getProduct(protoProduct.getReference(), protoProduct.getCode(), PrestaFactory.getPrice(protoProduct.getCost(), protoProduct.getPvp()));
				})
				.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			throw new ParserCSVErrorException("CSV product file: " + csvFilePath + " not exist.");
		} catch (RuntimeException e) {
			throw new ParserCSVErrorException("An error has been produced in product file: " + csvFilePath + ". Error: " + e.getMessage());
		}
	}
	
	

}
