package org.wepower.pricecalculator.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENERGY_PRICES")
public class EnergyPrices {

	@Id
	@Column(name = "ENERGY_PRICES_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "QUARTER")
	private String quarter;
	
	@Column(name = "MINING_SHAPE")
	private BigDecimal mining;
	
	@Column(name = "INDUSTRIAL_SHAPE")
	private BigDecimal industrial;

	@Column(name = "COMMERCIAL_SHAPE")
	private BigDecimal commercial;
	
	@Column(name = "ENERGY_PRICE")
	private BigDecimal energy;
	
	@Column(name = "LGC_PRICE")
	private BigDecimal lgc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public BigDecimal getMining() {
		return mining;
	}

	public void setMining(BigDecimal mining) {
		this.mining = mining;
	}

	public BigDecimal getIndustrial() {
		return industrial;
	}

	public void setIndustrial(BigDecimal industrial) {
		this.industrial = industrial;
	}

	public BigDecimal getCommercial() {
		return commercial;
	}

	public void setCommercial(BigDecimal commercial) {
		this.commercial = commercial;
	}

	public BigDecimal getEnergy() {
		return energy;
	}

	public void setEnergy(BigDecimal energy) {
		this.energy = energy;
	}

	public BigDecimal getLgc() {
		return lgc;
	}

	public void setLgc(BigDecimal lgc) {
		this.lgc = lgc;
	}
}
