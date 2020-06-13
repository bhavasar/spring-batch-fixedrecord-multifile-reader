package com.sg;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sg.poc.model.ContraRecord;

public class PovItemProcessor implements ItemProcessor<ContraRecord, ContraRecord> {

	private static AtomicInteger count = new AtomicInteger(0);
	private static final Logger log = LoggerFactory.getLogger(PovItemProcessor.class);

    @Override
    public ContraRecord process(final ContraRecord header) throws Exception {
    	
    	
        log.info("*****[" + count.addAndGet(1) + "]Converting (" + header + ") into (" + header + ")");
        
        
        return header;
    }

}
