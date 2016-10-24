package com.ge.fsmp.connector.engines.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IAssignmentEngine;



/**
 * Created by Gagandeep Singh on 9/21/16.
 */

@Service 
public class AssignmentEngine implements IAssignmentEngine {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentEngine.class);
	private ObjectMapper mapper;
	
	@Override
	public List<Assignments> getAssignmentList(String input) {
		mapper = new ObjectMapper();
		ArrayNode data = null;
		
		
		try {
			data = (ArrayNode)mapper.readTree(input).get("assignments").get("data");
			return mapper.readValue(data.toString(), new TypeReference<List<Assignments>>() {});
			
		} catch (JsonParseException |JsonMappingException e) {
			LOGGER.error("Error Deserailizing the Assignments Json" + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("I/O Error deserailizing the Assignments Json" +e.getMessage());
		}
		
		
         return  null;
	}
    
	
	
	@Override
	public Project getProject(String input) {
		mapper = new ObjectMapper();
		JsonNode data = null;
		
		try {
			data = mapper.readTree(input).get("project");
			return mapper.readValue(data.toString(), Project.class);
		} catch (JsonParseException | JsonMappingException e) {
			LOGGER.error("Error Deserailizing the Project Json" + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("I/O Error deserailizing the Project Json" + e.getMessage());
		}
		
		
	  return null;
	}



	@Override
	public CustomerDetails getCustomer(String input) {
		mapper = new ObjectMapper();
		JsonNode data = null;
		
		try {
			data = mapper.readTree(input).get("projectCustomer");
			return mapper.readValue(data.toString(), CustomerDetails.class);
		} catch (JsonParseException |JsonMappingException e ) {
			LOGGER.error("Error Deserailizing the CustomerDetails Json" +e.getMessage());
		} catch (IOException e) {
			LOGGER.error("I/O Error deserailizing the CustomerDetails Json" + e.getMessage());
		}
		
	   return null;
	}



	@Override
	public List<Equipment> getEquipments(String input) {
		mapper = new ObjectMapper();
		ArrayNode data = null;
		
	    try {
			data = (ArrayNode)mapper.readTree(input).get("projectEquipments").get("data");
			return mapper.readValue(data.toString(), new TypeReference<List<Equipment>>() {});
		} catch (JsonParseException |JsonMappingException e) {
			LOGGER.error("Error Deserailizing the Equipment Json"+e.getMessage());
		} catch (IOException e) {
			LOGGER.error("I/O Error deserailizing the Equipment Json" +e.getMessage());
		}
	    
		return null;
	}
	
	
}
