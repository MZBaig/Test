package com.ge.fsmp.connector.engines;

import java.util.List;
import java.util.Map;

import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;

/**
 * Created by Gagandeep Singh on 9/21/16.
 */
public interface IOutageEngine {

	List<AggOutage> getAggOutages(List<Project> projects, 
								  Map<String,List<Assignments>> assignmentMap,
								  Map<String, CustomerDetails> customerMap, 
								  Map<String, List<Equipment>> esnMap); 
	
	
	AggOutage getAggOutage(Project project, List<Assignments> assignments,
						   List<Equipment> equipments,CustomerDetails customerdetails);
	
}
