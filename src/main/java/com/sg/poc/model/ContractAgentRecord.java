package com.sg.poc.model;

public class ContractAgentRecord extends BaseRecord {

	private String contractNumber;
	private String agentTaxId;
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getAgentTaxId() {
		return agentTaxId;
	}
	public void setAgentTaxId(String agentTaxId) {
		this.agentTaxId = agentTaxId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContractAgentRecord [contractNumber=");
		builder.append(contractNumber);
		builder.append(", agentTaxId=");
		builder.append(agentTaxId);
		builder.append(", getSubmitterCode()=");
		builder.append(getSubmitterCode());
		builder.append(", getRecordType()=");
		builder.append(getRecordType());
		builder.append(", getSequenceNumber()=");
		builder.append(getSequenceNumber());
		builder.append("]");
		return builder.toString();
	}
	
	
}
