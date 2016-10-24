package com.ge.fsmp.connector.domain.converters.impl;

import org.springframework.stereotype.Service;

import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.engines.IDomainViewConverterEngine;
import com.ge.fsmp.core.models.outages.Outage;

/**
 * Created by Gagandeep Singh on 10/04/16.
 */
@Service
public class OutageConverter implements IDomainViewConverterEngine<AggOutage, Outage> {


	@Override
	public Outage convertToView(AggOutage domain) {
        if(domain == null){
            return null;
        }
        
        return new Outage(domain.getId(), null, domain.getEsn(), domain.getProjectType(), domain.getCustomerName(), domain.getProjectStartDate(),
        		domain.getProjectCompletionDate(),domain.getCountry(),domain.getCity(), domain.getOutageTypeList(), domain.getStatus(), domain.getDescription(),domain.getAdditonalFields());
        }
	

	@Override
	public AggOutage convertToDomain(Outage view) {
		
		throw new UnsupportedOperationException("Cannot be converted to domain");
	}

}
