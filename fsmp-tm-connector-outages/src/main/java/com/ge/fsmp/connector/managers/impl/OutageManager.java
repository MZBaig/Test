/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.fsmp.connector.managers.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IAssignmentEngine;
import com.ge.fsmp.connector.engines.IDomainViewConverterEngine;
import com.ge.fsmp.connector.engines.IOutageEngine;
import com.ge.fsmp.connector.managers.IOutageManager;
import com.ge.fsmp.connector.ras.IOutagesClient;
import com.ge.fsmp.core.models.outages.Outage;


/**
 * Created by Gagandeep Singh on 9/21/16.
 */
@Service
public class OutageManager implements IOutageManager{
    private static final Logger LOGGER = LoggerFactory.getLogger(OutageManager.class);


    @Autowired
    private IOutagesClient outagesClient;

    @Autowired
    private IAssignmentEngine assignmentEngine;
    
    @Autowired
    private IDomainViewConverterEngine<AggOutage, Outage> outageConverter;
    
    @Autowired
    private IOutageEngine outageEngine;

    
    
    @Override
    public List<Outage> getOutages(final Integer sso) {
    	
    	List<Assignments> assignments = assignmentEngine.getAssignmentList(outagesClient.getAssignedTasks(sso.toString()));
    	
    	Map<String, List<Assignments>> assignmentMap = assignments.stream()
    							   .collect(Collectors.groupingBy(Assignments::getProjectID,
    			                                         Collectors.mapping(Assignments->Assignments, Collectors.toList())));

    	List<Project> projects = assignmentMap.keySet()
        		                 .stream()
                                 .map(projectId->assignmentEngine.getProject(outagesClient.getProject(projectId)))
                            	 .collect(Collectors.toList());
        
    	Map<String, CustomerDetails> customerMap = assignmentMap.keySet()
        							.stream()
        							.collect(Collectors.toMap(projectId->projectId,
        													projectId->assignmentEngine.getCustomer(outagesClient.getCustomer(projectId))));
        
    	Map<String,List<Equipment>> equipmentMap =  assignmentMap.keySet()
        							 .stream()
                					 .collect(Collectors.toMap(projectId->projectId,
                									        projectId->assignmentEngine.getEquipments(outagesClient.getEquipment(projectId))));
       
        
        List<AggOutage> aggOutage= outageEngine.getAggOutages(projects,assignmentMap, customerMap, equipmentMap);

       return aggOutage.stream()
    		   .map(outageConverter::convertToView)
    		   .collect(Collectors.toList());
    }


	

    @Override
	public Outage getOutageById(Long projectId, Integer sso) {
		
		List<Assignments> assignments = assignmentEngine.getAssignmentList(outagesClient.getAssignedTasks(projectId.toString(),sso.toString()));
		Project project = assignmentEngine.getProject(outagesClient.getProject(projectId.toString()));
		CustomerDetails customer = assignmentEngine.getCustomer(outagesClient.getCustomer(projectId.toString()));
		List<Equipment> equipments = assignmentEngine.getEquipments(outagesClient.getEquipment(projectId.toString()));
		
		AggOutage aggOutage = outageEngine.getAggOutage(project, assignments, equipments, customer);
		
		
		return outageConverter.convertToView(aggOutage);
	}






	@Override
	public List<String> getAssignedUsers(String sso, String role) {
		
	    throw new UnsupportedOperationException("Cannot be converted to domain");
	}






}
