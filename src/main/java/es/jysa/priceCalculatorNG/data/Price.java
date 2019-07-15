package es.jysa.priceCalculatorNG.data;

public class Price {
	
	private double cost;
	private double pvp;

	public Price(double cost, double pvp) {
		if (cost < 0) {
			throw new IllegalArgumentException("Cost: " + cost + " is negative");
		}
		if (pvp < 0) {
			throw new IllegalArgumentException("Pvp: " + pvp + " is negative");
		}
		this.cost = cost;
		this.pvp = pvp;
	}

	public double getCost() {
		return cost;
	}

	public double getPvp() {
		return pvp;
	}

	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (object instanceof Price) {
			Price price = (Price) object;
			return ((Double.compare(cost, price.getCost()) == 0)? true : false) && ((Double.compare(pvp, price.getPvp()) == 0)? true : false);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (pvp + cost) /  23;
	}

	@Override
	public String toString() {
		StringBuilder priceString = new StringBuilder();
		priceString.append(", Cost: " + cost);
		priceString.append(", Pvp: " + pvp);
		return priceString.toString();
	}
}
