package org.wepower.pricecalculator.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Energy Price Calculation Result")
public class CalculationResult {
	@ApiModelProperty(notes = "Error flag - true if error, false otherwise")
	private boolean error;
	
	@ApiModelProperty(notes = "Error message")
	private String message;
	
	@ApiModelProperty(notes = "Price for product Energy")
	private double energyPrice;
	
	@ApiModelProperty(notes = "Price for product LGC")
	private double lgcPrice;
	
	@ApiModelProperty(notes = "Total Price")
	private double totalPrice;
	
	public CalculationResult() {
		this.energyPrice = 0.00;
		this.lgcPrice = 0.00;
		this.totalPrice = 0.00;
		this.error = false;
		this.message = "";
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
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
