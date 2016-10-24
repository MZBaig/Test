package com.ge.fsmp.connector.domain.mashaery;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Gagandeep Singh on 9/14/16.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Project {
	
	private String active;
	
	private String jobNumber; 
	
	private String projectID;
	
	private String projectName;
	
	private String outageID;
	
	private String fieldPL;
	
	private String projectDescription;
	
	private String plannedStartDate;
	
	private String plannedEndDate;

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	
	public String getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(String plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public String getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(String plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	
	public String getOutageID() {
		return outageID;
	}

	public void setOutageID(String outageID) {
		this.outageID = outageID;
	}

	public String getFieldPL() {
		return fieldPL;
	}

	public void setFieldPL(String fieldPL) {
		this.fieldPL = fieldPL;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	


	public Project(String active, String jobNumber, String projectID, String projectName, String outageID,
			String fieldPL, String projectDescription, String plannedStartDate, String plannedEndDate) {
		
		this.active = active;
		this.jobNumber = jobNumber;
		this.projectID = projectID;
		this.projectName = projectName;
		this.outageID = outageID;
		this.fieldPL = fieldPL;
		this.projectDescription = projectDescription;
		this.plannedStartDate = plannedStartDate;
		this.plannedEndDate = plannedEndDate;
	}

	public Project(){
		
	}

	
	@Override
	public boolean equals(Object o){
		
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Project project = (Project) o; 
        
        return  Objects.equals(active, project.active)&&
        		Objects.equals(jobNumber, project.jobNumber)&&
        		Objects.equals(projectID, project.projectID)&&
        		Objects.equals(projectName, project.projectName)&&
        		Objects.equals(outageID, project.outageID)&&
        		Objects.equals(fieldPL, project.fieldPL)&&
        		Objects.equals(projectDescription, project.projectDescription)&&
        		Objects.equals(plannedStartDate, project.plannedStartDate)&&
        		Objects.equals(plannedEndDate, project.plannedEndDate);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(active, jobNumber, projectID, projectName, outageID, fieldPL, projectDescription, plannedStartDate, plannedEndDate);
	}
}
