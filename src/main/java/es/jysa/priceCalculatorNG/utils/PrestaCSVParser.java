package es.jysa.priceCalculatorNG.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.MapperProductToCsv;
import es.jysa.priceCalculatorNG.data.Product;
import es.jysa.priceCalculatorNG.data.ProductUpdate;

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

	public void createCSVUpdate(List<ProductUpdate> listProductsUpdate, String filePath, Character delimeter,
			Character quote) {
		StringBuilder csvString = new StringBuilder();
		//Add the head
		csvString.append(quote +"Sku" + quote + delimeter + quote + "Cost"+ quote + delimeter + quote + "Price" + quote + "\n");
		Iterator<ProductUpdate> itListProductsUpdate = listProductsUpdate.iterator();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("####0.00", otherSymbols);
		while(itListProductsUpdate.hasNext()) {
			ProductUpdate product = itListProductsUpdate.next();
			String reference = product.getReference();
			String cost = format.format(product.getPrice().getCost()); 
			String pvp = format.format(product.getPrice().getPvp());
			csvString.append(quote + reference + quote + delimeter + quote + cost + quote + delimeter + quote + pvp + quote + "\n");
		}
		
		createFile(filePath, csvString.toString());
	}
	
	/*
	 * Create a new file in filePath, it delete file if it exists
	 * 
	 * @param filePath	path of file to create
	 * @param content	content of file
	 */
	private void createFile(String filePath, String content) {
		File file = null;
		FileWriter fw = null;
		
		try {
			file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			fw = new FileWriter(file);
			fw.write(content);
		} catch (IOException e) {
			System.out.print("I/O error, check your data\n");
			System.exit(1);
			//e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					System.out.print("I/O error, check your data\n");
					System.exit(1);
					//e.printStackTrace();
				}
			}
		}
	}

}
