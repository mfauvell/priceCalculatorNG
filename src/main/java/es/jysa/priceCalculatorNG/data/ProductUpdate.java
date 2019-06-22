package es.jysa.priceCalculatorNG.data;

public class ProductUpdate extends Product {
	
	private Price oldPrice;

	public ProductUpdate(String reference, String code, Price newPrice, Price oldPrice) {
		super(reference, code, newPrice);
		this.oldPrice = oldPrice;
	}

	public Price getOldPrice() {
		return oldPrice;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof ProductUpdate) {
			ProductUpdate productUpdate = (ProductUpdate) object;
			return super.getPrice().equals(productUpdate.getPrice()) && super.getReference().equals(productUpdate.getReference()) && super.getCode().equals(productUpdate.getCode())
					&& oldPrice.equals(productUpdate.getOldPrice());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode() + oldPrice.hashCode() /23;
	}
	
	@Override
	public String toString() {
		StringBuilder productUpdateString = new StringBuilder();
		productUpdateString.append(super.toString());
		productUpdateString.append(", OldPrice: " + oldPrice);
		return productUpdateString.toString();
	}
}
