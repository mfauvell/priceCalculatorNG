package prestaData;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	public void createProductAndCheckItsComponents() {
		Price price = mock(Price.class);
		String reference = "MAT001";
		String code = "CDE001";
		
		Product product = PrestaFactory.getProduct(reference, code, price);
		
		assertThat(product.getReference()).isEqualTo(reference);
		assertThat(product.getCode()).isEqualTo(code);
		assertThat(product.getPrice()).isEqualTo(price);
	}
	
	@Test
	public void createProductWithNullOrVoidReferenceThrowsException() {
		Price price = mock(Price.class);
		String reference = "";
		String code = "CDE001";
		
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getProduct(null, code, price);}, "Create a Product without reference must be throw IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> {PrestaFactory.getProduct(reference, code, price);}, "Create a product with a void reference must be throw IllegalArgumentException");
	}
	
	@Test
	public void changePriceOrCodeOfExistentProduct() {
		Price price = mock(Price.class);
		Price newPrice = mock(Price.class);
		String reference = "MAT001";
		String code = "CDE001";
		String newCode = "other code";
		
		Product product = PrestaFactory.getProduct(reference, code, price);
		
		product.setPrice(newPrice);
		product.setCode(newCode);
		
		assertThat(product.getPrice()).isEqualTo(newPrice);
		assertThat(product.getCode()).isEqualTo(newCode);
	}
}
