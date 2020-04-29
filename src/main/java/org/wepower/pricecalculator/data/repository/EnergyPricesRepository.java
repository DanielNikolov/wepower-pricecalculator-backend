package org.wepower.pricecalculator.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wepower.pricecalculator.data.entity.EnergyPrices;

@Repository
public interface EnergyPricesRepository extends CrudRepository<EnergyPrices, Long> {
	
	EnergyPrices findByQuarter(String quarter);
	List<EnergyPrices> findByQuarterIn(List<String> quarters);

}
