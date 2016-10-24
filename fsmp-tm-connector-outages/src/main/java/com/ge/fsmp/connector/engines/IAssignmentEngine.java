package com.ge.fsmp.connector.engines;

import java.util.List;

import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;


/**
 * Created by Gagandeep Singh on 9/21/16.
 */
public interface IAssignmentEngine {

	
	List<Assignments> getAssignmentList(String input);
	Project getProject(String input);
	CustomerDetails getCustomer(String input);
	List<Equipment> getEquipments(String input);
}
