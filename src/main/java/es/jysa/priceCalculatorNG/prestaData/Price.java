package es.jysa.priceCalculatorNG.prestaData;

import java.time.LocalDate;

public class Price {
	
	private double cost;
	private double pvp;
	private LocalDate date;

	public Price(LocalDate date, double cost, double pvp) {
		if (cost < 0) {
			throw new IllegalArgumentException("Cost: " + cost + " is negative");
		}
		if (pvp < 0) {
			throw new IllegalArgumentException("Pvp: " + pvp + " is negative");
		}
		if (Double.compare(cost, pvp) > 0) {
			throw new IllegalArgumentException("Cost: " + cost + " is greater than Pvp:" + pvp);
		}
		this.date = date;
		this.cost = cost;
		this.pvp = pvp;
	}

	public double getCost() {
		return cost;
	}

	public double getPvp() {
		return pvp;
	}

	public LocalDate getDate() {
		return date;
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
			return ((Double.compare(cost, price.getCost()) == 0)? true : false) && ((Double.compare(pvp, price.getPvp()) == 0)? true : false) && date == price.getDate();
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (((pvp + cost) / (date.getYear() + date.getMonthValue() + date.getDayOfMonth())) - 23);
	}

	@Override
	public String toString() {
		StringBuilder priceString = new StringBuilder();
		priceString.append("Date: " + date.toString());
		priceString.append(", Cost: " + cost);
		priceString.append(", Pvp: " + pvp);
		return priceString.toString();
	}
}
