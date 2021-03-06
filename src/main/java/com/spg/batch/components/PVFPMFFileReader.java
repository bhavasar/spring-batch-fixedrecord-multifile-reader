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
package com.spg.batch.components;


import java.util.ArrayList;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import com.sg.poc.model.*;

/**
 * @author Saravana Ganesan
 */
public class PVFPMFFileReader implements ResourceAwareItemReaderItemStream<ContraRecord> {

	private Object curItem = null;

	private ResourceAwareItemReaderItemStream<Object> delegate;

	public PVFPMFFileReader(ResourceAwareItemReaderItemStream<Object> delegate) {
		this.delegate = delegate;
	}

	public ContraRecord read() throws Exception {
		ContraRecord contraRecord = null;
		System.out.println("Reader Reading ...." );
		if(curItem == null) {
			curItem = delegate.read();
		}

		if(curItem != null && curItem instanceof SubmittingHeader) {
			SubmittingHeader submittingHeader = (SubmittingHeader) curItem;
			curItem = null;
			curItem = delegate.read();
		}
		
			if(curItem != null && curItem instanceof ContraRecord) {
				contraRecord = (ContraRecord) curItem;
								
				contraRecord.setContractRecords(new ArrayList<ContractRecord>());				
				curItem = peekAlways();
				while(curItem!=null && curItem instanceof ContractRecord) {
					contraRecord.getContractRecords().add((ContractRecord) curItem);
					ContractRecord contractRecord = (ContractRecord) curItem;
					contractRecord.setContractValuationRecords(new ArrayList<ContractValuationRecord>());
					curItem = peekAlways() ;
					while(curItem!=null &&  curItem instanceof ContractValuationRecord) {
						ContractValuationRecord contractValuationRecord = (ContractValuationRecord) curItem;
						contractRecord.getContractValuationRecords().add(contractValuationRecord);
						curItem = peekAlways();
					}
					contractRecord.setContractUnderlyingAssets(new ArrayList<ContractUnderlyingAssets>());
					while(curItem!=null &&  curItem instanceof ContractUnderlyingAssets) {
						ContractUnderlyingAssets contractUnderlyingAssets = (ContractUnderlyingAssets) curItem;
						contractRecord.getContractUnderlyingAssets().add(contractUnderlyingAssets);
						
						curItem = peekAlways();
						contractUnderlyingAssets.setContractBandGuaranteed(new ArrayList<ContractBandGuaranteed>());
						while(curItem!=null &&  curItem instanceof ContractBandGuaranteed) {
							ContractBandGuaranteed contractBandGuaranteed = (ContractBandGuaranteed) curItem;
							contractUnderlyingAssets.getContractBandGuaranteed().add(contractBandGuaranteed);
							curItem = peekAlways();
						}
						
					}
					
				}
				
			}

		return contraRecord;
	}

	private Object peek() throws Exception {
		if (curItem == null) {
			curItem = delegate.read();
		}
		return curItem;
	}
	private Object peekAlways() throws Exception {
		curItem = delegate.read();
		return curItem;
	}

	public void close() throws ItemStreamException {
		delegate.close();
	}

	public void open(ExecutionContext arg0) throws ItemStreamException {
		delegate.open(arg0);
	}

	public void update(ExecutionContext arg0) throws ItemStreamException {
		delegate.update(arg0);
	}

	@Override
	public void setResource(Resource resource) {
		this.delegate.setResource(resource);
	}
}
