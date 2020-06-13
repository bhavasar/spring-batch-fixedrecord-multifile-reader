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
		return "ContractRecord [getSubmitterCode()=" + getSubmitterCode() + ", getRecordType()=" + getRecordType()
				+ ", getSequenceNumber()=" + getSequenceNumber() + ", getGenUUID()=" + getGenUUID()
				+ ", getParentUUID()=" + getParentUUID() + ", contractNumber=" + contractNumber + ", cusip=" + cusip
				+ ", contractStatus=" + contractStatus + 
				"\n\t\t\t, contractValuationRecords=" + contractValuationRecords
				+ ",\n\t\t\t contractUnderlyingAssets=" + contractUnderlyingAssets + "]";
	}
	
	
	
}
