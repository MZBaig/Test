package com.ge.fsmp.connector.domain.mashaery;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Gagandeep Singh on 9/14/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Equipment {
	
	private String serialNumber;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Equipment(String serialNumber) {
		
		this.serialNumber = serialNumber;
	}
	
	public Equipment(){
		
	}
	
	@Override
	public boolean equals(Object o){
		
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipment equipment =(Equipment) o;
        
        return Objects.equals(serialNumber, equipment.serialNumber);
        
	}
	
	
	@Override
	public int hashCode(){
		
	   return Objects.hash(serialNumber);
	}

}
