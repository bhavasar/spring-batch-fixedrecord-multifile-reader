package com.sg.poc.model;

import java.util.UUID;

public class BaseRecord {
	private String submitterCode;
	private String recordType;
	private String sequenceNumber;
	private final String genUUID = UUID.randomUUID().toString();
	private String parentUUID; 
	
	public String getSubmitterCode() {
		return submitterCode;
	}
	public void setSubmitterCode(String submitterCode) {
		this.submitterCode = submitterCode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getGenUUID() {
		return genUUID;
	}
	public String getParentUUID() {
		return parentUUID;
	}
	public void setParentUUID(String parentUUID) {
		this.parentUUID = parentUUID;
	}
	@Override
	public String toString() {
		return "BaseRecord [submitterCode=" + submitterCode + ", recordType=" + recordType + ", sequenceNumber="
				+ sequenceNumber + ", genUUID=" + genUUID + ", parentUUID=" + parentUUID + "]";
	}
	
	
}
