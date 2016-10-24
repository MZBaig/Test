package com.ge.fsmp.connector.engines.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ge.fsmp.connector.domain.mashaery.AdditionalFields;
import com.ge.fsmp.connector.domain.mashaery.AggOutage;
import com.ge.fsmp.connector.domain.mashaery.Assignments;
import com.ge.fsmp.connector.domain.mashaery.CustomerDetails;
import com.ge.fsmp.connector.domain.mashaery.Equipment;
import com.ge.fsmp.connector.domain.mashaery.Project;
import com.ge.fsmp.connector.engines.IOutageEngine;
import com.ge.fsmp.coonector.exceptions.InvalidDataException;

/**
 * Created by Gagandeep Singh on 9/21/16.
 */

@Service
public class OutageEngine implements IOutageEngine {

    private static final String MAINTENANCE_SERVICES = "MaintenanceServices(MS)";
    private static final String TECHNOLOGY_SERVICES = "TechnologyServices(TS)";
    private static final String CONTRACTUAL_SERVICES = "ContractualServices(CS)";
    private static final String INSTALLATION = "Installation";

    @Override
    public List<AggOutage> getAggOutages(List<Project> projects, Map<String, List<Assignments>> assignmentMap,
            Map<String, CustomerDetails> customerMap, Map<String, List<Equipment>> esnMap) {

        if (projects == null || customerMap == null || esnMap == null || assignmentMap == null) {
            return null;
        }

        List<AggOutage> outages = new ArrayList<AggOutage>();

        for (Project project : projects) {
            AggOutage outage = getAggOutage(project, assignmentMap.get(project.getProjectID()),
                    esnMap.get(project.getProjectID()), customerMap.get(project.getProjectID()));
            if (outage.getProjectType() != null) {
                outages.add(outage);
            }

        }

        return outages;
    }

    @Override
    public AggOutage getAggOutage(Project project, List<Assignments> assignments, List<Equipment> equipments,
            CustomerDetails cutomerDetails) {

        AggOutage outage = new AggOutage();

        outage.setId(Long.parseLong(project.getProjectID()));
        outage.setEsn(getEsnList(equipments));
        outage.setCustomerName(cutomerDetails.getName());
        outage.setCity(cutomerDetails.getSiteCity());
        outage.setCountry(cutomerDetails.getCountry());
        outage.setProjectStartDate(getInstant(project.getPlannedStartDate()));
        outage.setProjectCompletionDate(getInstant(project.getPlannedEndDate()));
        outage.setDescription(project.getProjectDescription());
        outage.setOutageTypeList(new ArrayList<String>());
        outage.setStatus(project.getActive());
        outage.setProjectType(getProjectType(project.getOutageID(), project.getFieldPL()));
        outage.setAdditonalFields(getAdditionalData(project,assignments));

        return outage;
    }

    private Instant getInstant(String date) {

        if (date == null) {
            return null;
        }

        return LocalDateTime.parse(date).toInstant(ZoneOffset.UTC);
    }

    // Sometimes ESN's are coming as string of ';' separated values
    private List<String> getEsnList(List<Equipment> equipments) {

        List<String> esnList = new ArrayList<String>();

        try {
            for (Equipment equipment : equipments) {
                List<String> esns = Arrays.asList(equipment.getSerialNumber().split(";"));
                esnList.addAll(esns.stream().map(esn -> esn.trim()).collect(Collectors.toList()));
            }
        } catch (Exception e) {

            throw new InvalidDataException("Error creating esnList from equipments", e);

        }

        return esnList;
    }

    private List<Map<String, Instant>> getAssignmentDates(List<Assignments> assignments) {

        Map<String, Instant> date;
        List<Map<String, Instant>> dates = new ArrayList<>();

        for (Assignments assignment : assignments) {
            date = new HashMap<String, Instant>();
            date.put(AdditionalFields.START_DATE.getAdditionalFields(), getInstant(assignment.getAssignmentStartDate()));
            date.put(AdditionalFields.END_DATE.getAdditionalFields(), getInstant(assignment.getAssignmentEndDate()));
            dates.add(date);
        }

        return dates;
    }

    private String getProjectType(String outageId, String fieldPL) {
        fieldPL = fieldPL.replaceAll("\\s", "");
       
        if (outageId != null || MAINTENANCE_SERVICES.equalsIgnoreCase(fieldPL)
                || TECHNOLOGY_SERVICES.equalsIgnoreCase(fieldPL) || CONTRACTUAL_SERVICES.equalsIgnoreCase(fieldPL)) {

            return "outage";
        }
        if (INSTALLATION.equalsIgnoreCase(fieldPL)) {
            return "Installation";
        }
        return null;
    }
    
    
   private Map<String,Object> getAdditionalData(Project project,List<Assignments> assignments){
       
       Map<String, Object> data = new HashMap<String,Object>();
       data.put(AdditionalFields.ASSIGNMENT_DATES.getAdditionalFields(), getAssignmentDates(assignments));
       data.put(AdditionalFields.IBAS_ID.getAdditionalFields(), project.getOutageID());
       data.put(AdditionalFields.JOB_NUMBER.getAdditionalFields(), project.getJobNumber());
       
       
       return data;
   } 

}
