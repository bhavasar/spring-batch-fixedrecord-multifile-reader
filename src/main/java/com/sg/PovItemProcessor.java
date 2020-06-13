package com.sg;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sg.poc.model.BaseRecord;
import com.sg.poc.model.ContraRecord;
import com.sg.poc.model.ContractRecord;
import com.sg.poc.model.ContractUnderlyingAssets;
import com.sg.poc.model.ContractValuationRecord;

public class PovItemProcessor implements ItemProcessor<ContraRecord, ContraRecord> {

	private static AtomicInteger count = new AtomicInteger(0);
	private static final Logger log = LoggerFactory.getLogger(PovItemProcessor.class);

    @Override
    public ContraRecord process(final ContraRecord contraRecord) throws Exception {
    		
        log.info("*****[" + count.addAndGet(1) + "]Converting (" + contraRecord + ") into (" + contraRecord + ")");     
        processContraRecord(contraRecord);
        return contraRecord;
    }
    
    public static void copyKeys(final BaseRecord parentRecord, List<? extends BaseRecord> childRecords) {
    	if(childRecords!=null && childRecords.size() > 0) {	
    		childRecords.parallelStream().forEach((child) -> child.setParentUUID(parentRecord.getGenUUID()));
    	} 	
    }
    
    public static void processContraRecord(final ContraRecord contraRecord) {
    	List<ContractRecord> crecs = contraRecord.getContractRecords();
    	copyKeys(contraRecord,  crecs);
    	
    	crecs.parallelStream().forEach( (contract ) -> {
    		List<ContractUnderlyingAssets> cuas = contract.getContractUnderlyingAssets();
    		copyKeys(contract, cuas );
    		cuas.stream().forEach((cua) -> {
    			copyKeys(cua, cua.getContractBandGuaranteed() );
    		});
    		
    		List<ContractValuationRecord> cvrs  = contract.getContractValuationRecords();
    		copyKeys(contract, cvrs );
    	});
    	
    }

}
