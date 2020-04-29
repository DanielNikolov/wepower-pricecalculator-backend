package org.wepower.pricecalculator.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.wepower.pricecalculator.data.entity.EnergyPrices;
import org.wepower.pricecalculator.data.repository.EnergyPricesRepository;
import org.wepower.pricecalculator.domain.CalculationResult;


@RunWith(MockitoJUnitRunner.class)
public class PriceCalculatorServiceTest {
	
	private ArrayList<EnergyPrices> priceRecords;
	private ArrayList<String> quarters;
	
	@Before
	public void init() {
		priceRecords = new ArrayList<EnergyPrices>();
		EnergyPrices priceRecord = new EnergyPrices();
		priceRecord.setId(1L);
		priceRecord.setQuarter("Q4-2020");
		priceRecord.setIndustrial(new BigDecimal(2.00));
		priceRecord.setMining(new BigDecimal(1.00));
		priceRecord.setCommercial(new BigDecimal(0.5));
		priceRecord.setEnergy(new BigDecimal(1.00));
		priceRecord.setLgc(new BigDecimal(1.00));
		priceRecords.add(priceRecord);
		
		quarters = new ArrayList<String>();
		quarters.add("Q3-2020");
		quarters.add("Q4-2020");
	}
	
	@InjectMocks
	private PriceCalculatorService priceCalculatorService;
	
	@Mock
	private EnergyPricesRepository energyPricesRepository;
	
	@Test
	public void testValidPrices() {
		Mockito.when(energyPricesRepository.findByQuarterIn(quarters)).thenReturn(priceRecords);
		CalculationResult result =
				priceCalculatorService.calculateTotalPrices(
						1597093200000L,
						1605045600000L,
						"mining",
						"energy");
		assertTrue(result.getEnergyPrice() > 0);
	}
	
	@Test
	public void testIncorrectDates() {
		CalculationResult result =
				priceCalculatorService.calculateTotalPrices(1605045600000L, 1597093200000L, "mining", "energy");
		System.out.println(result.getEnergyPrice());
		assertTrue(result.isError());
	}
	
	@Test
	public void testEmptyList() {
		Mockito.when(energyPricesRepository.findByQuarterIn(quarters)).thenReturn(new ArrayList<EnergyPrices>());
		CalculationResult result =
				priceCalculatorService.calculateTotalPrices(1597093200000L, 1605045600000L, "mining", "energy");
		System.out.println(result.getEnergyPrice());
		assertTrue(result.isError());
	}

}
