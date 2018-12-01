package prestaData;

import java.time.LocalDate;

public final class PrestaFactory {
	private PrestaFactory() {
		
	}

	public static Price getPrice(LocalDate date, double cost, double pvp) throws IllegalArgumentException {
		return new Price(date, cost, pvp);
	}

	public static Product getProduct(String reference, String code, Price price) throws IllegalArgumentException {
		return new Product(reference, code, price);
	}
}
