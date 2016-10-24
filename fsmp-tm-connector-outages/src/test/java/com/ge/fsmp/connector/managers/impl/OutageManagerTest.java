package com.ge.fsmp.connector.managers.impl;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IAssignmentEngine;
import com.ge.fsmp.connector.engines.IDomainViewConverterEngine;
import com.ge.fsmp.connector.engines.IOutageEngine;
import com.ge.fsmp.connector.ras.IOutagesClient;
import com.ge.fsmp.core.models.outages.Outage;

@RunWith(MockitoJUnitRunner.class)
public class OutageManagerTest {

	@Mock
    private IOutagesClient apiGatewayClient;

    @Mock
    private IAssignmentEngine assignmentEngine;
    
    @Mock
    private IDomainViewConverterEngine<AggOutage, Outage> outageConverter;
    
    @Mock
    private IOutageEngine outageEngine;
    
    @InjectMocks
    private OutageManager outageManager;
    
    @Before
    public void setUp(){
    	
    	
    }
    
    @Test
    public void getOutages()throws Exception{
    	String assignments= new String("1231");
    	String customer = new String("1234");
    	String projects =new String("1234");
    	String equipment =new String("1234");
    	
    	Project project = new Project("Y", "1234","12345", "Demo","123","CS", "Outage",
    									"2015-11-01T00:00:00", "2015-12-01T00:00:00");
    	
    	List<Equipment> equipments= new ArrayList<Equipment>();
     	Equipment equipmentObj  = new Equipment("Val123");
		equipments.add(equipmentObj);
		
		CustomerDetails customerObj = new CustomerDetails("OMAN LIQUEFIED NATURAL GAS L L C",
                "SUR", 
                "SUR", 
                "OMAN");
		
		List<Assignments> assignmentsList = new ArrayList<Assignments>();
		Assignments assignmentObj = new Assignments("W01234", "1040450", 
													"2015-11-01T00:00:00",
													"2015-11-01T00:00:00", 
		    										"12345");
		assignmentsList.add(assignmentObj);
		
		
		
		List<AggOutage> aggOutages = new ArrayList<AggOutage>();
		AggOutage aggOutage = new AggOutage();
		aggOutage.setId((long)12345);
		aggOutages.add(aggOutage);
		
    	
    	when(apiGatewayClient.getAssignedTasks(anyString())).thenReturn(assignments);
    	when(apiGatewayClient.getCustomer(anyString())).thenReturn(customer);
    	when(apiGatewayClient.getProject(anyString())).thenReturn(projects);
    	when(apiGatewayClient.getEquipment(anyString())).thenReturn(equipment);
    	
    	when(assignmentEngine.getAssignmentList(anyString())).thenReturn(assignmentsList);
    	when(assignmentEngine.getCustomer(anyString())).thenReturn(customerObj);
    	when(assignmentEngine.getEquipments(anyString())).thenReturn(equipments);
    	when(assignmentEngine.getProject(anyString())).thenReturn(project);
    	
    	when(outageEngine.getAggOutages(anyListOf(Project.class),
    			                        Matchers.<Map<String,List<Assignments>>>any(),
    									anyMapOf(String.class,CustomerDetails.class),
    									Matchers.<Map<String, List<Equipment>>>any()))
    	.thenReturn(aggOutages);
    	
    	when(outageConverter.convertToView(Matchers.any(AggOutage.class))).
    	thenReturn(new Outage());
    	                     
    	outageManager.getOutages(123);
    	
    	
    	verify(apiGatewayClient).getAssignedTasks(anyString());
    	verify(apiGatewayClient).getProject(anyString());
    	verify(apiGatewayClient).getCustomer(anyString());
    	verify(apiGatewayClient).getEquipment(anyString());
    	
    	
    	verify(assignmentEngine).getAssignmentList(anyString());
    	verify(assignmentEngine).getCustomer(anyString());
    	verify(assignmentEngine).getEquipments(anyString());
    	verify(assignmentEngine).getProject(anyString());
    	
    	
    	verify(outageEngine).getAggOutages(anyListOf(Project.class),
    									   Matchers.<Map<String,List<Assignments>>>any(),
    			                           anyMapOf(String.class,CustomerDetails.class),
    			                           Matchers.<Map<String, List<Equipment>>>any());
    	
    	verify(outageConverter).convertToView(any(AggOutage.class));
    } 
	
}
