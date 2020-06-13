package com.sg.poc.model;

import java.math.BigDecimal;

public class ContractValuationRecord extends BaseRecord {
	private String contractNumber;
	private BigDecimal contractValueAmount1;
	private String contractValueQualifier1;
	private BigDecimal contractValueAmount2;
	private String contractValueQualifier2;
	
	
	
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public BigDecimal getContractValueAmount1() {
		return contractValueAmount1;
	}
	public void setContractValueAmount1(BigDecimal contractValueAmount1) {
		this.contractValueAmount1 = contractValueAmount1;
	}
	public String getContractValueQualifier1() {
		return contractValueQualifier1;
	}
	public void setContractValueQualifier1(String contractValueQualifier1) {
		this.contractValueQualifier1 = contractValueQualifier1;
	}
	public BigDecimal getContractValueAmount2() {
		return contractValueAmount2;
	}
	public void setContractValueAmount2(BigDecimal contractValueAmount2) {
		this.contractValueAmount2 = contractValueAmount2;
	}
	public String getContractValueQualifier2() {
		return contractValueQualifier2;
	}
	public void setContractValueQualifier2(String contractValueQualifier2) {
		this.contractValueQualifier2 = contractValueQualifier2;
	}
	@Override
	public String toString() {
		return "\n\t\t\t\t ContractValuationRecord [contractNumber=" + contractNumber + ", contractValueAmount1="
				+ contractValueAmount1 + ", contractValueQualifier1=" + contractValueQualifier1
				+ ", contractValueAmount2=" + contractValueAmount2 + ", contractValueQualifier2="
				+ contractValueQualifier2 + "]";
	}
	
}
