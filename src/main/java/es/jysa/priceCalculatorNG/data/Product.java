package es.jysa.priceCalculatorNG.data;

public class Product {
	
	private String reference; //Referencia en el prestashop
	private String code; //Código de la pieza
	private Price price;

	public Product(String reference, String code, Price price) {
		if (reference == null || reference.equals("")) {
			throw new IllegalArgumentException("Reference must be had a valid reference");
		}
		this.reference = reference;
		this.code = code;
		this.price = price;
	}

	public String getReference() {
		return reference;
	}

	public String getCode() {
		return code;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price newPrice) {
		price = newPrice;
	}

	public void setCode(String newCode) {
		code = newCode;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Product) {
			Product product = (Product) object;
			return price.equals(product.getPrice()) && reference.equals(product.getReference()) && code.equals(product.getCode());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (reference.hashCode() + 23 + code.hashCode()) / price.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder productString = new StringBuilder();
		productString.append("Reference: " + reference);
		productString.append(", Code: " + code);
		productString.append(", Price: " + price.toString());
		return productString.toString();
	}
}
