package com.sg.poc.model;

import java.util.List;

public class ContractUnderlyingAssets extends BaseRecord {


	private String contractNumber;
	private String cusip;
	private String contractStatus;
	private List<ContractBandGuaranteed> contractBandGuaranteed ;

	
	
	public List<ContractBandGuaranteed> getContractBandGuaranteed() {
		return contractBandGuaranteed;
	}
	public void setContractBandGuaranteed(List<ContractBandGuaranteed> contractBandGuaranteed) {
		this.contractBandGuaranteed = contractBandGuaranteed;
	}
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
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContractUnderlyingAssets [getSubmitterCode()=");
		builder.append(getSubmitterCode());
		builder.append(", getRecordType()=");
		builder.append(getRecordType());
		builder.append(", getSequenceNumber()=");
		builder.append(getSequenceNumber());
		builder.append(", contractNumber=");
		builder.append(contractNumber);
		builder.append(", cusip=");
		builder.append(cusip);
		builder.append(", contractStatus=");
		builder.append(contractStatus);
		builder.append(", contractBandGuaranteed=" + contractBandGuaranteed);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
