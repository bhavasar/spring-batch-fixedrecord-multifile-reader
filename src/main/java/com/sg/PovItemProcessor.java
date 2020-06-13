package com.sg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sg.poc.model.ContraRecord;

public class PovItemProcessor implements ItemProcessor<ContraRecord, ContraRecord> {

	private static int count = 0;
	private static final Logger log = LoggerFactory.getLogger(PovItemProcessor.class);

    @Override
    public ContraRecord process(final ContraRecord header) throws Exception {
    	count++;

        log.info("*****[" + count + "]Converting (" + header + ") into (" + header + ")");
        
        
        return header;
    }

}
