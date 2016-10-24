package com.ge.fsmp.connector.domain.mashaery;

public enum AdditionalFields {
    
    IBAS_ID("ibasID"), JOB_NUMBER("jobNumber"), ASSIGNMENT_DATES("assignmentDates"), START_DATE("startDate"), END_DATE("endDate");
    
    
    private String fieldName;
    
    private AdditionalFields(String fieldName){
        
        this.fieldName =fieldName;
    }
    public String getAdditionalFields(){
        return fieldName;
    }

}
