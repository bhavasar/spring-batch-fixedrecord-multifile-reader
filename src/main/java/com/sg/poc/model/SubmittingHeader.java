/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http:www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sg.poc.model;

import java.util.List;


/**
 * @author Saravana Ganesan
 */

public class SubmittingHeader  extends BaseRecord {

	private Long id;

	private String firstName;
	private String middleInitial;
	private String lastName;
	private String addressNumber;
	private String street;
	private String address;
	private String city;
	private String state;
	private String zipCode;

	private List<ContraRecord> c15Records;

	public SubmittingHeader() {
	}

	public SubmittingHeader(String firstName, String middleName, String lastName, String addressNumber, String street, String city, String state, String zipCode) {
		this.firstName = firstName;
		this.middleInitial = middleName;
		this.lastName = lastName;
		this.addressNumber = addressNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<ContraRecord> getTransactions() {
		return c15Records;
	}


	public void setTransactions(List<ContraRecord> transactions) {
		this.c15Records = transactions;
	}

	public String toString3() {
		return "SubmittingHeader{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", middleInitial='" + middleInitial + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zipCode='" + zipCode + '\'' +
				'}';
	}

	
	public String toStringOld1() {
		StringBuilder output = new StringBuilder();

		output.append(firstName);
		output.append(" ");
		output.append(middleInitial);
		output.append(". ");
		output.append(lastName);

		if(c15Records != null&& c15Records.size() > 0) {
			output.append(" has ");
			output.append(c15Records.size());
			output.append(" transactions.");
		} else {
			output.append(" has no transactions.");
		}

		return output.toString();
	}

	public String toString() {
		return "SubmittingHeader{" +
				"firstName='" + firstName + '\'' +
				", middleInitial='" + middleInitial + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", addressNumber='" + addressNumber + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zipCode='" + zipCode + '\'' +
				", \n\t ContraRecord=" + c15Records +
				'}';
	}
}
