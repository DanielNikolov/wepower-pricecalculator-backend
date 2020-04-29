package org.wepower.pricecalculator.domain;

public class CalculationResult {
	private boolean error;
	private boolean success;
	private String message;
	private double energyPrice;
	private double lgcPrice;
	private double totalPrice;
	
	public CalculationResult() {
		this.energyPrice = 0.00;
		this.lgcPrice = 0.00;
		this.totalPrice = 0.00;
		this.error = false;
		this.success = false;
		this.message = "";
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getEnergyPrice() {
		return energyPrice;
	}

	public void setEnergyPrice(double energyPrice) {
		this.energyPrice = energyPrice;
	}

	public double getLgcPrice() {
		return lgcPrice;
	}

	public void setLgcPrice(double lgcPrice) {
		this.lgcPrice = lgcPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
