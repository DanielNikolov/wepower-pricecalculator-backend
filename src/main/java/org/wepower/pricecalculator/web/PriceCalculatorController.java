/**
 * Processes requests for price calculations
 */
package org.wepower.pricecalculator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wepower.pricecalculator.domain.CalculationResult;
import org.wepower.pricecalculator.service.PriceCalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Daniel
 *
 */
@RestController("/api/wepower/calculateprice")
@Api(value = "Energy Price Calculator", description = "Performs energy price calculation")
public class PriceCalculatorController {
	
	@Autowired
	private PriceCalculatorService priceCalculatorService;

	@CrossOrigin(origins = "*")
	@GetMapping("/api/wepower/calculateprice")
	@ApiOperation(value = "Calculates total and product prices", response = CalculationResult.class)
	public CalculationResult calculatePrice(@RequestParam(name = "start", required = true) Long startPeriod,
			@RequestParam(name = "end", required = true) Long endPeriod,
			@RequestParam(name = "customer", required = true) String customerType,
			@RequestParam(name = "product", required = true) String energyType) {
		return priceCalculatorService.calculateTotalPrices(startPeriod, endPeriod, customerType, energyType);
	}
}
