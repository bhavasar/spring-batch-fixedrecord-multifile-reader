/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sg.poc.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Michael Minella
 */
public class ContraRecord  extends BaseRecord  {

	private String accountNumber;
	private Date transactionDate;
	private Double amount;

	private List<ContractRecord> contractRecords;


	private DateFormat formatter =
			new SimpleDateFormat("MM/dd/yyyy");

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDateString() {
		return this.formatter.format(this.transactionDate);
	}
	
	public List<ContractRecord> getContractRecords() {
		return contractRecords;
	}

	public void setContractRecords(List<ContractRecord> contractRecords) {
		this.contractRecords = contractRecords;
	}

	@Override
	public String toString() {
		return "ContraRecord [getSubmitterCode()=" + getSubmitterCode() + ", getRecordType()=" + getRecordType()
				+ ", getSequenceNumber()=" + getSequenceNumber() + ", getGenUUID()=" + getGenUUID()
				+ ", getParentUUID()=" + getParentUUID() + ", accountNumber=" + accountNumber + ", transactionDate="
				+ transactionDate + ", amount=" + amount + ",\n\t contractRecords=" + contractRecords + ", formatter="
				+ formatter + "]";
	}

	
}
