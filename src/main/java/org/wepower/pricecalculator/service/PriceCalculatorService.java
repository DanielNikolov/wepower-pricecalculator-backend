package org.wepower.pricecalculator.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wepower.pricecalculator.data.entity.EnergyPrices;
import org.wepower.pricecalculator.data.repository.EnergyPricesRepository;
import org.wepower.pricecalculator.domain.CalculationResult;


@Service
public class PriceCalculatorService {

	private final EnergyPricesRepository energyPricesRepository; 
	
	@Autowired
	public PriceCalculatorService(EnergyPricesRepository energyPricesRepository) {
		this.energyPricesRepository = energyPricesRepository;
	}
	
	private Map<String, Double> getAvgQuarterPricePerProduct(List<EnergyPrices> prices, String customerType, String[] productTypes) throws Exception {
		Map<String, Double> totalPricesMap = new HashMap<String, Double>();
		try {
		for (String productType : productTypes) {
			BigDecimal productPrice = new BigDecimal(0.00);
			for (EnergyPrices priceRecord : prices) {
				BigDecimal shape = getFieldValue(priceRecord, customerType);
				BigDecimal quarterPrice = getFieldValue(priceRecord, productType);
				productPrice = productPrice.add(quarterPrice.multiply(shape));				
			}
			totalPricesMap.put(productType, productPrice.doubleValue() / prices.size());
		}
		} catch (NoSuchFieldException nsfe) {
			throw new Exception(nsfe.getMessage());
		} catch (SecurityException se) {
			throw new Exception(se.getMessage());
		} catch (IllegalArgumentException iae) {
			throw new Exception(iae.getMessage());
		} catch (IllegalAccessException iacce) {
			throw new Exception(iacce.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return totalPricesMap;
	}
	
	/**
	 * calculates quarter string like Q4-2020 based on input calendar
	 * @param calendar calendar to be used for calculating the quarter value
	 * @return quarter value, e.g. Q4-2020 
	 */
	private String getQuarterValue(Calendar calendar) {
		return String.format("Q%d-%d", 
				(calendar.get(Calendar.MONTH) / 3) + 1,
				calendar.get(Calendar.YEAR));
	}
	
	/**
	 * get list of start and end quarter values based on start and end period
	 * @param start Start period in ms
	 * @param end End period in ms
	 * @return List of quarter values like Q4-2020, Q5-2020
	 * @throws Exception 
	 */
	private ArrayList<String> getQuarters(Long start, Long end) throws Exception {
		if (end < start) {
			throw new Exception("Invalid time frame");
		}
		ArrayList<String> result = new ArrayList<String>();
		Calendar calStart = Calendar.getInstance();
		calStart.setTimeInMillis(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTimeInMillis(end);
		String startQuarter = getQuarterValue(calStart);
		result.add(startQuarter);
		String endQuarter = getQuarterValue(calEnd);
		while (!startQuarter.equals(endQuarter)) {
			calStart.add(Calendar.MONTH, 3);
			startQuarter = getQuarterValue(calStart);
			result.add(startQuarter);			
		}

		return result;
	}
	
	private BigDecimal getFieldValue(EnergyPrices energyPrice, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = EnergyPrices.class.getDeclaredField(fieldName);
		field.setAccessible(true);
		return (BigDecimal) field.get(energyPrice);
	}
	
	public CalculationResult calculateTotalPrices(Long start, Long end, String customerType, String product) {
		CalculationResult calculationResult = new CalculationResult();
		try {
			List<EnergyPrices> priceRecords = energyPricesRepository.findByQuarterIn(getQuarters(start, end));
			Map<String, Double> avgProductsPrices = getAvgQuarterPricePerProduct(priceRecords, customerType, product.split(","));
			Double energyPrice = avgProductsPrices.getOrDefault("energy", 0.00);
			calculationResult.setEnergyPrice(energyPrice);
			Double lgcPrice = avgProductsPrices.getOrDefault("lgc", 0.00);
			calculationResult.setLgcPrice(lgcPrice);
			calculationResult.setTotalPrice(lgcPrice + energyPrice);
			calculationResult.setSuccess(true);
		} catch (Exception e) {
			calculationResult.setError(true);
			calculationResult.setMessage(e.getMessage());
		}
		return calculationResult;
	}
}
