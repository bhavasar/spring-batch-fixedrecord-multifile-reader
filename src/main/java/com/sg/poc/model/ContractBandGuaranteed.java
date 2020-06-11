package com.sg.poc.model;

public class ContractBandGuaranteed extends BaseRecord {

	private String contractNumber;
	private String cusip;
	private String depositGuaranteedStartDate;
	
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getDepositGuaranteedStartDate() {
		return depositGuaranteedStartDate;
	}
	public void setDepositGuaranteedStartDate(String depositGuaranteedStartDate) {
		this.depositGuaranteedStartDate = depositGuaranteedStartDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContractBandGuaranteed [getSubmitterCode()=");
		builder.append(getSubmitterCode());
		builder.append(", getRecordType()=");
		builder.append(getRecordType());
		builder.append(", getSequenceNumber()=");
		builder.append(getSequenceNumber());
		builder.append(", contractNumber=");
		builder.append(contractNumber);
		builder.append(", cusip=");
		builder.append(cusip);
		builder.append(", depositGuaranteedStartDate=");
		builder.append(depositGuaranteedStartDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
