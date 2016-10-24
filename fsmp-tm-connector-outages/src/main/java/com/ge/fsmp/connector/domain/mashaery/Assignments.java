
/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.fsmp.connector.domain.mashaery;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by gagandeep on 09/14/16.
 */


@JsonIgnoreProperties(ignoreUnknown=true)
public class Assignments {
	
	private String assignmentId;
	
	private String fseSso;
	
	private String assignmentStartDate;
	
	private String assignmentEndDate;
	
	private String projectID;
	
	
	public String getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}
	
	public String getFseSso() {
		return fseSso;
	}
	public void setFseSso(String fseSso) {
		this.fseSso = fseSso;
	}
	
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectId) {
		this.projectID = projectId;
	}
	
	public String getAssignmentStartDate() {
		return assignmentStartDate;
	}
	public void setAssignmentStartDate(String assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}
	public String getAssignmentEndDate() {
		return assignmentEndDate;
	}
	public void setAssignmentEndDate(String assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}
	
	
	public Assignments(String assignmentId, String fseSso, String assignmentStartDate, String assignmentEndDate,
			String projectID) {
		
		this.assignmentId = assignmentId;
		this.fseSso = fseSso;
		this.assignmentStartDate = assignmentStartDate;
		this.assignmentEndDate = assignmentEndDate;
		this.projectID = projectID;
	}
	public Assignments(){
		
	}
	
	@Override
	public boolean equals(Object o){
		
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Assignments assignment = (Assignments) o;
        
        return  Objects.equals(assignmentId, assignment.assignmentId)&&
        		Objects.equals(fseSso, assignment.fseSso)&&
        		Objects.equals(assignmentStartDate, assignment.assignmentStartDate)&&
        		Objects.equals(assignmentEndDate, assignment.assignmentEndDate)&&
        		Objects.equals(projectID, assignment.projectID);
		
	}
	
	
	@Override
	public int hashCode(){
		return Objects.hash(assignmentId, fseSso, assignmentStartDate, assignmentEndDate, projectID);
	}
	
	
}
