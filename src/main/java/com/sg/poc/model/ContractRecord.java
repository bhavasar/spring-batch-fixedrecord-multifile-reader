package com.sg.poc.model;

import java.util.List;

public class ContractRecord extends BaseRecord {


	private String contractNumber;
	private String cusip;
	private String contractStatus;
	
	private List<ContractValuationRecord> contractValuationRecords;
	private List<ContractUnderlyingAssets> contractUnderlyingAssets;
	
	
	
	public List<ContractUnderlyingAssets> getContractUnderlyingAssets() {
		return contractUnderlyingAssets;
	}
	public void setContractUnderlyingAssets(List<ContractUnderlyingAssets> contractUnderlyingAssets) {
		this.contractUnderlyingAssets = contractUnderlyingAssets;
	}
	public List<ContractValuationRecord> getContractValuationRecords() {
		return contractValuationRecords;
	}
	public void setContractValuationRecords(List<ContractValuationRecord> contractValuationRecords) {
		this.contractValuationRecords = contractValuationRecords;
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
		builder.append("ContractRecord [contractNumber=");
		builder.append(contractNumber);
		builder.append(", cusip=");
		builder.append(cusip);
		builder.append(", contractStatus=");
		builder.append(contractStatus);
		builder.append(", contractValuationRecords=");
		builder.append(contractValuationRecords);
		builder.append(", getSubmitterCode()=");
		builder.append(getSubmitterCode());
		builder.append(", getRecordType()=");
		builder.append(getRecordType());
		builder.append(", getSequenceNumber()=");
		builder.append(getSequenceNumber());
		builder.append(",\n\t\t\t contractValuationRecords="+contractValuationRecords); 
		builder.append(",\\n\\t\\t\\t  contractUnderlyingAssets="+contractUnderlyingAssets);
		builder.append("]");
		return builder.toString();
	}

	
	
}
