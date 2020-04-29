package org.wepower.pricecalculator.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wepower.pricecalculator.domain.CalculationResult;
import org.wepower.pricecalculator.service.PriceCalculatorService;
import org.wepower.pricecalculator.web.PriceCalculatorController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PriceCalculatorController.class)
public class PriceCalculatorControllerTest {
	
	@MockBean
	private PriceCalculatorService priceCalculatorService;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testValidPrimeNumber() throws Exception {
		CalculationResult result = new CalculationResult();
		result.setError(false);
		BDDMockito.given(priceCalculatorService.calculateTotalPrices(
				1635976800000L,
				1649019600000L,
				"mining",
				"energy")).willReturn(result);		
		this.mockMvc.perform(get("/api/wepower/calculateprice?start=1635976800000&end=1649019600000&customer=mining&product=energy"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"error\":false")));
	}
}
