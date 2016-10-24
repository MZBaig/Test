package com.ge.fsmp.connector.ras.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ge.fsmp.connector.engines.ISignatureEngine;
import com.ge.fsmp.connector.ras.IOutagesClient;


/**
 * Created by Gagandeep Singh 9/19/16.
 */
@Component
public class OutageClient implements IOutagesClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutageClient.class);
   
     
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ISignatureEngine signatureEngine;
   
    @Value("${vcap.services.tms-env-service.credentials.ASSIGNMENTS_URL}")
    private String assignmentUrl;
    
    @Value("${vcap.services.tms-env-service.credentials.SINGLE_PROJECT_ASSIGNMENTS_URL}")
    private String assignmentByProjectIdUrl;
     
    
    @Value("${vcap.services.tms-env-service.credentials.PROJECT_URL}")
    private String projectUrl;
    
    @Value("${vcap.services.tms-env-service.credentials.CUSTOMER_URL}")
    private String customerUrl;
    
    @Value("${vcap.services.tms-env-service.credentials.EQUIPMENTS_URL}")
    private String equipmentUrl;
    
    
    
    @Value("${vcap.services.tms-env-service.credentials.API_KEY}")
    private String apiKey;
    
    @Value("{vcap.services.tms-env-service.credentials.API_SECRET}")
    private String apiSecret;
    
  
    @Override
    public String getAssignedTasks(String sso) {
    	
    	
    	String sig = signatureEngine.getSignature(apiKey,apiSecret, Instant.now().toEpochMilli());
    	Map<String, String>params = new HashMap<String, String>();
		params.put("sso", sso);
		params.put("Requirement", "Requirement");
		params.put("apiKey", apiKey);
		params.put("sig", sig);
     	
        
        LOGGER.info("Getting the assignments from:"+assignmentUrl);
               
        ResponseEntity<String> response = restTemplate.getForEntity(assignmentUrl, String.class,params);
        return response.getBody();
    }

    
    @Override
	public String getAssignedTasks(String projectId, String sso) {
    	

    	String sig = signatureEngine.getSignature(apiKey,apiSecret, Instant.now().toEpochMilli());
    	Map<String, String>params = new HashMap<String, String>();
		params.put("sso", sso);
		params.put("projectId", projectId);
		params.put("Requirement", "Requirement");
		params.put("apiKey", apiKey);
		params.put("sig", sig);
     	
        
        LOGGER.info("Getting the assignments from:"+assignmentByProjectIdUrl);
		
        ResponseEntity<String> response = restTemplate.getForEntity(assignmentByProjectIdUrl, String.class,params);
        return response.getBody();
	}

	@Override
	public String getProject(String projectId) {
		

		  String sig = signatureEngine.getSignature(apiKey,apiSecret, Instant.now().toEpochMilli());
		  Map<String, String>params = new HashMap<String, String>();
		  params.put("projectId", projectId);
		  params.put("apiKey", apiKey);
		  params.put("sig", sig);
		  
		  
		  LOGGER.info("Getting the Project from:"+projectUrl);
		  ResponseEntity<String> response = restTemplate.getForEntity(projectUrl, String.class, params);
		  
		  return response.getBody();
	}



	@Override
	public String getCustomer(String projectId) {
		

		String sig = signatureEngine.getSignature(apiKey,apiSecret, Instant.now().toEpochMilli());
		Map<String, String>params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("apiKey", apiKey);
		params.put("sig", sig);
		
		
		LOGGER.info("Getting the Customer from:"+customerUrl);
		ResponseEntity<String> response = restTemplate.getForEntity(customerUrl, String.class, params);
		
		return response.getBody();
	}







	@Override
	public String getEquipment(String projectId) {
		
		

		String sig = signatureEngine.getSignature(apiKey,apiSecret, Instant.now().toEpochMilli());
		Map<String, String>params = new HashMap<String, String>();
		params.put("projectId", projectId);
		params.put("apiKey", apiKey);
		params.put("sig", sig);
		final String url = equipmentUrl;
		
		LOGGER.info("Getting the Equiments from:"+url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
		
		return response.getBody();
		
	}

   

}
