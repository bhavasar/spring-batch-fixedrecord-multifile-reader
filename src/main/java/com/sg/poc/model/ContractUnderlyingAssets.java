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
		return "\n\t\t\t\t\t ContractUnderlyingAssets [getSubmitterCode()=" + getSubmitterCode() + ", getRecordType()="
				+ getRecordType() + ", getSequenceNumber()=" + getSequenceNumber() + ", getGenUUID()=" + getGenUUID()
				+ ", getParentUUID()=" + getParentUUID() + ", contractNumber=" + contractNumber + ", cusip=" + cusip
				+ ", contractStatus=" + contractStatus + ", \n\t\t\t\t\t\t contractBandGuaranteed=" + contractBandGuaranteed + "]";
	}
	
	
	
	
}
