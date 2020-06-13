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
		return "\n\t\t\t\t\t\t ContractBandGuaranteed [getSubmitterCode()=" + getSubmitterCode() + ", getRecordType()="
				+ getRecordType() + ", getSequenceNumber()=" + getSequenceNumber() + ", getGenUUID()=" + getGenUUID()
				+ ", getParentUUID()=" + getParentUUID() + ", contractNumber=" + contractNumber + ", cusip=" + cusip
				+ ", depositGuaranteedStartDate=" + depositGuaranteedStartDate + "]";
	}

	
	
}
