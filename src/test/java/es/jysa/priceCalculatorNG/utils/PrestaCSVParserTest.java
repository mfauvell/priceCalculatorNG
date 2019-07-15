package es.jysa.priceCalculatorNG.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;
import es.jysa.priceCalculatorNG.data.Price;
import es.jysa.priceCalculatorNG.data.Product;
import es.jysa.priceCalculatorNG.data.ProductUpdate;

class PrestaCSVParserTest {

	@Test
	void testExtratcEightProductsFileFiveHasCode() throws ParserCSVErrorException {
		ClassLoader classLoader = getClass().getClassLoader();
		String csvFilePath = classLoader.getResource("products.csv").getPath();
		Character delimiter = ';';
		Character quote = '"';
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
				
		assertThat(parser.getProducts(csvFilePath,delimiter,quote))
			.hasSize(5)
			.extracting(Product::getReference,Product::getCode,Product::getPrice)
			.contains(tuple("BTN064","DAA1187",PrestaFactory.getPrice(4.50, 7.5)),
					  tuple("BTN063","DAA1189",PrestaFactory.getPrice(5.51, 8.5)),
					  tuple("CON011","VNK1149",PrestaFactory.getPrice(6.52, 9.5)),
					  tuple("MEC138","VXX2909",PrestaFactory.getPrice(7.53, 10)),
					  tuple("POT018","DCV1009",PrestaFactory.getPrice(8.54, 11.05)))
		    .doesNotContain(tuple("MEC118","",PrestaFactory.getPrice(9.55, 12.5)),
		    				tuple("MEC112","",PrestaFactory.getPrice(10.56, 13.5)),
		    				tuple("IC016","",PrestaFactory.getPrice(11.57, 14.5)));
	}
	
	@Test
	void testNotExistFileProductsCSV() {
		Character delimiter = ';';
		Character quote = '"';
		String notFileExist = "no";
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
		assertThrows(ParserCSVErrorException.class, ()->{parser.getProducts(notFileExist, delimiter, quote);}, "Throw ParserCSVErrorException because file not exist");
	}
	
	@Test
	void testNotColumnsInFileProductsCSV() {
		ClassLoader classLoader = getClass().getClassLoader();
		String csvNoColumnsFilePath = classLoader.getResource("productsNotColumns.csv").getPath();
		Character delimiter = ';';
		Character quote = '"';
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
		assertThrows(ParserCSVErrorException.class, ()->{parser.getProducts(csvNoColumnsFilePath, delimiter, quote);}, "Throw ParserCSVErrorException because file not had columns mapped");
	}
	
	@Test
	void testFaultyRequiredValueProductsCSV() {
		ClassLoader classLoader = getClass().getClassLoader();
		String faultyRequireValueFilePath = classLoader.getResource("productsFaultyRequiredValue.csv").getPath();
		Character delimiter = ';';
		Character quote = '"';
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
		assertThrows(ParserCSVErrorException.class, ()->{parser.getProducts(faultyRequireValueFilePath, delimiter, quote);}, "Throw ParserCSVErrorException because file has not a required value column");
	}
	
	@Test
	void testConversionErrorValueProductsCSV() {
		ClassLoader classLoader = getClass().getClassLoader();
		String faultyRequireValueFilePath = classLoader.getResource("productsErrorValue.csv").getPath();
		Character delimiter = ';';
		Character quote = '"';
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
		assertThrows(ParserCSVErrorException.class, ()->{parser.getProducts(faultyRequireValueFilePath, delimiter, quote);}, "Throw ParserCSVErrorException because file has not a required value column");
	}
	
	@Test
	void testCreateUpdateCSV() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		Price newPrice1 = mock(Price.class);
		when(newPrice1.getCost()).thenReturn(10.0);
		when(newPrice1.getPvp()).thenReturn(15.0);
		ProductUpdate productUpdate1 = mock(ProductUpdate.class);
		when(productUpdate1.getReference()).thenReturn("REFERENCE1");
		when(productUpdate1.getPrice()).thenReturn(newPrice1);
		
		Price newPrice2 = mock(Price.class);
		when(newPrice2.getCost()).thenReturn(20.0);
		when(newPrice2.getPvp()).thenReturn(25.0);
		ProductUpdate productUpdate2 = mock(ProductUpdate.class);
		when(productUpdate2.getReference()).thenReturn("REFERENCE2");
		when(productUpdate2.getPrice()).thenReturn(newPrice2);
		
		List<ProductUpdate> listProductsUpdate = new ArrayList<>();
		listProductsUpdate.add(productUpdate1);
		listProductsUpdate.add(productUpdate2);
		
		String newFileName = "filenew.csv";
		String newFilePath = "./src/test/resources/filenew.csv";
		String expectedFileName = "fileexpected.csv";
		
		Character delimeter = ';';
		Character quote = '"';
		
		PrestaCSVParser parser = PrestaFactory.getPrestaCSVParser();
		
		parser.createCSVUpdate(listProductsUpdate, newFilePath, delimeter, quote);
		
		File newFile = new File(classLoader.getResource(newFileName).getPath());
		File expectedFile = new File(classLoader.getResource(expectedFileName).getPath());
		
		assertThat(newFile).hasSameContentAs(expectedFile);
	}
}
