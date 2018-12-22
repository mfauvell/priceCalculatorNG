package es.jysa.priceCalculatorNG.prestaData;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

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
	

}
