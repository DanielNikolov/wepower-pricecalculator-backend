/**
 * Processes requests for price calculations
 */
package org.wepower.pricecalculator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wepower.pricecalculator.domain.CalculationResult;
import org.wepower.pricecalculator.service.PriceCalculatorService;

/**
 * @author Daniel
 *
 */
@RestController("/api/wepower/calculateprice")
public class PriceCalculatorController {
	
	@Autowired
	private PriceCalculatorService priceCalculatorService;

	@GetMapping("/api/wepower/calculateprice")
	public CalculationResult calculatePrice(@RequestParam(name = "start", required = true) Long startPeriod,
			@RequestParam(name = "end", required = true) Long endPeriod,
			@RequestParam(name = "customer", required = true) String customerType,
			@RequestParam(name = "product", required = true) String energyType) {
		return priceCalculatorService.calculateTotalPrices(startPeriod, endPeriod, customerType, energyType);
	}
}
