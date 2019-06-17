package es.jysa.priceCalculatorNG.prestaData;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import es.jysa.priceCalculatorNG.PrestaFactory;

public class ProductUpdateTest {
	
	@Test
	public void createProductUpdateAndCheckItsComponents() {
		Price oldPrice = mock(Price.class);
		Price newPrice = mock(Price.class);
		String reference = "MAT001";
		String code = "CDE001";
		
		ProductUpdate productUpdate = PrestaFactory.getProductUpdate(reference, code, newPrice, oldPrice);
		
		assertThat(productUpdate.getReference()).isEqualTo(reference);
		assertThat(productUpdate.getCode()).isEqualTo(code);
		assertThat(productUpdate.getPrice()).isEqualTo(newPrice);
		assertThat(productUpdate.getOldPrice()).isEqualTo(oldPrice);
	}
	
	@Test
	public void createProductUpdateWithNullOrVoidReferenceThrowsException() {
		Price newPrice = mock(Price.class);
		Price oldPrice = mock(Price.class);
		String reference = "";
		String code = "CDE001";
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getProductUpdate(null, code, newPrice, oldPrice);}, "Create a ProductUpdate without reference must be throw IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getProductUpdate(reference, code, newPrice, oldPrice);}, "Create a ProductUpdate with a void reference must be throw IllegalArgumentException");
	}
	

}
