package es.jysa.priceCalculatorNG;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import es.jysa.priceCalculatorNG.calculator.Calculator;
import es.jysa.priceCalculatorNG.calculator.NotPriceFindException;
import es.jysa.priceCalculatorNG.calculator.NotPriceProviderException;
import es.jysa.priceCalculatorNG.data.BadFileConfigException;
import es.jysa.priceCalculatorNG.data.Price;
import es.jysa.priceCalculatorNG.data.Product;
import es.jysa.priceCalculatorNG.data.ProductUpdate;
import es.jysa.priceCalculatorNG.utils.GzipUtils;
import es.jysa.priceCalculatorNG.utils.ParserCSVErrorException;
import es.jysa.priceCalculatorNG.utils.PrestaCSVParser;

public class Controller {
	
	private String tmpPath;
	private Character delimiter;
	private Character quote;
	
	private List<Product> products;
	private List<ProductUpdate> productsUpdate;
	private Calculator calculator;
	private PrestaCSVParser csvParser;
	
	public Controller(String configPath, String tmpPath, Character delimiter, Character quote) {
		try {
//			products = new ArrayList<>();
			this.delimiter = delimiter;
			this.quote = quote;
			this.tmpPath = tmpPath;
			calculator = PrestaFactory.getCalculator(PrestaFactory.getConfig(configPath));
			csvParser = PrestaFactory.getPrestaCSVParser();
		} catch (BadFileConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ProductUpdate> getProductsUpdate(){
		return productsUpdate;
	}
	
	public void loadPrestaData(String filePath) {
		if (GzipUtils.isGZipped(filePath)) {
			String newFilePath =  tmpPath+"Price_pioneer.txt";
			GzipUtils.gunzipIt(filePath, newFilePath);
			filePath = newFilePath;
		}
		try {
			products = csvParser.getProducts(filePath, delimiter, quote);
		} catch (ParserCSVErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPriceProviderPioneer(String filePath) {
		try {
			calculator.setPriceProvider(PrestaFactory.getPriceProvider("PioneerDJ", filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getNewPrices() {
		productsUpdate = products
				.parallelStream()
				.map(product -> {
					Price newPrice = null;
					try {
						newPrice = calculator.calculatePrice(product);
					} catch (NotPriceProviderException | NotPriceFindException e) {
						return PrestaFactory.getProductUpdate(product.getReference(),product.getCode(), product.getPrice(), product.getPrice());
					}
					return PrestaFactory.getProductUpdate(product.getReference(),product.getCode(), newPrice, product.getPrice());
				})
				.filter(productFiltrate -> !productFiltrate.getPrice().equals(productFiltrate.getOldPrice()))
				.collect(Collectors.toList());
	}
	
	public void exportNewPrices(String filePath) {
		csvParser.createCSVUpdate(productsUpdate, filePath, delimiter, quote);
	}

}
