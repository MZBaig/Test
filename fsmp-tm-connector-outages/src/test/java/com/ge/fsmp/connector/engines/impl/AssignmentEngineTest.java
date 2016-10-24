package com.ge.fsmp.connector.engines.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IAssignmentEngine;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentEngineTest {
	
    private IAssignmentEngine assignmentEngine;
    
    @Before
    public void setUp(){
    	assignmentEngine = new AssignmentEngine();
    }
    
    
    @Test
    public void getAssignmentList()throws Exception{
    	
    	String assignments= new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader()
                .getResource("Assignments.json")
                 .toURI())));
    	
    	List<Assignments> assigns = assignmentEngine.getAssignmentList(assignments);
    	assertNotNull(assigns);
    	assertThat(assigns.size(), is(35));
    	
    }
   
    @Test
    public void getProject()throws Exception{
    	
    	String projects = new String(Files.readAllBytes(
                 Paths.get(getClass().getClassLoader()
                .getResource("Project.json")
                 .toURI())));
      Project project = assignmentEngine.getProject(projects);
      assertNotNull(project);
      assertEquals(project.getProjectID(), "5485");
    	
    	
    }
    
    
    @Test 
    public void getCustomer() throws Exception{
    	String customers = new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader()
                .getResource("Customer.json")
                 .toURI())));
    	CustomerDetails customer = assignmentEngine.getCustomer(customers);
    	assertEquals(customer.getName(), "OMAN LIQUEFIED NATURAL GAS L L C");
    	
    }
    
    @Test 
    public void getEquipment() throws Exception{
    	String equipments =new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader()
                .getResource("Equipment.json")
                 .toURI())));
    	List<Equipment> equipment = assignmentEngine.getEquipments(equipments);
    	assertEquals(equipment.get(0).getSerialNumber(),"G06929");
    	
    	
    }
    
    
    
}
